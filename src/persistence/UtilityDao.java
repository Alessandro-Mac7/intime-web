package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilityDao {
	private DataSource dataSource;

	public UtilityDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void eliminaDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "drop SEQUENCE if EXISTS sequenza_id;"
					+ "drop table if EXISTS appuntamento CASCADE;"
					+ "drop table if EXISTS valutazione CASCADE;"
					+ "drop table if EXISTS prenotazione CASCADE;"
					+ "drop table if EXISTS cliente;"
					+ "drop table if EXISTS professionista;";
			PreparedStatement statement = connection.prepareStatement(delete);
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

	public void creaDatabase() {
		Connection connection = dataSource.getConnection();
		try {
			String create = "create SEQUENCE sequenza_id;"
					+ "create table cliente ( mail VARCHAR(255) primary key, password VARCHAR(255) NOT NULL, nome VARCHAR(255) NOT NULL, cognome VARCHAR(255) NOT NULL, telefono VARCHAR(255));"
					+ "create table professionista ( mail VARCHAR(255) primary key, password VARCHAR(255) NOT NULL, nome VARCHAR(255) NOT NULL, cognome VARCHAR(255) NOT NULL, professione VARCHAR(255) NOT NULL, telefono VARCHAR(255), descrizione TEXT, sito VARCHAR(255), indirizzo VARCHAR(255));"
					+ "create table appuntamento ( codice BIGINT primary key, data DATE NOT NULL, ora_inizio TIME NOT NULL, ora_fine TIME NOT NULL, descrizione TEXT, numero_clienti INT, data_creazione DATE NOT NULL, mail_professionista VARCHAR(255) REFERENCES professionista(mail) );"		
					+ "create table valutazione ( codice BIGINT primary key, descrizione VARCHAR(255) NOT NULL, data DATE, rating INT, mail_cliente VARCHAR(255) REFERENCES cliente(mail), codice_appuntamento BIGINT REFERENCES appuntamento(codice) );"
					+ "create table prenotazione ( data_prenotazione DATE NOT NULL, mail_cliente VARCHAR(255) REFERENCES cliente(mail) ON DELETE CASCADE, codice_appuntamento BIGINT REFERENCES appuntamento(codice) ON DELETE CASCADE, primary key (mail_cliente,codice_appuntamento) );";
			PreparedStatement statement = connection.prepareStatement(create);
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