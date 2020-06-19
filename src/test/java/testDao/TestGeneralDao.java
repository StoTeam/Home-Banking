package testDao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Azienda;
import com.stoteam.attori.Utente;
import com.stoteam.dao.AziendaDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.GeneralDao;
import com.stoteam.dao.UtenteDao;

class TestGeneralDao {

	@Test
	void test_getName() {
        // given
        String name = "Jack";
        String surname = "Doe";
    	String telefono = "000000111";
    	String email = "jack@doea.it";
    	String password = "aaablablabla";
    	String indirizzo = "via le mani dal naso, 69";
    	String codiceFiscale = "DOEJCK00B00H000L";
    	int tipoUtente = 1;
        Utente account = new Utente(name, surname, telefono, email, password, tipoUtente, indirizzo, codiceFiscale);
        
        // then
        Connection c = DbConnection.Connect();
        UtenteDao.UpUtente(c, account);
        GeneralDao.update(c, "utente", "nome", "Janice", account.getId());
        Utente accountDb = UtenteDao.getUtente(c, account.getId());
        System.out.println(accountDb.toString());
        String result = accountDb.getNome();
        System.out.println(result);
        UtenteDao.removeUtente(c, accountDb.getId());
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertNotEquals(name, result, "The name must be the same");
    }
	
	@Test
	void test_getAzOrPERS() {
        // given
        String name = "John";
        String surname = "Doe";
    	String telefono = "000000111";
    	String email = "john@doea.it";
    	String password = "aaablablabla";
    	String indirizzo = "via le mani dal naso, 69";
    	String codiceFiscale = "BBBZZZ00B00H000L";
    	int tipoUtente = 1;
        Utente account = new Utente(name, surname, telefono, email, password, tipoUtente, indirizzo, codiceFiscale);
        
        // then
        Connection c = DbConnection.Connect();
        UtenteDao.UpUtente(c, account);
        boolean result = GeneralDao.azOrPers(c, account.getIdIntestatario());
        UtenteDao.removeUtente(c, account.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertTrue("this will succeed", result);
    }

	@Test
	void test_getAZOrPers() {
		//given
		String name = "John";
        String surname = "Doe";
    	String telefono = "000000111";
    	String email = "mel@doea.it";
    	String password = "aaablablabla";
    	String indirizzo = "via le mani dal naso, 69";
    	String ragioneSociale = "BBBfffZ00B00H000L";
    	String pIva = "01234567891";
    	int tipoUtente = 1;
        Azienda account = new Azienda(name, surname, telefono, email, password, tipoUtente, indirizzo, ragioneSociale, pIva);
        
        // then
        Connection c = DbConnection.Connect();
        AziendaDao.UpAzienda(c, account);
        boolean result = GeneralDao.azOrPers(c, account.getIdIntestatario());
        AziendaDao.removeAzienda(c, account.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertTrue("this will succeed", result);
    }
}
