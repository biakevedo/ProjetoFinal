package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.CadastrarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.ListarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public CadastrarUsuarioDTO cadastrarUsuario(CadastrarUsuarioDTO cl) {
        // 2. Cria uma nova instância da ENTIDADE 'Tag', que será salva.
        Usuario novoUsuario = new  Usuario();

        novoUsuario.setNomeCompleto(cl.getNomeCompleto());
        novoUsuario.setEmail(cl.getEmail());
        novoUsuario.setSenha(cl.getSenha());
        novoUsuario.setDataCadastro(OffsetDateTime.now());// Associa o usuário completo que buscamos

        usuarioRepository.save(novoUsuario);

        return cl;
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<ListarUsuarioDTO> listarTodosDTO () {
        List<Usuario> tags = usuarioRepository.findAll();

        return tags.stream()
                .map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    private ListarUsuarioDTO converterParaListagemDTO (Usuario usuario) {
        ListarUsuarioDTO dto = new ListarUsuarioDTO();

        dto.setEmail(usuario.getEmail());
        dto.setNomeCompleto(usuario.getNomeCompleto());

        return dto;
    }

    public Usuario deletarUsuario(Integer id) {
        Usuario usuario = buscarPorId(id);

        if (usuario == null) {
            return null;
        }
        usuarioRepository.delete(usuario);
        return usuario;
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuarioNovo) {
        // 1. Procurar quem eu quero atualizar
        Usuario usuarioAntigo = buscarPorId(id);

        if (usuarioAntigo == null) {
            return null;
        }

        usuarioAntigo.setEmail(usuarioNovo.getEmail());
        usuarioAntigo.setDataCadastro(usuarioNovo.getDataCadastro());
        usuarioAntigo.setDataAlteracao(usuarioNovo.getDataAlteracao());

        return usuarioRepository.save(usuarioAntigo);
    }
}
