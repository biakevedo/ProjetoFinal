package br.com.senai.projetoFinal.projetoFinal.dto.tag;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.ListarUsuarioDTO;
import lombok.Data;


@Data
public class RetornoGetAllTagDTO {

    private String nome;
    private ListarUsuarioDTO usuario;

    public void RetornoTagCreateDTO(String nomeTag, ListarUsuarioDTO usuario) {
        this.nome = nomeTag;
        this.usuario = usuario;
    }

}
