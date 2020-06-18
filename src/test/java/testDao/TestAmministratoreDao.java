package testDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Amministratore;
import com.stoteam.attori.Azienda;
import com.stoteam.dao.AmministratoreDao;
import com.stoteam.dao.AziendaDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.GeneralDao;

public class TestAmministratoreDao {
	
	@Test
	void test_getName() {
        // given
        String name = "John";
        String surname = "Doe";
    	String telefono = "000000111";
    	String email = "john@doea.it";
    	String password = "aaablablabla";
    	String indirizzo = "via le mani, 69";
    	String livelloAccesso = "basso";
    	List<String> areaCompetenza = new ArrayList<>();
    	areaCompetenza.add("ciao");
    	int tipoUtente = 1;
        Amministratore account = new Amministratore(name, surname, telefono, email, password, tipoUtente, indirizzo, livelloAccesso, areaCompetenza);
                
        // then
        Connection c = DbConnection.Connect();
        AmministratoreDao.UpAmministratore(c, account);
        System.out.println("id assegnato all'account: " + account.getId());
        Amministratore accountDb = AmministratoreDao.getAmministratore(c, account.getId());
        System.out.println("mi sono rotto il cazzo");
//        System.out.println(accountDb.getCognome());
        String result = accountDb.getNome();
        AmministratoreDao.removeAmministratore(c, accountDb.getId());
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
        
//	@Test
//	void test_editName() {
//        // given
//        String name = "John";
//        String surname = "Doe";
//    	String telefono = "000000111";
//    	String email = "john@doea.it";
//    	String password = "aaablablabla";
//    	String indirizzo = "via le mani dal naso, 69";
//    	String ragioneSociale = "HoStatoIo SPA";
//    	String pIva = "01234567891";
//    	int tipoUtente = 1;
//        Azienda account = new Azienda(name, surname, telefono, email, password, tipoUtente, indirizzo, ragioneSociale, pIva);
//        
//        // then
//        Connection c = DbConnection.Connect();
//        //AziendaDao.UpAzienda(c, account);
//        GeneralDao.update(c, "aziende", "ragione_sociale", "asdrubale", AziendaDao.getIdAzienda(c, pIva));
//        Azienda accountDb = AziendaDao.getAzienda(c, account.getId());
//        System.out.println(accountDb.toString());
//        String result = accountDb.getRagioneSociale();
//        AziendaDao.removeAzienda(c, accountDb.getId());
//        //System.out.println(result);
//        try {
//			c.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        // expect
//        assertNotEquals(ragioneSociale, result, "The name must NOT be the same");
//    }
	

}
