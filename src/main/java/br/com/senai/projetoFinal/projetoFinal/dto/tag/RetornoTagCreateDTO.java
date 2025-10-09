package br.com.senai.projetoFinal.projetoFinal.dto.tag;

import br.com.senai.projetoFinal.projetoFinal.dto.usuario.CadastrarUsuarioDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import lombok.Data;

@Data
public class RetornoTagCreateDTO {

    private String nomeTag;
    private Integer idUsuario;

    public String getNomeTag() {
        return nomeTag;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }


    public RetornoTagCreateDTO(String nomeTag, Usuario idUsuario) {
        this.nomeTag = nomeTag;
        this.idUsuario = idUsuario.getId();
    }
}
