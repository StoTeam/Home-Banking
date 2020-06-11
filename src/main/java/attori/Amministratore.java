package attori;

import java.util.ArrayList;
import java.util.List;

public class Amministratore extends Persona {

	
	//cambiare con ENUM
	private int livelloAccesso;
	private List<String> areaCompetenza = new ArrayList<>();
	
	
	public Amministratore(String nome, String cognome, String telefono, String email, String password, int tipoUtente) {
		super(nome, cognome, telefono, email, password, tipoUtente);
	}

}
