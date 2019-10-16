package persistence;

import persistence.dao.AppuntamentoDao;
import persistence.dao.ClienteDao;
import persistence.dao.PrenotazioneDao;
import persistence.dao.ProfessionistaDao;
import persistence.dao.ValutazioneDao;

public class PostgresDaoFactory implements DaoFactory {

	private static DataSource dataSource = null;
	private static PostgresDaoFactory instance = null;

	static {
		try {
			Class.forName("org.postgresql.Driver");
			dataSource = new DataSource("jdbc:postgresql://horton.elephantsql.com:5432/sgjzqpnj", "sgjzqpnj", "TKPA08BvGxZGnMQ7RJOFfXMdLZ_iFV4K");
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	private PostgresDaoFactory() {
	}

	public static PostgresDaoFactory getInstance() {
		if (instance == null)
			instance = new PostgresDaoFactory();
		return instance;
	}

	@Override
	public ClienteDao getClienteDao() {
		return new ClienteDaoJDBC(dataSource);
	}

	@Override
	public ProfessionistaDao getProfessionistaDao() {
		return new ProfessionistaDaoJDBC(dataSource);
	}

	@Override
	public AppuntamentoDao getAppuntamentoDao() {
		return new AppuntamentoDaoJDBC(dataSource);
	}

	@Override
	public ValutazioneDao getValutazioneDao() {
		return new ValutazioneDaoJDBC(dataSource);
	}

	@Override
	public PrenotazioneDao getPrenotazioneDao() {
		return new PrenotazioneDaoJDBC(dataSource);
	}

	@Override
	public UtilityDao getUtilityDao() {
		return new UtilityDao(dataSource);
	}
	
	@Override
	public LoginDao getLoginDao() {
		return new LoginDao(dataSource);
	}

}
