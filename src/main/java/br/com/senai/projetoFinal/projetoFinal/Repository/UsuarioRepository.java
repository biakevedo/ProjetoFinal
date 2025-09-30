package br.com.senai.projetoFinal.projetoFinal.Repository;


import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
