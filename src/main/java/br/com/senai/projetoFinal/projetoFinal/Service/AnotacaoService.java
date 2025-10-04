package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.CadastrarAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.dto.anotacao.ListarAnotacaoDTO;
import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import br.com.senai.projetoFinal.projetoFinal.Repository.AnotacaoRepository;
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
        public List<ListarAnotacaoDTO> listarAnotacoesPorEmail(String email) {
            List<Anotacao> anotacoes = anotacaoRepository.findByUsuarioEmailCompleto(email);

            return anotacoes.stream()
                    .map(this::converterParaDTO)
                    .collect(Collectors.toList());
        }

        // Conversor para DTO simples (id e titulo)
        private ListarAnotacaoDTO converterParaDTO(Anotacao anotacao) {
            return new ListarAnotacaoDTO(anotacao.getId(), anotacao.getTitulo());
        }
    }


