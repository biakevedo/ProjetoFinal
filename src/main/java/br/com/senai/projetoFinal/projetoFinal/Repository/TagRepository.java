package br.com.senai.projetoFinal.projetoFinal.Repository;

import br.com.senai.projetoFinal.projetoFinal.model.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TagRepository extends JpaRepository<TagModel, Integer> {

    List<TagModel> findByUsuarioId(Integer usuarioId);
    List<TagModel> findByNomeContainingIgnoreCase(String nome);
    Optional<TagModel> findByNomeAndUsuarioId(String nome, Integer usuarioId);

}
