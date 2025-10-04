package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.TagRepository;
import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import br.com.senai.projetoFinal.projetoFinal.dto.tag.RetornoTagCreateDTO;
import br.com.senai.projetoFinal.projetoFinal.model.TagModel;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final UsuarioRepository usuarioRepository;

    public TagService(TagRepository tagRepository, UsuarioRepository usuarioRepository) {
        this.tagRepository = tagRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public RetornoTagCreateDTO cadastraTag(RetornoTagCreateDTO tag) {
        Integer usuarioId = tag.getIdUsuario().getId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        TagModel novaTag = new TagModel();

        novaTag.setNome(tag.getNomeTag());
        novaTag.setUsuario(usuario);

        tagRepository.save(novaTag);

        return tag;
    }

    public List<RetornoTagCreateDTO> buscaTag(String tagNome) {
        List<TagModel> tags = tagRepository.findByNomeContainingIgnoreCase(tagNome);
        List<RetornoTagCreateDTO> dtos = tags.stream()
                .map(tag -> new RetornoTagCreateDTO(tag.getNome(), tag.getUsuario()))
                .collect(Collectors.toList());
        return dtos;
    }

    public List<RetornoTagCreateDTO> findByUsuarioId(Integer usuarioId){
        List<TagModel> usuarios = tagRepository.findByUsuarioId(usuarioId);

        List<RetornoTagCreateDTO> dtos = usuarios.stream()
                .map(usuario -> new RetornoTagCreateDTO(usuario.getNome(), usuario.getUsuario()))
                .collect(Collectors.toList());
        return dtos;
    }
}
