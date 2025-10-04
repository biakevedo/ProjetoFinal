package br.com.senai.projetoFinal.projetoFinal.Service;

import br.com.senai.projetoFinal.projetoFinal.model.Anotacao;
import br.com.senai.projetoFinal.projetoFinal.Repository.AnotacaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
    public class AnotacaoService {

        private final AnotacaoRepository anotacaoRepository;

        public AnotacaoService(AnotacaoRepository anotacaoRepository) {
            this.anotacaoRepository = anotacaoRepository;
        }

        public List<Anotacao> listarTodos() {
            return anotacaoRepository.findAll();
        }

        public Anotacao cadastrarAnotacao(Anotacao anotacao) {

            if (anotacao.getTitulo() == null || anotacao.getTitulo().isEmpty()) {
                throw new IllegalArgumentException("Título é obrigatório");
            }
            if (anotacao.getStatus() == null || anotacao.getStatus().isEmpty()) {
                throw new IllegalArgumentException("Status é obrigatório");
            }

            anotacao.setDataCadastro(OffsetDateTime.now());

            return anotacaoRepository.save(anotacao);
        }

        public Anotacao buscarPorId(Integer id) {
            return anotacaoRepository.findById(id).orElse(null);
        }

        public Anotacao deletarAnotacao(Integer id) {
            Anotacao anotacao = buscarPorId(id);
            if (anotacao == null) {
                return null;
            }
            anotacaoRepository.delete(anotacao);
            return anotacao;
        }

        public Anotacao atualizarAnotacao(Integer id, Anotacao anotacaoNova) {
            Anotacao anotacaoAntiga = buscarPorId(id);

            if (anotacaoAntiga == null || anotacaoNova == null) {
                return null;
            }

            anotacaoAntiga.setTitulo(anotacaoNova.getTitulo());
            anotacaoAntiga.setStatus(anotacaoNova.getStatus());
            anotacaoAntiga.setAnotacao(anotacaoNova.getAnotacao());
            anotacaoAntiga.setImagem(anotacaoNova.getImagem());
            anotacaoAntiga.setDataAlteracao(OffsetDateTime.now());

            return anotacaoRepository.save(anotacaoAntiga);
        }
    }




