package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appuntamento {

	private Long codice;
	private LocalDate data;
	private LocalTime ora_inizio;
	private LocalTime ora_fine;
	private String mail_professionista;
	private String descrizione;
	private int numero_clienti;
	private LocalDate data_creazione;


	public Appuntamento() {
	}

	public Appuntamento(Long ID, LocalDate data, LocalTime oraInizio, LocalTime oraFine, String codice_professionista) {
		this.codice = ID;
		this.data = data;
		this.ora_inizio = oraInizio;
		this.ora_fine = oraFine;
		this.mail_professionista=codice_professionista;
	}
	
	

	public Appuntamento(Long codice, LocalDate data, LocalTime ora_inizio, LocalTime ora_fine, String descrizione, int numero_clienti, 
			String mail_professionista) {
		this.codice = codice;
		this.data = data;
		this.ora_inizio = ora_inizio;
		this.ora_fine = ora_fine;
		this.descrizione = descrizione;
		this.numero_clienti = numero_clienti;
		this.mail_professionista = mail_professionista;

	}

	public Long getCodice() {
		return codice;
	}

	public void setCodice(Long iD) {
		codice = iD;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOra_inizio() {
		return ora_inizio;
	}

	public void setOra_inizio(LocalTime oraInizio) {
		this.ora_inizio = oraInizio;
	}

	public LocalTime getOra_fine() {
		return ora_fine;
	}

	public void setOra_fine(LocalTime oraFine) {
		this.ora_fine = oraFine;
	}

	public String getMail_professionista() {
		return mail_professionista;
	}

	public void setMail_professionista(String codice_professionista) {
		this.mail_professionista = codice_professionista;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getNumero_clienti() {
		return numero_clienti;
	}

	public void setNumero_clienti(int numero_clienti) {
		this.numero_clienti = numero_clienti;
	}

	public LocalDate getData_creazione() {
		return data_creazione;
	}

	public void setData_creazione(LocalDate data_creazione) {
		this.data_creazione = data_creazione;
	}


}
