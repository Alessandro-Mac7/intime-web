package model;

import java.time.LocalDate;

public class Valutazione {

	private Long codice;
	private String descrizione;
	private LocalDate data;
	private long codice_appuntamento;
	private String mail_cliente;
	private int rating;

	public Valutazione() {
	}

	public Valutazione(Long codice, String descrizione, LocalDate data,int rating, long codice_appuntamento, String codice_cliente) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.rating = rating;
		this.codice_appuntamento = codice_appuntamento;
		this.mail_cliente = codice_cliente;
	}

	public Long getCodice() {
		return codice;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public long getCodice_appuntamento() {
		return codice_appuntamento;
	}

	public void setCodice_appuntamento(long codice_appuntamento) {
		this.codice_appuntamento = codice_appuntamento;
	}

	public String getMail_cliente() {
		return mail_cliente;
	}

	public void setMail_cliente(String codice_cliente) {
		this.mail_cliente = codice_cliente;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}