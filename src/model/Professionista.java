package model;

public class Professionista {

	private String mail;
	private String password;
	private String nome;
	private String cognome;
	private String professione;
	private String telefono;
	private String descrizione;
	private String sito;
	private String indirizzo;
	

	public Professionista(String mail, String password, String nome, String cognome, String professione) {
		this.mail = mail;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.setProfessione(professione);
	}

	public Professionista(String mail, String password, String nome, String cognome, String professione,
			String telefono, String descrizione, String sito, String indirizzo) {
		this.mail = mail;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.professione = professione;
		this.telefono = telefono;
		this.descrizione = descrizione;
		this.sito = sito;
		this.indirizzo = indirizzo;
	}

	public Professionista() {
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

	public String getProfessione() {
		return professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSito() {
		return sito;
	}

	public void setSito(String sito) {
		this.sito = sito;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	
}
