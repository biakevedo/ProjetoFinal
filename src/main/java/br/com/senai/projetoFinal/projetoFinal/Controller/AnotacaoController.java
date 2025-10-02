package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.AnotacaoService;
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
@GetMapping
@Operation(
                summary = "Lista todas as anotações",
                description = "Lista todas as anotações sem nenhuma restrição"
        )
        public ResponseEntity<List<Anotacao>> listarAnotacoes() {
            List<Anotacao> anotacoes = anotacaoService.listarTodos();
            return ResponseEntity.ok(anotacoes);
        }

@PostMapping
        public ResponseEntity<Anotacao> cadastrarAnotacao(@RequestBody Anotacao anotacao) {
            anotacaoService.cadastrarAnotacao(anotacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(anotacao);
        }

@GetMapping("/{id}")
        public ResponseEntity<?> buscarAnotacaoPorId(@PathVariable Integer id) {
            Anotacao anotacao = anotacaoService.buscarPorId(id);

            if (anotacao == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Anotação com ID " + id + " não encontrada!");
            }

            return ResponseEntity.ok(anotacao);
        }

@DeleteMapping("/{id}")
        public ResponseEntity<?> deletarAnotacao(@PathVariable Integer id) {
            Anotacao anotacao = anotacaoService.deletarAnotacao(id);

            if (anotacao == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Anotação com ID " + id + " não encontrada!");
            }

            return ResponseEntity.ok(anotacao);
        }

@PutMapping("/{id}")
        public ResponseEntity<?> atualizarAnotacao(@PathVariable Integer id, @RequestBody Anotacao anotacao) {
            Anotacao anotacaoAtualizada = anotacaoService.atualizarAnotacao(id, anotacao);

            if (anotacaoAtualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Anotação com ID " + id + " não encontrada!");
            }

            return ResponseEntity.ok(anotacaoAtualizada);
        }
    }

