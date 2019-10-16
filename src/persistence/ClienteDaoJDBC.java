package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Appuntamento;
import model.Cliente;
import model.Valutazione;
import persistence.dao.ClienteDao;

public class ClienteDaoJDBC implements ClienteDao {

	private DataSource dataSource;

	public ClienteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Cliente cliente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "INSERT INTO cliente (mail,password,nome,cognome,telefono) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, cliente.getMail());
			statement.setString(2, cliente.getPassword());
			statement.setString(3, cliente.getNome());
			statement.setString(4, cliente.getCognome());
			statement.setString(5, cliente.getTelefono());

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
	public List<Cliente> findByCognome(String cognome) {
		Connection connection = this.dataSource.getConnection();
		List<Cliente> clienti = new ArrayList<>();
		try {
			Cliente cliente;
			String query = "SELECT * FROM cliente WHERE cognome = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cognome);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				cliente = new Cliente();
				cliente.setMail(result.getString("mail"));
				cliente.setPassword(result.getString("password"));
				cliente.setNome(result.getString("nome"));
				cliente.setCognome(result.getString("cognome"));
				cliente.setTelefono(result.getString("telefono"));
				clienti.add(cliente);
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
		return clienti;
	}

	@Override
	public Cliente findByMail(String mail) {
		Cliente cliente = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM cliente WHERE mail = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				cliente = new Cliente();
				cliente.setMail(result.getString("mail"));
				cliente.setPassword(result.getString("password"));
				cliente.setNome(result.getString("nome"));
				cliente.setCognome(result.getString("cognome"));
				cliente.setTelefono(result.getString("telefono"));

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
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Cliente> clienti = new ArrayList<>();
		try {
			Cliente cliente;
			String query = "SELECT * FROM cliente";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				cliente = new Cliente();
				cliente.setMail(result.getString("mail"));
				cliente.setPassword(result.getString("password"));
				cliente.setNome(result.getString("nome"));
				cliente.setCognome(result.getString("cognome"));
				cliente.setTelefono(result.getString("telefono"));

				clienti.add(cliente);
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
		return clienti;
	}

	@Override
	public void update(Cliente cliente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "UPDATE cliente SET password = ?, nome = ?, cognome = ?, telefono=? WHERE mail = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, cliente.getPassword());
			statement.setString(2, cliente.getNome());
			statement.setString(3, cliente.getCognome());
			statement.setString(4, cliente.getTelefono());
			statement.setString(5, cliente.getMail());

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
	public void delete(String mail) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM cliente WHERE mail = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, mail);
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
	public List<Appuntamento> findAppuntamentiPrenotati(String mail_professionista, String mail_cliente) {
		Connection connection = this.dataSource.getConnection();
		List<Appuntamento> appuntamenti = new ArrayList<>();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento where mail_professionista=? and codice in (select codice_appuntamento from prenotazione where mail_cliente=?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail_professionista);
			statement.setString(2, mail_cliente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
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
	public List<Appuntamento> findAppuntamentiPrenotabili(String mail_professionista, String mail_cliente,
			LocalDate data) {
		Connection connection = this.dataSource.getConnection();
		List<Appuntamento> appuntamenti = new ArrayList<>();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento a where a.mail_professionista=? and a.data>=? and a.codice not in (select codice_appuntamento from prenotazione where mail_cliente=?) and a.numero_clienti > (select count(*) from prenotazione p where a.codice=p.codice_appuntamento);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail_professionista);
			statement.setDate(2, java.sql.Date.valueOf(data));
			statement.setString(3, mail_cliente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
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
	public List<Appuntamento> findStoricoAppuntamenti(String mail) {

		Connection connection = this.dataSource.getConnection();
		List<Appuntamento> appuntamenti = new ArrayList<>();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento a where a.codice in(select codice_appuntamento from prenotazione p,cliente c where c.mail=p.mail_cliente and c.mail=?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
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
	public List<Appuntamento> findAppuntamentiValutabili(String mail) {
		Connection connection = this.dataSource.getConnection();
		List<Appuntamento> appuntamenti = new ArrayList<>();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento a where (a.data<? or(a.data=? and a.ora_fine<?)) and a.codice in(select codice_appuntamento from prenotazione p,cliente c where c.mail=p.mail_cliente and c.mail=? and p.codice_appuntamento not in (select v.codice_appuntamento from valutazione v));";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			statement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			statement.setTime(3, java.sql.Time.valueOf(LocalTime.now()));
			statement.setString(4, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
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
	public List<Appuntamento> findAppuntamentiFuturi(String mail) {
		Connection connection = this.dataSource.getConnection();
		List<Appuntamento> appuntamenti = new ArrayList<>();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento a where a.data>=? and a.codice in(select codice_appuntamento from prenotazione p,cliente c where c.mail=p.mail_cliente and c.mail=?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			statement.setString(2, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
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
	public List<Valutazione> findStoricoValutazioni(String mail) {
		Connection connection = this.dataSource.getConnection();
		List<Valutazione> valutazioni = new ArrayList<>();
		try {
			Valutazione valutazione;
			String query = "select * from valutazione where mail_cliente=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				valutazione = new Valutazione();
				valutazione.setCodice(result.getLong("codice"));
				valutazione.setData((result.getDate("data")).toLocalDate());
				valutazione.setDescrizione(result.getString("descrizione"));
				valutazione.setCodice_appuntamento(result.getLong("codice_appuntamento"));
				valutazione.setMail_cliente(result.getString("mail_cliente"));
				valutazione.setRating(result.getInt("rating"));
				valutazioni.add(valutazione);
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
		return valutazioni;
	}

	@Override
	public Map<String, Integer> findPercentualeAppuntamenti(String mail) {
		Connection connection = this.dataSource.getConnection();
		Map<String, Integer> map = new HashMap<>();

		try {
			String query = "select count(a.mail_professionista)as numero,a.mail_professionista as mail from appuntamento a,prenotazione p where p.codice_appuntamento=a.codice and p.mail_cliente=? group by a.mail_professionista;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String chiave=result.getString("mail");
				int valore=result.getInt("numero");
				map.put(chiave, valore);
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
		return map;
	}

	@Override
	public List<Appuntamento> findAppuntamentiOdierni(String mail) {
		Connection connection = this.dataSource.getConnection();
		List<Appuntamento> appuntamenti = new ArrayList<>();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento a, prenotazione p where a.data=? and p.mail_cliente=? and a.codice=p.codice_appuntamento;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			statement.setString(2, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
				appuntamento.setData_creazione(result.getDate("data_creazione").toLocalDate());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
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

}