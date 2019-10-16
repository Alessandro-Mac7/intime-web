package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Valutazione;
import persistence.dao.ValutazioneDao;

public class ValutazioneDaoJDBC implements ValutazioneDao {
	private DataSource dataSource;

	public ValutazioneDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Valutazione valutazione) {
		Connection connection = this.dataSource.getConnection();
		try {
			Long codice = IdBroker.getId(connection);
			valutazione.setCodice(codice);
			String insert = "INSERT INTO valutazione (codice,descrizione,data,rating,codice_appuntamento,mail_cliente) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, valutazione.getCodice());
			statement.setString(2, valutazione.getDescrizione());
			statement.setDate(3, java.sql.Date.valueOf(valutazione.getData()));
			statement.setInt(4, valutazione.getRating());
			statement.setLong(5, valutazione.getCodice_appuntamento());
			statement.setString(6, valutazione.getMail_cliente());
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
	public Valutazione findByPrimaryKey(Long codice) {
		Valutazione valutazione = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM valutazione WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				valutazione = new Valutazione();
				valutazione.setCodice(result.getLong("codice"));
				valutazione.setDescrizione(result.getString("descrizione"));
				valutazione.setData((result.getDate("data")).toLocalDate());
				valutazione.setRating(result.getInt("rating"));
				valutazione.setMail_cliente(result.getString("mail_cliente"));
				valutazione.setCodice(result.getLong("codice_appuntamento"));
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
		return valutazione;
	}

	@Override
	public void update(Valutazione valutazione) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "UPDATE valutazione SET descrizione = ?, data = ?, rating=? WHERE codice = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, valutazione.getDescrizione());
			statement.setDate(2, java.sql.Date.valueOf(valutazione.getData()));
			statement.setInt(3, valutazione.getRating());
			statement.setLong(4, valutazione.getCodice());
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
	public void delete(Long codice) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM valutazione WHERE  codice_appuntamento = ?";
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

}
