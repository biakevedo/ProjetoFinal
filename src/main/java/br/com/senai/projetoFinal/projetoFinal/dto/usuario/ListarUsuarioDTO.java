package br.com.senai.projetoFinal.projetoFinal.dto.usuario;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class ListarUsuarioDTO {

    private String email;
    private String nomeCompleto;
}
