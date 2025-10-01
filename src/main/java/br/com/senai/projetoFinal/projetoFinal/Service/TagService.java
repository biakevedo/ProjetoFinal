package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.TagRepository;
import br.com.senai.projetoFinal.projetoFinal.model.TagModel;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagModel cadastraTag(TagModel tag) {
        return tagRepository.save(tag);
    }

    public List<TagModel> buscaTag(String tagNome) {
       return tagRepository.findByNomeContainingIgnoreCase(tagNome);
    }

    public List<TagModel> findByIdUsuario(Usuario usuarioId){
        return tagRepository.findByIdUsuario(usuarioId);
    }
}
