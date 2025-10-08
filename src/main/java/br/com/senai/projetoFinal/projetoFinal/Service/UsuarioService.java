package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.CadastrarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.ListarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.GetByIdUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;

    }

    public CadastrarUsuarioDTO cadastrarUsuario(CadastrarUsuarioDTO cl) {

        Usuario novoUsuario = new Usuario();

        novoUsuario.setNomeCompleto(cl.getNomeCompleto());
        novoUsuario.setEmail(cl.getEmail());
        String senhaCriptografada = passwordEncoder.encode(cl.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setDataCadastro(OffsetDateTime.now());// Associa o usuário completo que buscamos

        usuarioRepository.save(novoUsuario);

        return cl;
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com id " + id));
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

    public GetByIdUsuarioDTO deletarUsuario(Integer id) {
        Usuario usuario = buscarPorId(id);

        if (usuario == null) {
            return null;
        }
        GetByIdUsuarioDTO dto = new GetByIdUsuarioDTO(usuario.getEmail(), usuario.getNomeCompleto());

         usuarioRepository.delete(usuario);

        return dto;
    }

    public ListarUsuarioDTO atualizarUsuario(Usuario usuarioNovo) {
        Usuario usuarioAntigo = buscarPorId(usuarioNovo.getId());

        if (usuarioAntigo == null) {
            return null;
        }

        usuarioAntigo.setEmail(usuarioNovo.getEmail());
        usuarioAntigo.setSenha(usuarioNovo.getSenha());
        usuarioAntigo.setDataAlteracao(OffsetDateTime.now());
        usuarioAntigo.setNomeCompleto(usuarioNovo.getNomeCompleto());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioAntigo);

        return converterParaListagemDTO(usuarioAtualizado);
    }


    public void processForgotPassword(String email) {
        usuarioRepository.findByEmail(email).ifPresent(usuario -> {
            String novaSenha = RandomStringUtils.randomAlphanumeric(10);
            String senhaCodificada = encoder.encode(novaSenha);
            usuario.setSenha(senhaCodificada);
            usuarioRepository.save(usuario);
            emailService.enviarEmail(usuario.getEmail(), novaSenha);
        });
    }
}
