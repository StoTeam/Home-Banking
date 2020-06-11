package attori;

public class Azienda extends Persona {
	
	private String ragioneSociale;
	private String pIva;
	private String sede;

	public Azienda(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String ragioneSociale, String pIva, String sede) {
		super(nome, cognome, telefono, email, password, tipoUtente);
		setRagioneSociale(ragioneSociale);
		setpIva(pIva);
		setSede(sede);
		
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		if(ragioneSociale != null && !ragioneSociale.trim().isEmpty())
			this.ragioneSociale = ragioneSociale;
	}
	public String getpIva() {
		return pIva;
	}
	public void setpIva(String pIva) {
		boolean reg = pIva.matches("p{Digit}{11}");
		if(reg) {
			this.pIva = pIva;
		}
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		if(sede != null && !sede.trim().isEmpty())
			this.sede = sede;
	}
	
	

}
