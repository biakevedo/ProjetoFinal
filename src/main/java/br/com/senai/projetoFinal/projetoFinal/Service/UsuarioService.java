package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();

    }

    public Usuario cadastrarUsuario(Usuario cl) {
        return usuarioRepository.save(cl);

    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);

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

        // 2. Se eu não achar, retorno nulo
        if (usuarioAntigo == null) {
            return null;

        }

        usuarioAntigo.setSenha(usuarioNovo.getSenha());
        usuarioAntigo.setEmail(usuarioNovo.getEmail());
        usuarioAntigo.setDataCadastro(usuarioNovo.getDataCadastro());
        usuarioAntigo.setDataAlteracao(usuarioNovo.getDataAlteracao());
        return usuarioRepository.save(usuarioAntigo);

    }
}
