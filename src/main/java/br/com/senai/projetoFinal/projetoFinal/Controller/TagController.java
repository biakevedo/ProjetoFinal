package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.TagService;
import br.com.senai.projetoFinal.projetoFinal.dto.tag.CriaTagDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@Controller
@RequestMapping("api/tag")

@Tag(name = "Controller de Tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastra uma tag"
    )
    public ResponseEntity<?> criaTags(@RequestBody CriaTagDTO t) {
        CriaTagDTO tag = tagService.cadastraTag(t);

        if (tag == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar tag");
        }

        if (tag.getNomeTag() == null || tag.getNomeTag().isEmpty()) {
            throw new IllegalArgumentException("Nome da tag precisa ser informado");
        }

        if (tag.getIdUsuario() == null || tag.getIdUsuario() == 0) {
            throw new IllegalArgumentException("Necessario associar usuario com a tag");
        }

       return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Busca uma tag pelo nome"
    )
    public ResponseEntity<?> filtraTag(@Parameter String nome) {
        List<CriaTagDTO> tag = tagService.buscaTag(nome);

        if (tag.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag nao encontrada");
        }
        return ResponseEntity.ok().body(tag);
    }

    @GetMapping
    @Operation(
            summary = "Busca os usuarios dono da tag, usando usuarioId"
    )
    public ResponseEntity<?> findByUsuarioId(@Parameter Integer usuarioId) {
        List tag = tagService.findByUsuarioId(usuarioId);

        if (tag.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        return ResponseEntity.ok().body(tag);
    }

}
