package br.com.senai.projetoFinal.projetoFinal.Repository;

import br.com.senai.projetoFinal.projetoFinal.dto.tag.RetornoTagDTO;
import br.com.senai.projetoFinal.projetoFinal.model.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<TagModel, Integer> {

    List<TagModel> findByUsuarioId(Integer usuarioId);
    List<RetornoTagDTO> findByNomeContainingIgnoreCase(String nome);
}
