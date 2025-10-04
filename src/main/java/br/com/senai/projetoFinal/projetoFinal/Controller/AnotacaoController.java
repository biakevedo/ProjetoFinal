package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.AnotacaoService;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.AnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.CadastrarAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.RetornoAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anotacao")
@Tag(name = "Controller de Anotação", description = "Métodos de Anotação")

public class AnotacaoController {

    private final AnotacaoService anotacaoService;

    public AnotacaoController(AnotacaoService anotacaoService) {
        this.anotacaoService = anotacaoService;
    }

    //  Listar todas as anotações
    @GetMapping
    public ResponseEntity<List<AnotacaoDTO>> listarAnotacoes() {
        List<AnotacaoDTO> anotacoes = anotacaoService.listarTodos();
        return ResponseEntity.ok(anotacoes);
    }

    // ✅ Buscar anotação por ID
    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> buscarPorId(@PathVariable Integer id) {
        AnotacaoDTO anotacao = anotacaoService.buscarPorId(id);
        if (anotacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(anotacao);
    }

    // ✅ Cadastrar nova anotação (recebe DTO)
    @PostMapping
    public ResponseEntity<AnotacaoDTO> cadastrarAnotacao(@RequestBody AnotacaoDTO anotacaoDTO) {
        AnotacaoDTO novaAnotacao = anotacaoService.cadastrarAnotacao(anotacaoDTO);
        return ResponseEntity.ok(novaAnotacao);
    }

    // ✅ Atualizar anotação
    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> atualizarAnotacao(@PathVariable Integer id,
                                                         @RequestBody AnotacaoDTO anotacaoDTO) {
        AnotacaoDTO anotacaoAtualizada = anotacaoService.atualizarAnotacao(id, anotacaoDTO);
        if (anotacaoAtualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(anotacaoAtualizada);
    }

    // ✅ Deletar anotação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnotacao(@PathVariable Integer id) {
        boolean deletado = anotacaoService.deletarAnotacao(id);
        if (!deletado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
