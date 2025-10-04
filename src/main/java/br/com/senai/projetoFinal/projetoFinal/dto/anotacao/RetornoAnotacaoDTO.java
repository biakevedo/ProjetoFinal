package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;

import java.util.List;

public class RetornoAnotacaoDTO {

    private Integer id;
    private String titulo;
    private String descricao;
    private String imagemUrl;
    private Integer usuarioId;
    private List<String> tags;

    public RetornoAnotacaoDTO(Integer id, String titulo, String descricao, String imagemUrl, Integer usuarioId, List<String> tags) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.usuarioId = usuarioId;
        this.tags = tags;
    }

    // Getters
    public Integer getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getImagemUrl() {
        return imagemUrl;
    }
    public Integer getUsuarioId() {
        return usuarioId;
    }
    public List<String> getTags() {
        return tags;
    }
}
