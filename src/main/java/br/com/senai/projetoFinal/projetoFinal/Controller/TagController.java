package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.TagService;
import br.com.senai.projetoFinal.projetoFinal.model.TagModel;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<TagModel> criaTags(@RequestBody TagModel t) {
        TagModel tag = tagService.cadastraTag(t);
       return ResponseEntity.ok().body(tag);
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Busca uma tag pelo nome"
    )
    public ResponseEntity<?> filtraTag(String nome) {
        TagModel tag = tagService.buscaTag(nome);

        if (tag == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag nao encontrada");
        }
        return ResponseEntity.ok().body(tag);
    }

    @GetMapping
    @Operation(
            summary = "Busca usuario por email"
    )
    public ResponseEntity<List<TagModel>> findByIdUsuario(Usuario usuarioId) {
        List tag = tagService.findByIdUsuario(usuarioId);
        return ResponseEntity.ok().body(tag);
    }

}
