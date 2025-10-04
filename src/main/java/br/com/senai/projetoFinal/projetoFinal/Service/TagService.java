package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.TagRepository;
import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import br.com.senai.projetoFinal.projetoFinal.dto.tag.CriaTagDTO;
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

    public CriaTagDTO cadastraTag(CriaTagDTO tag) {
        Integer usuarioId = tag.getIdUsuario();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        TagModel novaTag = new TagModel();

        novaTag.setNome(tag.getNomeTag());
        novaTag.setUsuario(usuario);

        tagRepository.save(novaTag);

        return tag;
    }

    public List<CriaTagDTO> buscaTag(String tagNome) {
        List<TagModel> tags = tagRepository.findByNomeContainingIgnoreCase(tagNome);
        List<CriaTagDTO> dtos = tags.stream()
                .map(tag -> new CriaTagDTO(tag.getNome(), tag.getId()))
                .collect(Collectors.toList());
        return dtos;
    }

    public List<CriaTagDTO> findByUsuarioId(Integer usuarioId){
        List<TagModel> usuarios = tagRepository.findByUsuarioId(usuarioId);

        List<CriaTagDTO> dtos = usuarios.stream()
                .map(usuario -> new CriaTagDTO(usuario.getNome(), usuario.getId()))
                .collect(Collectors.toList());
        return dtos;
    }
}
