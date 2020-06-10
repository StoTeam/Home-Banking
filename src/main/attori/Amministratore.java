package attori;

import java.util.ArrayList;
import java.util.List;

public class Amministratore extends Persona {

	private static final int BASE = 1;
	private static final int INTERMEDIO = 2;
	private static final int ALTO = 3;
	//cambiare con ENUM
	private int livelloAccesso;
	private List<String> areaCompetenza = new ArrayList<>();
	
	
	public Amministratore(String nome, String cognome, String telefono, String email, String password, int tipoUtente) {
		super(nome, cognome, telefono, email, password, tipoUtente);
		
	}

}
