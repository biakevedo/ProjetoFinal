package br.com.senai.projetoFinal.projetoFinal.Controller;

import br.com.senai.projetoFinal.projetoFinal.Service.TagService;
import br.com.senai.projetoFinal.projetoFinal.model.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<Tag> criaTags(@RequestBody Tag t) {
        Tag tag = tagService.cadastraTag(t);
       return ResponseEntity.ok().body(tag);
    }

    @GetMapping
    @Operation(
            summary = "Busca uma tag pelo nome"
    )
    public ResponseEntity<Tag> filtaTag(String nome) {
        Tag tag = tagService.buscaTag(nome);
        return ResponseEntity.ok().body(tag);
    }

    @GetMapping
    @Operation(
            summary = "Busca usuario por email"
    )
    public ResponseEntity<List<Tag>> findByUsuarioEmail(String email) {
        List tag = tagService.findByUsuarioEmail(email);
        return ResponseEntity.ok().body(tag);
    }

}
