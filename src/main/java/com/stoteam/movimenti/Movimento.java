/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.movimenti;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import com.stoteam.conto.Conto;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.MovimentoDao;

public abstract class Movimento {

	int id;
	Conto conto;
	double importo;
	private String tipoMovimento;
	private LocalDateTime dataEsecuzione;
	private boolean isEseguito;

	public Movimento(Conto conto, double importo, String tipoMovimento) {
		this.dataEsecuzione = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		setConto(conto);
		setImporto(importo);
		setTipoMovimento(tipoMovimento);	
	}
	@JsonbCreator
	public Movimento(@JsonbProperty("contoIban") String contoIban, 
			@JsonbProperty("importo") double importo, 
			@JsonbProperty("tipoMovimento") String tipoMovimento,
			@JsonbProperty("isEseguito") boolean isEseguito ){
		setConto(contoIban);
		setImporto(importo);
		setTipoMovimento(tipoMovimento);
		setEseguito(isEseguito);
	}
	/**
	 * @param isEseguito - Restituisce un booleano se il movimento è stato eseguito
	 * @return Destinatario
	 */
	public boolean isEseguito() {
		return isEseguito;
	}
	public void setEseguito(boolean isEseguito) {
		this.isEseguito = isEseguito;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		if(importo >= 0);
		this.importo = importo;
	}
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		if(conto != null)
			this.conto = conto;
	}
	public void setConto(String contoIban) {
		Connection c = DbConnection.Connect();
		ContoDao.getConto(c, ContoDao.getIdContoByIban(c, contoIban));
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getTipoMovimento() {
		return this.tipoMovimento;
	}
	public void setTipoMovimento(String tipoMovimento) {
		if(tipoMovimento != null && !tipoMovimento.trim().isEmpty()) {
			this.tipoMovimento = tipoMovimento.trim();
		}
	}
	public Timestamp getDataEsecuzione() {
		Timestamp ts = Timestamp.valueOf(dataEsecuzione);
		return ts;
	}
	public void setDataEsecuzione(String data) {
		Timestamp ts = Timestamp.valueOf(data);
		dataEsecuzione = ts.toLocalDateTime();
	}
	public String toJson() {
		JsonbConfig config = new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {

			@Override
			public boolean isVisible(Method arg0) {
				return false;
			}

			@Override
			public boolean isVisible(Field arg0) {
				return true;
			}
		});
		return JsonbBuilder.newBuilder().withConfig(config).build().toJson(this);
	}
	public void esegui() {
		double commissione = 2.0;
		double cifra = conto.rimuoviDenaro(importo + commissione);
		if(this instanceof Bonifico) {
			((Bonifico) this).getDestinatario().aggiungiDenaro(cifra);
		}else if(this instanceof Pagamento) {
			((Pagamento) this).getDestinatario().aggiungiDenaro(cifra);
		}
		this.setEseguito(true);
	}
	public void salva() {
		Connection c = DbConnection.Connect();
		MovimentoDao.UpMovimento(c, this);
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void rimuovi() {
		Connection c = DbConnection.Connect();
		MovimentoDao.removeMovimento(c, this.getId());
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Movimento download(int idMovimento) {
		Connection c = DbConnection.Connect();
		Movimento mo = MovimentoDao.getMovimento(c, idMovimento);
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mo;
	}
}
