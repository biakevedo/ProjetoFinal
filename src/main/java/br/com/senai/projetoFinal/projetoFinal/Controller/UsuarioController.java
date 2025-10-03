package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.UsuarioService;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.CadastrarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Controller de Usuario", description = "Metodos de Usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os clientes",
            description = "Lista todos os clientes sem nenhuma restricao"
    )
    public ResponseEntity<List<Usuario>> listarUsuario(){
        // 1. Pegar a lista de clientes
        List<Usuario> clientes = usuarioService.listarTodos();
        return ResponseEntity.ok(clientes);

    }

    @PostMapping
    public ResponseEntity<CadastrarUsuarioDTO> cadastrar (@RequestBody CadastrarUsuarioDTO usuario
    )

    {

        // 1. Tentar cadastrar o cliente
        usuarioService.cadastrarUsuario(usuario);


        // Codigo 200 - OK
        //return ResponseEntity.ok(usuario);

        // Codigo 201 - CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);


    }

    // Buscar Cliente por id
    // GET, POST, PUT, DELETE
    @GetMapping("/{id}")
    // Path Variable -> Recebe um valor no LINK
    // Request Body -> Recebe dados pelo corpo
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Integer id) {
        // 1. Procurar e guardar o Cliente
        Usuario cliente = usuarioService.buscarPorId(id);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario" + id + " nao encontrado!");

        }

        return ResponseEntity.ok(cliente);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Integer id) {
        // 1. Verifico se o cliente existe
        Usuario cliente = usuarioService.deletarUsuario(id);

        // 2. Se não existir retorno erro
        if (cliente == null) {
            return ResponseEntity.status(404)
                    .body("Usuario " + id + " não encontrado!");
        }

        // 3. Se existir, retorno ok
        return ResponseEntity.ok(cliente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Integer id, @RequestBody Usuario usuario) {

        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);

        if (usuarioAtualizado == null) {
            return ResponseEntity.status(404).body("cliente nao encontrado!");

        }

        return ResponseEntity.ok(usuarioAtualizado);

    }


}



