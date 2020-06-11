package attori;

import org.bson.Document;

public class Utente extends Persona {

	private String codiceFiscale;


	public Utente(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String codiceFiscale) {
		super(nome, cognome, telefono, email, password, tipoUtente);
		setCodiceFiscale(codiceFiscale);
	}

	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		boolean reg = codiceFiscale.matches("\\p{Upper}{6}\\p{Digit}{2}\\p{Upper}\\p{Digit}{2}\\p{Upper}\\p{Digit}{3}\\p{Upper}");		
		if(reg) {
			this.codiceFiscale = codiceFiscale;
		} else {
			throw new IllegalArgumentException(codiceFiscale + " Non è un codice fiscale valido");
		}
	}
	
	public Document utenteToDocument() {
		Document utente = new Document().append("nome", getNome())
				.append("cognome", getCognome())
				.append("telefono", getTelefono())
				.append("email", getEmail())
				.append("password", getPassword())
				.append("tipoUtente", getTipoUtente())
				.append("codiceFiscale", getCodiceFiscale());
		return utente;	
	}


	@Override
	public String toString() {
		return "Utente [codiceFiscale=" + codiceFiscale + ", getNome()=" + getNome() + ", getCognome()=" + getCognome()
				+ ", getTelefono()=" + getTelefono() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
				+ "]";
	}
	
	
}
