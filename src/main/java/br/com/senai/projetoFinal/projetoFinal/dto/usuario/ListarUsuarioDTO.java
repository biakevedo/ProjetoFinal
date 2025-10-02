package br.com.senai.projetoFinal.projetoFinal.dto.usuario;

import jakarta.persistence.Id;

import java.time.OffsetDateTime;

public class ListarUsuarioDTO {

    private Integer id;

    private String email;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataAlteracao;

    private String nomeCompleto;

}
