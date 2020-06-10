package attori;

public abstract class Persona {
	
	private String nome;
	private String cognome;	
	private String telefono;
	private String email;
	private String password;
	private boolean auth;
	private int reputazione;
	private int tipoUtente;
	
	public Persona(String nome, String cognome, String telefono, String email, String password, int tipoUtente) {
		setNome(nome);
		setCognome(cognome);
		setTelefono(telefono);
		setEmail(email);
		setPassword(password);
		setTipoUtente(tipoUtente);
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome != null && !nome.trim().isEmpty()) {
			this.nome = nome;
		}
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		if(cognome != null && !cognome.trim().isEmpty())
			this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		boolean reg = telefono.matches("^\\d+");
		if(telefono.trim().length() >= 6 && reg) {
			telefono = telefono.trim();
			this.telefono = telefono;
		}
		else throw new IllegalArgumentException();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email != null) {
			email = email.trim();
			boolean reg = email.matches("^\\w+@[a-zA-Z_.]+?\\.[a-zA-Z]{2,3}$");
			if(reg) {
			this.email = email;
			} else {
				throw new IllegalArgumentException();
			}
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password != null && password.trim().length() >= 8)
			this.password = password;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public int getReputazione() {
		return reputazione;
	}
	public void setReputazione(int reputazione) {
		this.reputazione = reputazione;
	}
	public int getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(int tipoUtente) {
		if(tipoUtente >= 0 && tipoUtente < 2)
		this.tipoUtente = tipoUtente;
	}
	
	
}
