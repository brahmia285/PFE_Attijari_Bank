package com.satisfaction;
import com.satisfaction.entity.Enquete;
import com.satisfaction.repository.EnqueteRepository;





import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@SpringBootApplication
public class SatisfactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatisfactionApplication.class, args);
	}

	@Bean
	CommandLineRunner init(EnqueteRepository repo, DataSource dataSource) {
		return args -> {
			try (Connection conn = dataSource.getConnection()) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("\n=== CONFIGURATION ORACLE ===");
				System.out.println("URL: " + meta.getURL());
				System.out.println("User: " + meta.getUserName());
				System.out.println("Database: " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion());
				System.out.println("Driver: " + meta.getDriverName() + " " + meta.getDriverVersion());
				System.out.println("Schema: " + conn.getSchema() + "\n");
			}

			// Initialisation des données de test
			if (repo.count() == 0) {
				repo.save(new Enquete(null, "Satisfaction Client", "Enquête trimestrielle"));
				repo.save(new Enquete(null, "Feedback Produit", "Évaluation du nouveau produit"));
				System.out.println("2 enquêtes de test créées dans le schéma SYSTEM");
			}
		};
	}
}
