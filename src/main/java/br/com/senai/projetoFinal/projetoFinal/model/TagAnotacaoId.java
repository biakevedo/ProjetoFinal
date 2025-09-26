package br.com.senai.projetoFinal.projetoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TagAnotacaoId implements Serializable {
    private static final long serialVersionUID = 3578403372892572181L;
    @Column(name = "id_tag", nullable = false)
    private Integer idTag;

    @Column(name = "id_anotacao", nullable = false)
    private Integer idAnotacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TagAnotacaoId entity = (TagAnotacaoId) o;
        return Objects.equals(this.idAnotacao, entity.idAnotacao) &&
                Objects.equals(this.idTag, entity.idTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnotacao, idTag);
    }

}