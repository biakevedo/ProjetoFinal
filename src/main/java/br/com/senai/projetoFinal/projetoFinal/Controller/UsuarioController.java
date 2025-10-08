package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.UsuarioService;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.CadastrarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.GetByIdUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.ListarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.ResetSenhaDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Tag(name = "Controller de Usuario", description = "Metodos de Usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os usuarios",
            description = "Lista todos os usuarios sem nenhuma restricao"
    )
    public ResponseEntity<List<ListarUsuarioDTO>> listar() {
        List<ListarUsuarioDTO> usuario = usuarioService.listarTodosDTO();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<CadastrarUsuarioDTO> cadastrar(@RequestBody CadastrarUsuarioDTO usuario)
    {
        usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario cliente = usuarioService.buscarPorId(id);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario" + id + " nao encontrado!");
        }
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Integer id) {
        GetByIdUsuarioDTO cliente = usuarioService.deletarUsuario(id);

        if (cliente == null) {
            return ResponseEntity.status(404)
                    .body("Usuario " + id + " não encontrado!");
        }
        return ResponseEntity.ok(cliente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario) {

        ListarUsuarioDTO usuarioAtualizado = usuarioService.atualizarUsuario(usuario);

        if (usuarioAtualizado == null) {
            return ResponseEntity.status(404).body("cliente nao encontrado!");
        }
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ResetSenhaDTO resetarSenhaDTO) {
        usuarioService.processForgotPassword(resetarSenhaDTO.getEmail());
        return ResponseEntity.ok("Se um usuário com este e-mail existir, uma nova senha será enviada.");
    }

}



