package br.com.senai.projetoFinal.projetoFinal.dto.usuario;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class ListarUsuarioDTO {

    private Integer id;

    private String email;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataAlteracao;

    private String nomeCompleto;

}
