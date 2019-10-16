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
import model.Professionista;
import model.Valutazione;
import persistence.dao.ProfessionistaDao;

public class ProfessionistaDaoJDBC implements ProfessionistaDao {

	private DataSource dataSource;

	public ProfessionistaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Professionista professionista) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "INSERT INTO professionista (mail,password,nome,cognome,professione,telefono,descrizione,sito,indirizzo) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, professionista.getMail());
			statement.setString(2, professionista.getPassword());
			statement.setString(3, professionista.getNome());
			statement.setString(4, professionista.getCognome());
			statement.setString(5, professionista.getProfessione());
			statement.setString(6, professionista.getTelefono());
			statement.setString(7, professionista.getDescrizione());
			statement.setString(8, professionista.getSito());
			statement.setString(9, professionista.getIndirizzo());
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
	public List<Professionista> findByCognome(String cognome) {
		Connection connection = this.dataSource.getConnection();
		List<Professionista> professionisti = new ArrayList<>();
		try {
			Professionista professionista;
			String query = "SELECT * FROM professionista WHERE cognome = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cognome);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				professionista = new Professionista();
				professionista.setMail(result.getString("mail"));
				professionista.setPassword(result.getString("password"));
				professionista.setNome(result.getString("nome"));
				professionista.setCognome(result.getString("cognome"));
				professionista.setProfessione(result.getString("professione"));
				professionista.setTelefono(result.getString("telefono"));
				professionista.setDescrizione(result.getString("descrizione"));
				professionista.setSito(result.getString("sito"));
				professionista.setIndirizzo(result.getString("indirizzo"));

				professionisti.add(professionista);
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
		return professionisti;
	}

	@Override
	public Professionista findByMail(String mail) {
		Professionista professionista = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM professionista WHERE mail = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				professionista = new Professionista();
				professionista.setMail(result.getString("mail"));
				professionista.setPassword(result.getString("password"));
				professionista.setNome(result.getString("nome"));
				professionista.setCognome(result.getString("cognome"));
				professionista.setProfessione(result.getString("professione"));
				professionista.setTelefono(result.getString("telefono"));
				professionista.setDescrizione(result.getString("descrizione"));
				professionista.setSito(result.getString("sito"));
				professionista.setIndirizzo(result.getString("indirizzo"));

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
		return professionista;
	}

	@Override
	public List<Professionista> findByProfessione(String professione) {
		Connection connection = this.dataSource.getConnection();
		List<Professionista> professionisti = new ArrayList<>();
		try {
			Professionista professionista;
			String query = "SELECT * FROM professionista WHERE professione = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, professione);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				professionista = new Professionista();
				professionista.setMail(result.getString("mail"));
				professionista.setPassword(result.getString("password"));
				professionista.setNome(result.getString("nome"));
				professionista.setCognome(result.getString("cognome"));
				professionista.setProfessione(result.getString("professione"));
				professionista.setTelefono(result.getString("telefono"));
				professionista.setDescrizione(result.getString("descrizione"));
				professionista.setSito(result.getString("sito"));
				professionista.setIndirizzo(result.getString("indirizzo"));

				professionisti.add(professionista);
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
		return professionisti;
	}

	@Override
	public List<Professionista> findByCognomeProfessione(String chiave) {
		Connection connection = this.dataSource.getConnection();
		List<Professionista> professionisti = new ArrayList<>();
		try {
			Professionista professionista;
			String query = "SELECT * FROM professionista WHERE upper(cognome) = ? or upper(professione) = ? or upper(nome) = ? or upper(cognome) like ? or upper(professione) like ? or upper(nome) like ? order by cognome;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, chiave);
			statement.setString(2, chiave);
			statement.setString(3, chiave);
			statement.setString(4, chiave + "%");
			statement.setString(5, chiave + "%");
			statement.setString(6, chiave + "%");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				professionista = new Professionista();
				professionista.setMail(result.getString("mail"));
				professionista.setPassword(result.getString("password"));
				professionista.setNome(result.getString("nome"));
				professionista.setCognome(result.getString("cognome"));
				professionista.setProfessione(result.getString("professione"));
				professionista.setTelefono(result.getString("telefono"));
				professionista.setDescrizione(result.getString("descrizione"));
				professionista.setSito(result.getString("sito"));
				professionista.setIndirizzo(result.getString("indirizzo"));

				professionisti.add(professionista);
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
		return professionisti;
	}

	@Override
	public List<Professionista> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Professionista> professionisti = new ArrayList<>();
		try {
			Professionista professionista;
			String query = "SELECT * FROM professionista";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				professionista = new Professionista();
				professionista.setMail(result.getString("mail"));
				professionista.setPassword(result.getString("password"));
				professionista.setNome(result.getString("nome"));
				professionista.setCognome(result.getString("cognome"));
				professionista.setProfessione(result.getString("professione"));
				professionista.setTelefono(result.getString("telefono"));
				professionista.setDescrizione(result.getString("descrizione"));
				professionista.setSito(result.getString("sito"));
				professionista.setIndirizzo(result.getString("indirizzo"));

				professionisti.add(professionista);
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
		return professionisti;
	}

	@Override
	public void update(Professionista professionista) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "UPDATE professionista SET password = ?, nome = ?, cognome = ?, professione = ?, telefono = ?, descrizione=?, sito=?, indirizzo=? WHERE mail = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, professionista.getPassword());
			statement.setString(2, professionista.getNome());
			statement.setString(3, professionista.getCognome());
			statement.setString(4, professionista.getProfessione());
			statement.setString(5, professionista.getTelefono());
			statement.setString(6, professionista.getDescrizione());
			statement.setString(7, professionista.getSito());
			statement.setString(8, professionista.getIndirizzo());
			statement.setString(9, professionista.getMail());

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
			String delete = "delete FROM professionista WHERE mail = ? ";
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
	public List<Cliente> findTuttiPrenotati(String mail) {
		List<Cliente> clientiPrenotati = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Cliente cliente;
			String query = "select * from cliente c where c.mail in(select pren.mail_cliente from prenotazione pren where pren.codice_appuntamento in(select codice from appuntamento a,professionista pro where a.mail_professionista=pro.mail and pro.mail=?));";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				cliente = new Cliente();
				cliente.setMail(result.getString("mail"));
				cliente.setPassword(result.getString("password"));
				cliente.setNome(result.getString("nome"));
				cliente.setCognome(result.getString("cognome"));
				cliente.setTelefono(result.getString("telefono"));
				clientiPrenotati.add(cliente);
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
		return clientiPrenotati;
	}

	@Override
	public List<Cliente> findTuttiPrenotatiSingoloAppuntamento(String mail, long codice) {
		List<Cliente> clientiPrenotati = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Cliente cliente;
			String query = "select * from cliente c where c.mail in(select pren.mail_cliente from prenotazione pren where pren.codice_appuntamento in(select codice from appuntamento a,professionista pro where a.mail_professionista=pro.mail and pro.mail=? and a.codice=?));";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setLong(2, codice);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				cliente = new Cliente();
				cliente.setMail(result.getString("mail"));
				cliente.setPassword(result.getString("password"));
				cliente.setNome(result.getString("nome"));
				cliente.setCognome(result.getString("cognome"));
				cliente.setTelefono(result.getString("telefono"));
				clientiPrenotati.add(cliente);
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
		return clientiPrenotati;
	}

	@Override
	public List<String> autocomplete() {
		List<String> suggerimenti = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select nome,cognome,professione from professionista;";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if (!suggerimenti.contains(result.getString("cognome")))
					suggerimenti.add(result.getString("cognome"));
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
		return suggerimenti;
	}

	@Override
	public List<Appuntamento> findAppuntamentiCreatiOggi(String mail) {
		List<Appuntamento> appuntamenti = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento where mail_professionista=? and data_creazione=?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
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
	public List<Valutazione> findRecensioni(String mail) {
		List<Valutazione> valutazioni = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Valutazione valutazione;
			String query = "select * from valutazione v,appuntamento a where v.codice_appuntamento=a.codice and a.mail_professionista=?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				valutazione = new Valutazione();
				valutazione.setCodice(result.getLong("codice"));
				valutazione.setDescrizione(result.getString("descrizione"));
				valutazione.setData((result.getDate("data")).toLocalDate());
				valutazione.setRating(result.getInt("rating"));
				valutazione.setMail_cliente(result.getString("mail_cliente"));
				valutazione.setCodice_appuntamento(result.getLong("codice_appuntamento"));
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
	public List<Valutazione> findRecensioniRecenti(String mail) {
		List<Valutazione> valutazioni = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Valutazione valutazione;
			String query = "select * from valutazione v,appuntamento a where v.codice_appuntamento=a.codice and a.mail_professionista=? and a.data>?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setDate(2, java.sql.Date.valueOf(LocalDate.now().minusWeeks(1)));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				valutazione = new Valutazione();
				valutazione.setCodice(result.getLong("codice"));
				valutazione.setDescrizione(result.getString("descrizione"));
				valutazione.setData((result.getDate("data")).toLocalDate());
				valutazione.setRating(result.getInt("rating"));
				valutazione.setMail_cliente(result.getString("mail_cliente"));
				valutazione.setCodice_appuntamento(result.getLong("codice_appuntamento"));
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
	public List<Appuntamento> findAppuntamentiOdierni(String mail) {
		List<Appuntamento> appuntamenti = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento where mail_professionista=? and data=?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
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
	public int findMediaValutazioni(String mail) {
		int media = 0;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select avg(rating) as rating from appuntamento a,valutazione v where a.mail_professionista=? and v.codice_appuntamento=a.codice;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				media = result.getInt("rating");
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
		return media;
	}

	@Override
	public int findNumeroValutazioniRicevute(String mail) {
		int numValutazioni = 0;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select count(*) as numero from valutazione v,appuntamento a where v.codice_appuntamento=a.codice and a.mail_professionista=?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				numValutazioni = result.getInt("numero");
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
		return numValutazioni;
	}

	@Override
	public int findRating(String mail, int stelle) {
		int numStelle = 0;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select count(*) as numero from valutazione v,appuntamento a where v.codice_appuntamento=a.codice and a.mail_professionista=? and v.rating=?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setInt(2, stelle);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				numStelle = result.getInt("numero");
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
		return numStelle;
	}

	@Override
	public List<Appuntamento> controlloInserimento(String mail, LocalDate data, LocalTime inizio, LocalTime fine) {
		List<Appuntamento> appuntamenti = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "select * from appuntamento where mail_professionista=? and data=? and ora_inizio=?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setDate(2, java.sql.Date.valueOf(data));
			statement.setTime(3, java.sql.Time.valueOf((inizio)));
			// statement.setTime(4, java.sql.Time.valueOf((fine)));
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
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
	public Map<String, Integer> findTopClienti(String mail) {
		Map<String, Integer> map = new HashMap<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select c.mail as mail, count(c.mail) as numero from cliente c, prenotazione p, appuntamento a where c.mail=p.mail_cliente and p.codice_appuntamento=a.codice and a.mail_professionista=? group by c.mail limit 10;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String chiave = result.getString("mail");
				int valore = result.getInt("numero");
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
	public List<Appuntamento> findTuttiAppuntamentiPrenotatiSingoloCliente(String mail_cliente,
			String mail_professionista) {
		List<Appuntamento> appuntamenti = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			Appuntamento appuntamento;
			String query = "select * from prenotazione p,appuntamento a where p.mail_cliente=? and a.mail_professionista=? and p.codice_appuntamento=a.codice;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail_cliente);
			statement.setString(2, mail_professionista);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				appuntamento = new Appuntamento();
				appuntamento.setCodice(result.getLong("codice"));
				appuntamento.setData((result.getDate("data")).toLocalDate());
				appuntamento.setOra_inizio((result.getTime("ora_inizio")).toLocalTime());
				appuntamento.setOra_fine((result.getTime("ora_fine")).toLocalTime());
				appuntamento.setMail_professionista(result.getString("mail_professionista"));
				appuntamento.setDescrizione(result.getString("descrizione"));
				appuntamento.setNumero_clienti(result.getInt("numero_clienti"));
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
	public List<Valutazione> findTutteValutazioniSingoloCliente(String mail_cliente, String mail_professionista) {
		List<Valutazione> valutazioni = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select * from valutazione v,appuntamento a where a.codice=v.codice_appuntamento and v.mail_cliente=? and a.mail_professionista=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail_cliente);
			statement.setString(2, mail_professionista);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Valutazione valutazione = new Valutazione();
				valutazione.setCodice_appuntamento(result.getLong("codice_appuntamento"));
				valutazione.setDescrizione(result.getString("descrizione"));
				valutazione.setData((result.getDate("data")).toLocalDate());
				valutazione.setRating(result.getInt("rating"));
				valutazione.setCodice(result.getLong("codice"));
				valutazione.setMail_cliente(result.getString("mail_cliente"));
				
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

}
