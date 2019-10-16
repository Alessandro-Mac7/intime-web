package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Prenotazione;
import persistence.dao.PrenotazioneDao;

public class PrenotazioneDaoJDBC implements PrenotazioneDao {

	private DataSource dataSource;

	public PrenotazioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Prenotazione prenotazione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "INSERT INTO prenotazione (data_prenotazione,mail_cliente,codice_appuntamento) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			statement.setString(2, prenotazione.getMail_cliente());
			statement.setLong(3, prenotazione.getCodice_appuntamento());
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
	public Prenotazione findByPrimaryKey(String mail_cliente, long codiceAppuntamento) {
		Prenotazione prenotazione = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM prenotazione WHERE mail_cliente = ? AND codice_appuntamento = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail_cliente);
			statement.setLong(2, codiceAppuntamento);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				prenotazione = new Prenotazione();
				prenotazione.setMail_cliente(result.getString("mail_cliente"));
				prenotazione.setCodice_appuntamento(result.getLong("codice_appuntamento"));
				prenotazione.setData_prenotazione(result.getDate("data_prenotazione").toLocalDate());
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
		return prenotazione;
	}

	@Override
	public void delete(String mail,long codice) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM prenotazione WHERE mail_cliente = ? AND codice_appuntamento = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, mail);
			statement.setLong(2, codice);
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
	public List<Prenotazione> findByCliente(String mail_cliente) {
		List<Prenotazione> prenotazioni = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM prenotazione WHERE mail_cliente = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail_cliente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prenotazione prenotazione = new Prenotazione();
				prenotazione.setMail_cliente(result.getString("mail_cliente"));
				prenotazione.setCodice_appuntamento(result.getLong("codice_appuntamento"));
				prenotazione.setData_prenotazione(result.getDate("data_prenotazione").toLocalDate());
				prenotazioni.add(prenotazione);
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
		return prenotazioni;
	}

}
