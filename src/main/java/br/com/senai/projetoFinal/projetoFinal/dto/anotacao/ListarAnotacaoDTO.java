package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;

public class ListarAnotacaoDTO {
    private Integer id;
    private String titulo;

    public ListarAnotacaoDTO(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
}
