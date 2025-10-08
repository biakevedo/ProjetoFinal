package br.com.senai.projetoFinal.projetoFinal;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
    private String jwtSecret = "Z97Y(Yotf%OsK.YyhrLkVG-iM7;8%q~}BD0DsR9fUA£Xiz'9";

    @Bean
    public JwtEncoder jwtEncoder() {

        var Secretkey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");

        return new NimbusJwtEncoder(new ImmutableSecret<>(Secretkey));

    }

    @Bean
    public JwtDecoder jwtDecoder() {

        var secretKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa a proteção CSRF, que não é necessária para APIs stateless.
                .csrf(csrf -> csrf.disable())
                // Configura a gestão de sessão para ser stateless (sem estado).
                // O servidor não guarda informações do usuário entre as requisições.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Permite TODAS as requisições para QUALQUER endpoint.
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}

