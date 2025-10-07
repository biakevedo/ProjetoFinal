package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;

import br.com.senai.projetoFinal.projetoFinal.dto.tag.ListarTagDTO;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class AnotacaoDTO {
        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getAnotacao() {
                return anotacao;
        }

        public void setAnotacao(String anotacao) {
                this.anotacao = anotacao;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public OffsetDateTime getDataAlteracao() {
                return dataAlteracao;
        }

        public void setDataAlteracao(OffsetDateTime dataAlteracao) {
                this.dataAlteracao = dataAlteracao;
        }

        public OffsetDateTime getDataCadastro() {
                return dataCadastro;
        }

        public void setDataCadastro(OffsetDateTime dataCadastro) {
                this.dataCadastro = dataCadastro;
        }

        public List<ListarTagDTO> getTags() {
                return tags;
        }

        public void setTags(List<ListarTagDTO> tags) {
                this.tags = tags;
        }

        private String titulo;
        private String anotacao;
        private Integer id;
        private String status;
        private OffsetDateTime dataAlteracao;
        private OffsetDateTime dataCadastro;
        private List<ListarTagDTO> tags;


    }

