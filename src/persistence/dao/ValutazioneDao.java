package persistence.dao;

import model.Valutazione;

public interface ValutazioneDao {
	
	public void save(Valutazione valutazione);

	public Valutazione findByPrimaryKey(Long codice);

	public void update(Valutazione valutazione);

	public void delete(Long codice_appuntamento);
}