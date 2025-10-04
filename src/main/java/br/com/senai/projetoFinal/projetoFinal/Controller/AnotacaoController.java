package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.AnotacaoService;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.CadastrarAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.ListarAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

        // ✅ Listar todas as anotações
        @GetMapping
        @Operation(summary = "Listar todas as anotações")
        public ResponseEntity<List<Anotacao>> listarAnotacoes() {
            List<Anotacao> anotacoes = anotacaoService.listarTodos();
            return ResponseEntity.ok(anotacoes);
        }

        // ✅ Buscar anotação por ID
        @GetMapping("/{id}")
        @Operation(summary = "Buscar anotação por ID")
        public ResponseEntity<Anotacao> buscarPorId(@PathVariable Integer id) {
            Anotacao anotacao = anotacaoService.buscarPorId(id);
            if (anotacao == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(anotacao);
        }

        // Cadastrar nova anotação
        @PostMapping
        @Operation(summary = "Cadastrar nova anotação")
        public ResponseEntity<CadastrarAnotacaoDTO> cadastrarAnotacao(@RequestBody CadastrarAnotacaoDTO dto) {
            CadastrarAnotacaoDTO nova = anotacaoService.cadastrarAnotacao(dto);
            return ResponseEntity.status(201).body(nova);
        }

        // Atualizar anotação existente
        @PutMapping("/{id}")
        @Operation(summary = "Atualizar anotação")
        public ResponseEntity<Anotacao> atualizarAnotacao(@PathVariable Integer id,
                                                          @RequestBody Anotacao novaAnotacao) {
            Anotacao atualizada = anotacaoService.atualizarAnotacao(id, novaAnotacao);
            if (atualizada == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(atualizada);
        }

        //  Deletar anotação
        @DeleteMapping("/{id}")
        @Operation(summary = "Deletar anotação")
        public ResponseEntity<Void> deletarAnotacao(@PathVariable Integer id) {
            Anotacao deletada = anotacaoService.deletarAnotacao(id);
            if (deletada == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        }
    // Em AnotacaoController.java
    @GetMapping("/{email}")
    public ResponseEntity<List<ListarAnotacaoDTO>> listarAnotacoesPorEmail(@PathVariable String email, Authentication authentication) {
        // 2. Anota o pedido e envia para a cozinha (Service)
        List<ListarAnotacaoDTO> anotacoesDTO = anotacaoService.listarAnotacoesPorEmail(email);

        // 3. Pega o prato pronto e entrega ao cliente com status 200 OK.
        return ResponseEntity.ok(anotacoesDTO);
    }
    }
