package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.CadastrarUsuarioDTO;
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

    public CadastrarUsuarioDTO cadastrarUsuario(CadastrarUsuarioDTO cl) {
        // 2. Cria uma nova instância da ENTIDADE 'Tag', que será salva.
        Usuario novoUsuario = new  Usuario();

        // 3. Mapeia os dados da DTO e da entidade buscada para a nova entidade.
        novoUsuario.setNomeCompleto(cl.getNomeCompleto());
        novoUsuario.setSenha(cl.getSenha());
        novoUsuario.setDataCadastro(cl.getDataCadastro);// Associa o usuário completo que buscamos

        // 4. Salva a entidade preenchida no banco.
        usuarioRepository.save(novoUsuario);

        // 5. Retorna a DTO original para confirmar os dados que foram enviados.
        return cl;

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
