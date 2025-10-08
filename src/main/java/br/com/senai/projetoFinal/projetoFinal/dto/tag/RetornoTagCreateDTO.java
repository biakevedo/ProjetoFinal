package br.com.senai.projetoFinal.projetoFinal.dto.tag;

import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import lombok.Data;

@Data
public class RetornoTagCreateDTO {

    private String nomeTag;
    private Usuario idUsuario;

    public String getNomeTag() {
        return nomeTag;
    }

    public void setNomeTag(String nomeTag) {
        this.nomeTag = nomeTag;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RetornoTagCreateDTO(String nomeTag, Usuario idUsuario) {
        this.nomeTag = nomeTag;
        this.idUsuario = idUsuario;
    }
}
