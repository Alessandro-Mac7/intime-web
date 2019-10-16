package persistence;

public class DataBaseManager {

	private static DataBaseManager instance = null;

	public static DataBaseManager getInstance() {
		if (instance == null)
			instance = new DataBaseManager();
		return instance;
	}

	private DaoFactory daoFactory;

	private DataBaseManager() {
		daoFactory = PostgresDaoFactory.getInstance();
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

}
