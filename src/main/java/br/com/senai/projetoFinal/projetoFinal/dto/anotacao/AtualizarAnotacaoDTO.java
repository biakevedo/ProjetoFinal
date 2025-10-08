package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;

import java.util.List;

public class AtualizarAnotacaoDTO {

        private String titulo;
        private String anotacao;
        private String imagem;
        private String status;
        private List<String> tags;

        // Getters e setters
        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }

        public String getAnotacao() { return anotacao; }
        public void setAnotacao(String anotacao) { this.anotacao = anotacao; }

        public String getImagem() { return imagem; }
        public void setImagem(String imagem) { this.imagem = imagem; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public List<String> getTags() { return tags; }
        public void setTags(List<String> tags) { this.tags = tags; }
    }


