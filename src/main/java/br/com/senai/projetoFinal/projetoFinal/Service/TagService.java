package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.TagRepository;
import br.com.senai.projetoFinal.projetoFinal.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag cadastraTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag buscaTag(String tagNome) {
       return tagRepository.findByTagNome(tagNome);
    }

    public List<Tag> findByUsuarioEmail(String email){
        return tagRepository.findByUsuarioEmail(email);
    }
}
