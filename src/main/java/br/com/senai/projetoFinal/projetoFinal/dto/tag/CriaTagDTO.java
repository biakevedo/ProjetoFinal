package br.com.senai.projetoFinal.projetoFinal.dto.tag;

import br.com.senai.projetoFinal.projetoFinal.model.Usuario;
import lombok.Data;

@Data
public class CriaTagDTO {

    private String nomeTag;
    private Integer idUsuario;

    public CriaTagDTO(String nomeTag, Integer idUsuario) {
        this.nomeTag = nomeTag;
        this.idUsuario = idUsuario;
    }
}
