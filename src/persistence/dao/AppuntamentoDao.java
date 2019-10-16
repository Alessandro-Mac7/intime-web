package persistence.dao;

import java.time.LocalDate;
import java.util.List;

import model.Appuntamento;

public interface AppuntamentoDao {
	
	public void save(Appuntamento appuntamento);

	public Appuntamento findByPrimaryKey(Long codice);
	
	public List<Appuntamento> findAppuntamentiEffettuati(String mail);
	
	public List<Appuntamento> findAppuntamentiDaEffettuare(String mail);
	
	public List<Appuntamento> findByProfessionista(String mail);
	
	public List<Appuntamento> findByProfessionistaPrenotabile(String mail,LocalDate date);

	public int findNumeroPrenotati(long codice);
	
	public void update(Appuntamento appuntamento);

	public void delete(long codice);

}