package testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Utente;
import com.stoteam.conto.Conto;
import com.stoteam.conto.Prestito;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.MovimentoDao;
import com.stoteam.dao.PrestitoDao;
import com.stoteam.dao.UtenteDao;
import com.stoteam.movimenti.Bonifico;
import com.stoteam.movimenti.Movimento;

class TestPrestitoDao {

	@Test
	void test_getName() {
        // given
        double importo = 10000;
        double tan = 0.2;
        double taeg = 1.4;
        int tempo = 10;
        boolean isFisso = true;
        Utente u1 = new Utente("ablabaa", "sdasdwasdawdas", "00000000000", "jjjjjj@eeeee.ee", "xxxxxxxxxxxx", 1, "via velletri", "ADFXCV93C32H501L");
        Conto conto = new Conto("Ciccia", "AAACCCVVVBBBB", u1, 1000);
        
        Prestito prestito = new Prestito(importo, tan, taeg, tempo, isFisso, conto);
        
        // then
        Connection c = DbConnection.Connect();
        UtenteDao.UpUtente(c, u1);
        ContoDao.UpConto(c, conto, u1.getIdIntestatario());
        PrestitoDao.UpPrestito(c, prestito);
        System.out.println(prestito.getId() + "a");
        System.out.println(prestito.getConto().getId() + "conto id");
        Prestito accountDb = PrestitoDao.getPrestito(c, prestito.getId());
        System.out.println(prestito.getId() + "b");
        System.out.println(accountDb.toString());
        double result = accountDb.getImporto();
        PrestitoDao.removePrestito(c, accountDb.getId());
        ContoDao.removeConto(c, conto.getId());
        UtenteDao.removeUtente(c, u1.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertEquals(importo, result, "importo must be the same");
    }

}
