package persistence.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import model.Appuntamento;
import model.Cliente;
import model.Professionista;
import model.Valutazione;

public interface ProfessionistaDao {

	public void save(Professionista professionista);

	public Professionista findByMail(String mail);

	public List<Professionista> findByCognome(String cognome);

	public List<Professionista> findByProfessione(String professione);

	public List<Professionista> findByCognomeProfessione(String ricerca);

	public List<Cliente> findTuttiPrenotati(String mail);

	public List<Cliente> findTuttiPrenotatiSingoloAppuntamento(String mail, long codice);

	public List<String> autocomplete();

	public List<Professionista> findAll();

	public List<Appuntamento> findAppuntamentiCreatiOggi(String mail);

	public List<Appuntamento> findAppuntamentiOdierni(String mail);

	public List<Valutazione> findRecensioni(String mail);

	public List<Valutazione> findRecensioniRecenti(String mail);

	public int findMediaValutazioni(String mail);

	public int findNumeroValutazioniRicevute(String mail);

	public int findRating(String mail, int stelle);

	public List<Appuntamento> controlloInserimento(String mail, LocalDate data, LocalTime inizio, LocalTime fine);

	public Map<String, Integer> findTopClienti(String mail);
	
	public List<Appuntamento> findTuttiAppuntamentiPrenotatiSingoloCliente(String mail_cliente,String mail_professionista);
	
	public List<Valutazione> findTutteValutazioniSingoloCliente(String mail_cliente,String mail_professionista);
	
	public void update(Professionista professionista);

	public void delete(String mail);
}
