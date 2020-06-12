package com.stoteam.attori;

public class Azienda extends Persona {
	
	private String ragioneSociale;
	private String pIva;

	public Azienda(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String indirizzo, String ragioneSociale, String pIva, String sede) {
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
}
