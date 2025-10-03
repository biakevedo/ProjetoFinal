package br.com.senai.projetoFinal.projetoFinal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuario", schema = "projeto_final")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "senha", nullable = false, length = Integer.MAX_VALUE)
    private String senha;

    @Column(name = "data_cadastro", nullable = false )
    private OffsetDateTime dataCadastro;

    @Column(name = "data_alteracao", nullable = true)
    private OffsetDateTime dataAlteracao;

    @Column(name = "nome_completo", length = Integer.MAX_VALUE)
    private String nomeCompleto;

}