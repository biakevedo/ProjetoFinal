package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.TagRepository;
import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import br.com.senai.projetoFinal.projetoFinal.dto.tag.RetornoTagCreateDTO;
import br.com.senai.projetoFinal.projetoFinal.model.TagModel;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final UsuarioRepository usuarioRepository;

    public TagService(TagRepository tagRepository, UsuarioRepository usuarioRepository) {
        this.tagRepository = tagRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public RetornoTagCreateDTO cadastraTag(RetornoTagCreateDTO tag) {
        Integer usuarioId = tag.getIdUsuario();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        TagModel novaTag = new TagModel();

        novaTag.setNome(tag.getNomeTag());
        novaTag.setUsuario(usuario);

        tagRepository.save(tag);

        return tag;
    }

    public List<RetornoTagCreateDTO> buscaTag(String tagNome) {
       return tagRepository.findByNomeContainingIgnoreCase(tagNome);
    }

    public List<TagModel> findByUsuarioId(Integer usuarioId){
        return tagRepository.findByUsuarioId(usuarioId);
    }
}
