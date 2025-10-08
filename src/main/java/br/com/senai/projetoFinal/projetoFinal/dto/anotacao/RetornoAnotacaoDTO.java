package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;

import java.util.List;

public class RetornoAnotacaoDTO {
        private Integer id;
        private String titulo;
        private String anotacao;  // era descricao
        private String imagem;    // era imagemUrl
        private Integer usuarioId;
        private List<String> tags;

        public RetornoAnotacaoDTO(Integer id, String titulo, String anotacao, String imagem, Integer usuarioId, List<String> tags) {
            this.id = id;
            this.titulo = titulo;
            this.anotacao = anotacao;
            this.imagem = imagem;
            this.usuarioId = usuarioId;
            this.tags = tags;
        }

        // Getters
        public Integer getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getAnotacao() { return anotacao; }
        public String getImagem() { return imagem; }
        public Integer getUsuarioId() { return usuarioId; }
        public List<String> getTags() { return tags; }
    }
