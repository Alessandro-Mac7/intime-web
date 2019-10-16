package model;

import java.time.LocalDate;

public class Prenotazione {

	private String mail_cliente;
	private long codice_appuntamento;
	private LocalDate data_prenotazione;

	public Prenotazione() {
	}

	public Prenotazione(String mail_cliente, long codice_appuntamento) {
		this.codice_appuntamento = codice_appuntamento;
		this.mail_cliente = mail_cliente;
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

	public void setMail_cliente(String mail_cliente) {
		this.mail_cliente = mail_cliente;
	}

	public LocalDate getData_prenotazione() {
		return data_prenotazione;
	}

	public void setData_prenotazione(LocalDate data_prenotazione) {
		this.data_prenotazione = data_prenotazione;
	}
}
