package br.com.senai.projetoFinal.projetoFinal.dto.usuario;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class CadastrarUsuarioDTO {


    public OffsetDateTime getDataCadastro;
    private String email;

    private String senha;

    private String nomeCompleto;

}
