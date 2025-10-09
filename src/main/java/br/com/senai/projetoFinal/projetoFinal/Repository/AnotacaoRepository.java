package br.com.senai.projetoFinal.projetoFinal.Repository;

import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Integer> {

    List<Anotacao> findByUsuarioEmail( String email);
}


