package br.com.senai.projetoFinal.projetoFinal.dto.usuario;
import lombok.Data;

@Data
public class GetByIdUsuarioDTO {

    private String email;
    private String nomeCompleto;

    public GetByIdUsuarioDTO(String email, String nomeCompleto) {
        this.email = email;
        this.nomeCompleto = nomeCompleto;
    }
}
