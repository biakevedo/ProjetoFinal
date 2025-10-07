package br.com.senai.projetoFinal.projetoFinal.dto.tag;
import br.com.senai.projetoFinal.projetoFinal.dto.usuario.ListarUsuarioDTO;
import lombok.Data;


@Data
public class RetornoGetAllTagDTO {

    private String nome;
    private ListarUsuarioDTO usuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ListarUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(ListarUsuarioDTO usuario) {
        this.usuario = usuario;
    }
//    public RetornoTagCreateDTO(String nomeTag, ListarUsuarioDTO usuario) {
//        this.nome = nomeTag;
//        this.usuario = usuario;
//    }

}
