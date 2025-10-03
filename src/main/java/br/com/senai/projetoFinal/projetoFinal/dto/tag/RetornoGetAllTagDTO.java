package br.com.senai.projetoFinal.projetoFinal.dto.tag;
import lombok.Data;


@Data
public class RetornoGetAllTagDTO {

    private String nome;
    private ListarUsuarioDTO usuario;

    public RetornoTagCreateDTO(String nomeTag, ListarUsuarioDTO usuario) {
        this.nome = nomeTag;
        this.usuario = usuario;
    }

}
