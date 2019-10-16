package persistence.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import model.Appuntamento;
import model.Cliente;
import model.Valutazione;

public interface ClienteDao {

	public void save(Cliente cliente);

	public Cliente findByMail(String mail);

	public List<Cliente> findByCognome(String cognome);

	public List<Cliente> findAll();

	public List<Appuntamento> findAppuntamentiPrenotati(String mail_professionista, String mail_cliente);

	public List<Appuntamento> findAppuntamentiPrenotabili(String mail_professionista, String mail_cliente,
			LocalDate data);

	public List<Appuntamento> findStoricoAppuntamenti(String mail);

	public List<Appuntamento> findAppuntamentiValutabili(String mail);

	public List<Appuntamento> findAppuntamentiFuturi(String mail);
	
	public List<Valutazione> findStoricoValutazioni(String mail);
	
	public Map<String, Integer> findPercentualeAppuntamenti(String mail);
	
	public List<Appuntamento> findAppuntamentiOdierni(String mail);

	public void update(Cliente cliente);

	public void delete(String mail);

}