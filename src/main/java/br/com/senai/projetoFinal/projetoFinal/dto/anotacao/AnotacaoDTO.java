package br.com.senai.projetoFinal.projetoFinal.dto.anotacao;

import br.com.senai.projetoFinal.projetoFinal.dto.tag.ListarTagDTO;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class AnotacaoDTO {

        private String titulo;
        private String anotacao;
        private Integer id;
        private String status;
        private OffsetDateTime dataAlteracao;
        private OffsetDateTime dataCadastro;
        private List<ListarTagDTO> tags;


    }

