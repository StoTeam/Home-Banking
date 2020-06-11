package attori;

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
}
