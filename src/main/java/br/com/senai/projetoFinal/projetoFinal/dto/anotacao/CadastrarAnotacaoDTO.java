package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CadastrarAnotacaoDTO {

        @NotBlank(message = "O título é obrigatório")
        private String titulo;

        @NotBlank(message = "A anotação é obrigatória")
        private String anotacao; // era "descricao"

        private String imagem;

        @NotNull(message = "O usuário é obrigatório")
        private Integer usuarioId;

        private String status = "ativo"; // valor padrão

        private List<String> tags;

        // Getters e setters
        public String getTitulo() {
            return titulo;
        }
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAnotacao() {
            return anotacao;
        }
        public void setAnotacao(String anotacao) {
            this.anotacao = anotacao;
        }

        public String getImagem() {
            return imagem;
        }
        public void setImagem(String imagem) {
            this.imagem = imagem;
        }

        public Integer getUsuarioId() {
            return usuarioId;
        }
        public void setUsuarioId(Integer usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getTags() {
            return tags;
        }
        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
