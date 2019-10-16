package model;

public class Cliente {

	private String mail;
	private String password;
	private String nome;
	private String cognome;
	private String telefono;

	public Cliente(String mail, String password, String nome, String cognome, String telefono) {
		this.mail = mail;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
	}

	public Cliente() {
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
