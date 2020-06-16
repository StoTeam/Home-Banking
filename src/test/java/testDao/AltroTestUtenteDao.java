package testDao;

import java.sql.Connection;
import java.sql.SQLException;

import com.stoteam.attori.Utente;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

public class AltroTestUtenteDao {

	public static void main(String[] args) {
		String name = "John";
        String surname = "Doe";
    	String telefono = "000000111";
    	String email = "john@doea.it";
    	String password = "aaablablabla";
    	String indirizzo = "via le mani dal culo, 69";
    	String codiceFiscale = "BBBZZZ00B00H000L";
    	int tipoUtente = 1;
        Utente account = new Utente(name, surname, telefono, email, password, tipoUtente, indirizzo, codiceFiscale);
        
        // then
        Connection c = DbConnection.Connect();
        UtenteDao.UpUtente(c, account);
        System.out.println(account.toString());
        
        Utente accountDb = UtenteDao.getUtente(c, account.getId());
        System.out.println(accountDb.toString());
        String result = accountDb.getNome();
        
        //System.out.println(result);
        try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
