package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import objets.CasierTablePojo;

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
	public Map<String, String> testToDb() {
		
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

			connection.close();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "erreur");
			return response;
		}
	}
	
	@GetMapping("/getList")
	public List<CasierTablePojo> getTableRappel() {
		
		List<CasierTablePojo> response = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "postgres";

			Connection connection = DriverManager.getConnection(url, username, password);

			Statement statement = connection.createStatement();

			String query = "SELECT * FROM tablerappel ";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				CasierTablePojo elt = new CasierTablePojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
				response.add(elt);
			}

			connection.close();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		}
	}
	
	@PostMapping("/firstsave")
	public void firstSave(@RequestBody List<CasierTablePojo> tableRappel) {
		
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "postgres";

			Connection connection = DriverManager.getConnection(url, username, password);

			String sql = "INSERT INTO tablerappel (numerocasier, contenucasier, nomliste) VALUES (?, ?, ?)";
			// Création d'un PreparedStatement avec la requête SQL
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            for(CasierTablePojo casier : tableRappel) {
            	// Ajouter plusieurs enregistrements au batch
                preparedStatement.setInt(1, casier.getNumeroCasier());
                preparedStatement.setString(2, casier.getContenuCasier());
                preparedStatement.setString(3, "Table par défaut");
                preparedStatement.addBatch();
            }
            
            // Exécution du batch
            int[] rowsAffected = preparedStatement.executeBatch();
            
            // Affichage du nombre de lignes insérées pour chaque instruction
            for (int rows : rowsAffected) {
                System.out.println(rows + " ligne(s) insérée(s) avec succès.");
            }
            
            // Fermeture des ressources
            preparedStatement.close();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
