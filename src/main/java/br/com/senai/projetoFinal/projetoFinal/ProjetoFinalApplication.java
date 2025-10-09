package br.com.senai.projetoFinal.projetoFinal;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
		name = "bearerAuth", // 1. Um nome para referenciar este esquema de segurança.
		type = SecuritySchemeType.HTTP, // 2. O tipo de segurança. HTTP é usado para Bearer, Basic Auth, etc.
		scheme = "bearer", // 3. O esquema específico. "bearer" para JWT.
		bearerFormat = "JWT" // 4. Um "hint" para o formato do token.
)

public class ProjetoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalApplication.class, args);
	}

}
