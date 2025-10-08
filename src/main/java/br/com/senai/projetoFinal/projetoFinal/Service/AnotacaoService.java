package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.AnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.CadastrarAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.tag.ListarTagDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import br.com.senai.projetoFinal.projetoFinal.Repository.AnotacaoRepository;
import br.com.senai.projetoFinal.projetoFinal.model.TagModel;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    public AnotacaoService(AnotacaoRepository anotacaoRepository) {
        this.anotacaoRepository = anotacaoRepository;
    }

    // Listar todas as anotações (entidade completa)
    public List<Anotacao> listarTodos() {
        return anotacaoRepository.findAll();
    }

    // Cadastrar uma nova anotação (recebendo DTO)
    public CadastrarAnotacaoDTO cadastrarAnotacao(CadastrarAnotacaoDTO dto) {
        Anotacao nova = new Anotacao();

        nova.setTitulo(dto.getTitulo());
        nova.setStatus(dto.getStatus());
        nova.setAnotacao(dto.getAnotacao());
        nova.setImagem(dto.getImagem());
        nova.setDataCadastro(OffsetDateTime.now());
        nova.setDataAlteracao(OffsetDateTime.now());

        anotacaoRepository.save(nova);

        return dto;
    }

    // Buscar anotação por ID (entidade completa)
    public Anotacao buscarPorId(Integer id) {
        return anotacaoRepository.findById(id).orElse(null);
    }

    // Deletar anotação pelo ID
    public Anotacao deletarAnotacao(Integer id) {
        Anotacao anotacao = buscarPorId(id);

        if (anotacao == null) {
            return null;
        }

        anotacaoRepository.delete(anotacao);
        return anotacao;
    }

    // Atualizar anotação (entidade completa)
    public Anotacao atualizarAnotacao(Integer id, Anotacao novaAnotacao) {
        Anotacao antiga = buscarPorId(id);

        if (antiga == null) {
            return null;
        }

        antiga.setTitulo(novaAnotacao.getTitulo());
        antiga.setStatus(novaAnotacao.getStatus());
        antiga.setAnotacao(novaAnotacao.getAnotacao());
        antiga.setImagem(novaAnotacao.getImagem());
        antiga.setDataAlteracao(OffsetDateTime.now());

        return anotacaoRepository.save(antiga);
    }

    // Listar anotações por email do usuário, retornando ListarAnotacaoDTO (id e titulo)
    public List<AnotacaoDTO> listarAnotacoesPorEmail(String email) {
        List<Anotacao> anotacoes = anotacaoRepository.findByUsuarioEmailCompleto(email);

        return anotacoes.stream()
                .map(this::ConversorAnotacaoDTO)
                .collect(Collectors.toList());
    }

    // Conversor para DTO simples (id e titulo)
    public AnotacaoDTO ConversorAnotacaoDTO(Anotacao anotacao) {
        // 1. Criamos nosso objeto DTO de anotação, ainda vazio.
        AnotacaoDTO dto = new AnotacaoDTO();

        // 2. Fazemos o "De-Para" dos campos simples.
        dto.setId(anotacao.getId());
        dto.setTitulo(anotacao.getTitulo());
        dto.setAnotacao(anotacao.getAnotacao());

        // 3. Converter as Tags associadas à anotação
        List<ListarTagDTO> tagsDto = anotacao.getTagAnotacao().stream()
                .map(associacao -> converterTagParaDTO(associacao.getIdTag()))  // Corrigido aqui: acesso ao campo TagModel
                .collect(Collectors.toList());

        dto.setTags(tagsDto);

        return dto;
    }

    // Método para converter TagModel para ListarTagDTO
    private ListarTagDTO converterTagParaDTO(TagModel tag) {
        ListarTagDTO dto = new ListarTagDTO();
        dto.setId(tag.getId());
        dto.setNome(tag.getNome());
        return dto;
    }
}
