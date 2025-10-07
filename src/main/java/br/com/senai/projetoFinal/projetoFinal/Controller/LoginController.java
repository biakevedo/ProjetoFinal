package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

// @RestController: Marca a classe como um controller que lida com requisições REST.
@RestController
// @RequestMapping: Define o prefixo da URL para todos os endpoints neste controller.
@RequestMapping("/api/auth")
public class LoginController {

    // 1. Injetamos os "motores" que configuramos na SecurityConfiguration.
    private final AuthenticationManager authenticationManager; // O "Gerente de Autenticação".
    private final JwtEncoder jwtEncoder; // A "Máquina de Criar Tokens".

    // Injeção de dependência via construtor.
    public LoginController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    // @PostMapping: Mapeia este método para requisições do tipo POST na URL do controller
    // (ou seja, /api/auth).
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // @RequestBody: Pega o corpo da requisição (JSON) e o converte para um objeto LoginRequest.

        // --- ETAPA 1: AUTENTICAÇÃO ---

        // 2. Cria um "pedido de autenticação" com o email e senha que o usuário enviou.
        // Neste ponto, as credenciais ainda não são confiáveis.
        var authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());

        // 3. Entrega o pedido ao Gerente. Ele usará nosso UserDetailsServiceImpl e PasswordEncoder
        // para verificar se o usuário existe e se a senha está correta.
        // SE A AUTENTICAÇÃO FALHAR, uma exceção será lançada aqui e o método irá parar.
        Authentication auth = authenticationManager.authenticate(authToken);

        // --- ETAPA 2: GERAÇÃO DO TOKEN (só executa se a autenticação foi bem-sucedida) ---

        // 4. Pega o momento atual para definir a data de criação e expiração do token.
        Instant now = Instant.now();
        long validade = 3600L; // 1 Hora em segundos.

        // 5. Constrói o "payload" (o conteúdo) do token.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("urbanswift-api") // Quem emitiu o token.
                .issuedAt(now) // Quando foi emitido.
                .expiresAt(now.plusSeconds(validade)) // Quando expira.
                .subject(auth.getName()) // A quem o token pertence (o email do usuário).
                .claim("roles", auth.getAuthorities()) // Informações extras, como os perfis do usuário.
                .build();

        // 6. Define o cabeçalho do token, especificando o algoritmo de assinatura.
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

        // 7. Usa a "Máquina de Criar Tokens" para gerar a string final, combinando o cabeçalho
        // e o payload, e assinando tudo com nossa chave secreta.
        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

        // 8. Retorna o token gerado para o cliente com um status 200 OK.
        return ResponseEntity.ok(token);
    }
}