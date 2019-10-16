package persistence;

import persistence.dao.*;

public interface DaoFactory {
	
	public ClienteDao getClienteDao();
	
	public ProfessionistaDao getProfessionistaDao();

	public AppuntamentoDao getAppuntamentoDao();

	public ValutazioneDao getValutazioneDao();
	
	public PrenotazioneDao getPrenotazioneDao();
		
	public UtilityDao getUtilityDao();
	
	public LoginDao getLoginDao();
}
