package testDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.stoteam.attori.Utente;
import com.stoteam.conto.Conto;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.GeneralDao;
import com.stoteam.dao.MovimentoDao;
import com.stoteam.dao.UtenteDao;
import com.stoteam.movimenti.Bonifico;
import com.stoteam.movimenti.Movimento;

public class TestMovimentoDao {
	
	@Test
	void test_getName() {
        // given
        String tipoMovimento = "bonifico";
        double importo = 1000;
        Utente u1 = new Utente("ablabaa", "sdasdwasdawdas", "00000000000", "jjjjjj@eeeee.ee", "xxxxxxxxxxxx", 1, "via velletri", "ADFXCV93C32H501L");
        Utente u2 = new Utente("grhease", "thgsdfadfawdad", "00000011111", "qqqqqq@eeeee.ee", "xxxxxxxxxxxx", 1, "via velletri", "AGXXCV93C32H666L");
        Conto mittente = new Conto("Ciccia", "AAACCCVVVBBBB", u1, importo);
    	Conto destinatario = new Conto ("Franca", "AAACCCRRRTTTT", u2, importo);
    	String causale = "stipendio";
    	
        Bonifico bonifico = new Bonifico(importo, mittente, tipoMovimento, destinatario, causale);
        
        // then
        Connection c = DbConnection.Connect();
        UtenteDao.UpUtente(c, u1);
        UtenteDao.UpUtente(c, u2);
        ContoDao.UpConto(c, mittente, u1.getIdIntestatario());
        ContoDao.UpConto(c, destinatario, u2.getIdIntestatario());
        MovimentoDao.UpMovimento(c, bonifico);
        Movimento accountDb = MovimentoDao.getMovimento(c, bonifico.getId());
        System.out.println(bonifico.getId());
        System.out.println(accountDb.toString());
        String result = accountDb.getTipoMovimento();
        MovimentoDao.removeMovimento(c, accountDb.getId());
        ContoDao.removeConto(c, destinatario.getId());
        ContoDao.removeConto(c, mittente.getId());
        UtenteDao.removeUtente(c, u2.getId());
        UtenteDao.removeUtente(c, u1.getId());
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // expect
        assertEquals(tipoMovimento, result, "tipo movimento must be the same");
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
