package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

	private DataSource dataSource;

	public LoginDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean accessoClienti(String mail, String pass) {
		Connection connection = dataSource.getConnection();
		boolean stato = false;
		try {
			
			String query = "select * from Cliente where mail=? and password=? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setString(2, pass);

			ResultSet rs = statement.executeQuery();
			stato = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		return stato;
	}
	
	public boolean accessoProfessionisti(String mail, String pass) {
		Connection connection = dataSource.getConnection();
		boolean stato = false;
		try {
			
			String query = "select * from Professionista where mail=? and password=? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			statement.setString(2, pass);

			ResultSet rs = statement.executeQuery();
			stato = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		return stato;
	}
}
