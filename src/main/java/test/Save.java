package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Save {
	
	public void method() {
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "postgres";

			Connection connection = DriverManager.getConnection(url, username, password);

			Statement statement = connection.createStatement();

			String query = "SELECT * FROM test	";
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			

			// Utilise la connexion pour exécuter des requêtes

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	

}
