package br.com.senai.projetoFinal.projetoFinal.Repository;

import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Integer> {
}
