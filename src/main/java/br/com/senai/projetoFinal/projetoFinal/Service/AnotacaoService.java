package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.AnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.ListarAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import br.com.senai.projetoFinal.projetoFinal.Repository.AnotacaoRepository;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.yaml.snakeyaml.tokens.Token.ID.Tag;


@Service
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    public AnotacaoService(AnotacaoRepository anotacaoRepository) {
        this.anotacaoRepository = anotacaoRepository;
    }

    // Converter entidade para DTO simples
    private AnotacaoDTO toDTO(Anotacao anotacao) {
        return new AnotacaoDTO(
                anotacao.getTitulo(),
                anotacao.getDescricao(),
                anotacao.getImagemUrl(),
                anotacao.getUsuario() != null ? anotacao.getUsuario().getId() : null,
                anotacao.getTags() != null
                        ? anotacao.getTags().stream().map(Tag::getNome).collect(Collectors.toList())
                        : null
        );
    }

    // Converter DTO para entidade
    private Anotacao toEntity(AnotacaoDTO dto) {
        Anotacao anotacao = new Anotacao();
        anotacao.setTitulo(dto.getTitulo());
        anotacao.setDescricao(dto.getDescricao());
        anotacao.setImagemUrl(dto.getImagemUrl());
        return anotacao;
    }

    public List<AnotacaoDTO> listarTodos() {
        return anotacaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AnotacaoDTO buscarPorId(Integer id) {
        return anotacaoRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public AnotacaoDTO cadastrarAnotacao(AnotacaoDTO dto) {
        if (dto.getTitulo() == null || dto.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }

        Anotacao anotacao = toEntity(dto);
        Anotacao salvo = anotacaoRepository.save(anotacao);
        return toDTO(salvo);
    }

    public AnotacaoDTO atualizarAnotacao(Integer id, AnotacaoDTO anotacaoNovo) {
        Anotacao anotacoes = anotacaoRepository.findById(id).orElse(null);



        AnotacaoDTO dto = anotacoes.stream()
                .map(anotacao -> new AnotacaoDTO ( anotacao.getTitulo(),
                        anotacao.getDescricao(),
                        anotacao.getImagemUrl())
                        );
            return dto;
        })
    }

    public boolean deletarAnotacao(Integer id) {
        return anotacaoRepository.findById(id).map(anotacao -> {
            anotacaoRepository.delete(anotacao);
            return true;
        }).orElse(false);
    }


    public List<ListarAnotacaoDTO> listarAnotacoesPorEmail(String email) {

        List<Anotacao> anotacoesDoBanco = anotacaoRepository.findByUsuarioEmailCompleto(email);


        return anotacoesDoBanco.stream()
                .map(this::converterParaDTO) // chamando método auxiliar
                .collect(Collectors.toList());
    }


    private ListarAnotacaoDTO converterParaDTO(Anotacao anotacao) {
        return new ListarAnotacaoDTO(
                anotacao.getTitulo(),
                anotacao.getDescricao(),
                anotacao.getImagemUrl(),
                anotacao.getTags() != null
                        ? anotacao.getTags().stream().map(Tag::getNome).collect(Collectors.toList())
                        : null
        );
    }
}
