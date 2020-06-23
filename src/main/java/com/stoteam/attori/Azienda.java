package com.stoteam.attori;

public class Azienda extends Persona {
	
	private String ragioneSociale;
	private String pIva;

	public Azienda(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String indirizzo, String ragioneSociale, String pIva) {
		super(nome, cognome, telefono, email, password, tipoUtente, indirizzo);
		setRagioneSociale(ragioneSociale);
		setpIva(pIva);
		
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

	@Override
	public String toString() {
		return "Azienda [ragioneSociale=" + ragioneSociale + ", pIva=" + pIva + ", getIdIntestatario()="
				+ getIdIntestatario() + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getCognome()="
				+ getCognome() + ", getTelefono()=" + getTelefono() + ", getEmail()=" + getEmail() + ", getPassword()="
				+ getPassword() + ", isAuth()=" + isAuth()
				+ ", getTipoUtente()=" + getTipoUtente() + ", getIndirizzo()=" + getIndirizzo() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
