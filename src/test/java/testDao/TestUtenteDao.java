package testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Utente;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.GeneralDao;
import com.stoteam.dao.UtenteDao;

class TestUtenteDao {

	@Test
	void test_getName() {
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
        Utente accountDb = UtenteDao.getUtente(c, account.getId());
        System.out.println(accountDb.toString());
        String result = accountDb.getNome();
        UtenteDao.removeUtente(c, accountDb.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertEquals(name, result, "The name must be the same");
    }
	@Test
	void test_editName() {
        // given
        String name = "John";
        String surname = "Doe";
    	String telefono = "000000111";
    	String email = "john@doea.it";
    	String password = "aaablablabla";
    	String indirizzo = "via le mani dal naso, 69";
    	String codiceFiscale = "AAABBB93C29H501L";
    	int tipoUtente = 1;
    	Utente account = new Utente(name, surname, telefono, email, password, tipoUtente, indirizzo, codiceFiscale);        
        // then
        Connection c = DbConnection.Connect();
        UtenteDao.UpUtente(c, account);
        GeneralDao.update(c, "utente", "nome", "asdrubale", UtenteDao.getIdUtente(c, codiceFiscale));
        Utente accountDb = UtenteDao.getUtente(c, account.getId());
        System.out.println(accountDb.toString());
        String result = accountDb.getNome();
        UtenteDao.removeUtente(c, accountDb.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertNotEquals(name, result, "The name must NOT be the same");
    }
}
