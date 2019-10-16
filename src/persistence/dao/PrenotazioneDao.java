package persistence.dao;

import java.util.List;

import model.Prenotazione;

public interface PrenotazioneDao {

	public void save(Prenotazione prenotazione);

	public Prenotazione findByPrimaryKey(String mail_cliente,long codiceAppuntamento);
	
	public List<Prenotazione> findByCliente(String mail_cliente);

	public void delete(String mail, long codice);
}
