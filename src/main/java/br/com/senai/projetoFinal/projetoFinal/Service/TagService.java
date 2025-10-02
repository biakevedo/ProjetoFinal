package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.TagRepository;
import br.com.senai.projetoFinal.projetoFinal.dto.tag.RetornoTagDTO;
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

    public RetornoTagDTO cadastraTag(TagModel tag) {
        return tagRepository.save(tag);
    }

    public List<RetornoTagDTO> buscaTag(String tagNome) {
       return tagRepository.findByNomeContainingIgnoreCase(tagNome);
    }

    public List<TagModel> findByUsuarioId(Integer usuarioId){
        return tagRepository.findByUsuarioId(usuarioId);
    }
}
