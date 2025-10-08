package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;

public class DeletarAnotacaoDTO {
        private Integer id;
        private String mensagem;

        public DeletarAnotacaoDTO(Integer id, String mensagem) {
            this.id = id;
            this.mensagem = mensagem;
        }

        public Integer getId() { return id; }
        public String getMensagem() { return mensagem; }
    }


