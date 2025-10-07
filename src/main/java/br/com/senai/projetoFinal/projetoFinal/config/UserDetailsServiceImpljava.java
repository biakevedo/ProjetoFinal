package br.com.senai.projetoFinal.projetoFinal.config;

import br.com.senai.projetoFinal.projetoFinal.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpljava implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpljava(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)

        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }

}
