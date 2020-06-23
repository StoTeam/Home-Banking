package testDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Utente;
import com.stoteam.carte.Bancomat;
import com.stoteam.conto.Conto;
import com.stoteam.dao.CartaDao;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

public class TestCartaDao {
	
	@Test
	void test_getName() {
        // given
		
		Utente u = new Utente("John", "Doe", "000000111", "john@doea.it", "aaablablabla", 1, "via le mani dal naso, 69", "BBBZZZ00B00H000L");
		Conto conto = new Conto("1521", "100422bnnn344b2b5", u, 1000);		
        String codSicurezza = "123";
    	String pin = "a1";
    	int tipoUtente = 1;
        Bancomat bancomat = new Bancomat(conto, pin, codSicurezza);
        
        // then
        Connection c = DbConnection.Connect();
        UtenteDao.UpUtente(c, u);
        ContoDao.UpConto(c, conto, conto.getUtente().getIdIntestatario());
        CartaDao.UpCarta(c, bancomat);
        Bancomat cartaDb = CartaDao.getCarta(c, bancomat.getId());
        System.out.println(cartaDb.toString());
        String result = cartaDb.getCodSicurezza();
        CartaDao.removeCarta(c, cartaDb.getId());
        ContoDao.removeConto(c, conto.getId());
        UtenteDao.removeUtente(c, u.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertEquals(codSicurezza, result, "The name must be the same");
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
//    	String codiceFiscale = "AAABBB93C29H501L";
//    	int tipoUtente = 1;
//    	Utente account = new Utente(name, surname, telefono, email, password, tipoUtente, indirizzo, codiceFiscale);        
//        // then
//        Connection c = DbConnection.Connect();
//        UtenteDao.UpUtente(c, account);
//        GeneralDao.update(c, "utente", "nome", "asdrubale", UtenteDao.getIdUtente(c, codiceFiscale));
//        Utente accountDb = UtenteDao.getUtente(c, account.getId());
//        System.out.println(accountDb.toString());
//        String result = accountDb.getNome();
//        UtenteDao.removeUtente(c, accountDb.getId());
//        //System.out.println(result);
//        try {
//			c.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        // expect
//        assertNotEquals(name, result, "The name must NOT be the same");
//    }

}
