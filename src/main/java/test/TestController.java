package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Chemin de base pour toutes les URL de ce contrôleur
public class TestController {

	@GetMapping("/hello")
	public Map<String, String> hello() {
		Map<String, String> response = new HashMap<>();
		response.put("message", "Hello, world!");
		return response;
	}

	@GetMapping("/db")
	public Map<String, String> method() {
		
		Map<String, String> response = new HashMap<>();
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "postgres";

			Connection connection = DriverManager.getConnection(url, username, password);

			Statement statement = connection.createStatement();

			String query = "SELECT * FROM test	";
			ResultSet resultSet = statement.executeQuery(query);

			String output = "";

			while (resultSet.next()) {
				output = resultSet.getString(1);
				response.put("message", output);
			}

			// Utilise la connexion pour exécuter des requêtes

			connection.close();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "erreur");
			return response;
		}
	}

}
