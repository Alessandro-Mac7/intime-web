package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Appuntamento;
import persistence.dao.AppuntamentoDao;

public class AppuntamentoDaoJDBC implements AppuntamentoDao {
	private DataSource dataSource;

	public AppuntamentoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Appuntamento appuntamento) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long id = IdBroker.getId(connection);
			appuntamento.setCodice(id);
			String insert = "INSERT INTO appuntamento (codice,data,ora_inizio,ora_fine,descrizione,numero_clienti,data_creazione,mail_professionista) values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, appuntamento.getCodice());
			statement.setDate(2, java.sql.Date.valueOf(appuntamento.getData()));
			statement.setTime(3, java.sql.Time.valueOf(appuntamento.getOra_inizio()));
			statement.setTime(4, java.sql.Time.valueOf(appuntamento.getOra_fine()));
			statement.setString(5, appuntamento.getDescrizione());
			statement.setInt(6, appuntamento.getNumero_clienti());
			statement.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
			statement.setString(8, appuntamento.getMail_professionista());
			

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public Appuntamento findByPrimaryKey(Long codice) {
		Appuntamento appuntamento = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM appuntamento WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return appuntamento;
	}

	@Override
	public List<Appuntamento> findByProfessionista(String mail) {
		List<Appuntamento> appuntamenti = new ArrayList<Appuntamento>();
		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "SELECT * FROM appuntamento WHERE mail_professionista=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamenti.add(appuntamento);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return appuntamenti;
	}

	@Override
	public List<Appuntamento> findByProfessionistaPrenotabile(String mail, LocalDate data) {
		List<Appuntamento> appuntamenti = new ArrayList<Appuntamento>();
		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "SELECT * FROM appuntamento WHERE mail_professionista=? and data>=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setDate(2, java.sql.Date.valueOf(data));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamenti.add(appuntamento);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return appuntamenti;
	}

	@Override
	public void update(Appuntamento appuntamento) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "UPDATE appuntamento SET data = ?, ora_inizio = ?, ora_fine = ?, descrizione=?, numero_clienti=?, data_creazione=?, mail_professionista = ? WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setDate(1, java.sql.Date.valueOf(appuntamento.getData()));
			statement.setTime(2, java.sql.Time.valueOf(appuntamento.getOra_inizio()));
			statement.setTime(3, java.sql.Time.valueOf(appuntamento.getOra_fine()));
			statement.setString(4, appuntamento.getDescrizione());
			statement.setInt(5, appuntamento.getNumero_clienti());
			statement.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
			statement.setString(7, appuntamento.getMail_professionista());
			statement.setLong(8, appuntamento.getCodice());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(long codice) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM appuntamento WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, codice);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public List<Appuntamento> findAppuntamentiEffettuati(String mail) {
		List<Appuntamento> appuntamenti = new ArrayList<Appuntamento>();

		LocalDate date = LocalDate.now();
		LocalTime orarioFine = LocalTime.now();

		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "SELECT * FROM appuntamento WHERE mail_professionista=? and (data<? or (data=? and ora_fine<=?))  ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setDate(2, java.sql.Date.valueOf(date));
			statement.setDate(3, java.sql.Date.valueOf(date));
			statement.setTime(4, Time.valueOf(orarioFine));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamenti.add(appuntamento);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return appuntamenti;
	}

	@Override
	public List<Appuntamento> findAppuntamentiDaEffettuare(String mail) {
		List<Appuntamento> appuntamenti = new ArrayList<Appuntamento>();

		LocalDate date = LocalDate.now();
		LocalTime orarioFine = LocalTime.now();
		
		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "SELECT * FROM appuntamento WHERE mail_professionista=? and (data>? or (data=? and ora_fine>=?))  ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setDate(2, java.sql.Date.valueOf(date));
			statement.setDate(3, java.sql.Date.valueOf(date));
			statement.setTime(4, Time.valueOf(orarioFine));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamenti.add(appuntamento);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return appuntamenti;
	}

	@Override
	public int findNumeroPrenotati(long codice) {
		Connection connection = this.dataSource.getConnection();
		int numeroPrenotati=0;
		try {
			
			String query = "SELECT count(*) as numero FROM prenotazione WHERE codice_appuntamento=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				numeroPrenotati=result.getInt("numero");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return numeroPrenotati;
	}

}
