package com.tempolivre.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		// Carregar o .env
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();

		// Mapear e definir as variaveis do sistema
		Map<String, Object> props = new HashMap<>();
		dotenv.entries().forEach(dotenvEntry -> {
			String key = dotenvEntry.getKey();
			String value = dotenvEntry.getValue();
			System.setProperty(key, value);
			props.put(key, value);
		});

		// Iniciar o Spring com as variaveis carregadas

		SpringApplication app = new SpringApplication(ApiApplication.class);
		app.setDefaultProperties(props);
		app.run(args);
	}

}
