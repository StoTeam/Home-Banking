package testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Persona;
import com.stoteam.attori.Utente;
import com.stoteam.conto.Conto;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

class TestContoDao {

	@Test
	void test() {
		// given
		Utente u = new Utente("John", "Doe", "000000111", "john@doea.it", "aaablablabla", 1, "via le mani dal naso, 69", "BBBZZZ00B00H000L");		
		String codice = "aaabbbbxxx9999";
		String iban = "111222bnnn344b2b5";
		double saldo = 1000;
		Conto conto = new Conto(codice, iban, u, saldo);

		// then
		Connection c = DbConnection.Connect();
		UtenteDao.UpUtente(c, u);
		ContoDao.UpConto(c, conto, u.getIdIntestatario());
		Conto contoDb = ContoDao.getConto(c, conto.getId());
		System.out.println(contoDb.toString());
		String result = contoDb.getCodice();
		ContoDao.removeConto(c, contoDb.getId());
		UtenteDao.removeUtente(c, u.getId());
		//System.out.println(result);
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// expect
		assertEquals(codice, result, "The codex must be the same");
	}
}

