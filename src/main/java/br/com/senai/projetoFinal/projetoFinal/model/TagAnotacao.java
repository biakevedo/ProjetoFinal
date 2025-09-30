package br.com.senai.projetoFinal.projetoFinal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tag_anotacao", schema = "projeto_final")
public class TagAnotacao {
    @EmbeddedId
    private TagAnotacaoId id;

    @MapsId("idTag")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tag", nullable = false)
    private TagModel idTag;

    @MapsId("idAnotacao")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_anotacao", nullable = false)
    private Anotacao idAnotacao;

}