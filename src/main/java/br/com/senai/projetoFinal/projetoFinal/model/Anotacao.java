package br.com.senai.projetoFinal.projetoFinal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "anotacao", schema = "projeto_final")
public class Anotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anotacao", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = Integer.MAX_VALUE)
    private String titulo;

    @Column(name = "status", nullable = false, length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "anotacao", length = Integer.MAX_VALUE)
    private String anotacao;

    @Column(name = "data_alteracao", nullable = true,columnDefinition = "TIMESTAMPTZ" )
    private OffsetDateTime dataAlteracao;

    @Column(name = "data_cadastro", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime dataCadastro;

    @Column(name = "imagem", nullable = false, length = Integer.MAX_VALUE)
    private String imagem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public List<TagAnotacao> getTagAnotacao() {
        return tagAnotacao;
    }

    public void setTagAnotacao(List<TagAnotacao> tagAnotacao) {
        this.tagAnotacao = tagAnotacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @OneToMany(mappedBy = "idAnotacao")
    private List<TagAnotacao> tagAnotacao;

    }


