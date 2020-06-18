package testDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Azienda;
import com.stoteam.dao.AziendaDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.GeneralDao;

class TestAziendaDao {

	@Test
	void test_getName() {
        // given
        String name = "John";
        String surname = "Doe";
    	String telefono = "000000111";
    	String email = "john@doea.it";
    	String password = "aaablablabla";
    	String indirizzo = "via le mani dal culo, 69";
    	String ragioneSociale = "BBBZZZ00B00H000L";
    	String pIva = "01234567891";
    	int tipoUtente = 1;
        Azienda account = new Azienda(name, surname, telefono, email, password, tipoUtente, indirizzo, ragioneSociale, pIva);
        
        // then
        Connection c = DbConnection.Connect();
        AziendaDao.UpAzienda(c, account);
        Azienda accountDb = AziendaDao.getAzienda(c, account.getId());
        System.out.println(accountDb.toString());
        String result = accountDb.getRagioneSociale();
        AziendaDao.removeAzienda(c, accountDb.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertEquals(ragioneSociale, result, "The name must be the same");
        
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
    	String ragioneSociale = "HoStatoIo SPA";
    	String pIva = "01234567891";
    	int tipoUtente = 1;
        Azienda account = new Azienda(name, surname, telefono, email, password, tipoUtente, indirizzo, ragioneSociale, pIva);
        
        // then
        Connection c = DbConnection.Connect();
        //AziendaDao.UpAzienda(c, account);
        GeneralDao.update(c, "aziende", "ragione_sociale", "asdrubale", AziendaDao.getIdAzienda(c, pIva));
        Azienda accountDb = AziendaDao.getAzienda(c, account.getId());
        System.out.println(accountDb.toString());
        String result = accountDb.getRagioneSociale();
        AziendaDao.removeAzienda(c, accountDb.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertNotEquals(ragioneSociale, result, "The name must NOT be the same");
    }
	
}
