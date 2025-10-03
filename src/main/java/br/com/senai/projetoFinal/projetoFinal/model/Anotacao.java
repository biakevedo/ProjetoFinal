package br.com.senai.projetoFinal.projetoFinal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "anotacao", schema = "projeto_final")
public class Anotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anotacao", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = Integer.MAX_VALUE)
    private String titulo;

    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "anotacao", length = Integer.MAX_VALUE)
    private String anotacao;

    @Column(name = "data_alteracao", nullable = true)
    private OffsetDateTime dataAlteracao;

    @Column(name = "data_cadastro", nullable = false, length = Character.MAX_VALUE)
    private OffsetDateTime dataCadastro;

    @Column(name = "imagem", nullable = false, length = Integer.MAX_VALUE)
    private String imagem;
}