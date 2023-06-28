package br.ufma.sppg.model;

import java.util.List;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producao")
    Integer id;

    @Column(name = "tipo", nullable = true)
    String tipo;

    @Column(name = "issn_ou_sigla", nullable = true)
    String issnOuSigla;

    @Column(name = "nome_local", nullable = true)
    String nomeLocal;

    @Column(name = "titulo", nullable = true)
    String titulo;

    @Column(name = "ano")
    Integer ano;

    @Column(name = "qualis")
    String qualis;

    @Column(name = "percentile_ou_h5", nullable = true)
    Float percentileOuH5;

    @Column(name = "qtd_grad", nullable = true)
    Integer qtdGrad;

    @Column(name = "qtd_mestrado", nullable = true)
    Integer qtdMestrado;

    @Column(name = "qtd_doutorado", nullable = true)
    Integer qtdDoutorado;

    @ManyToMany
    @JoinTable(name = "producao_orientacao", joinColumns = @JoinColumn(name = "id_producao"), inverseJoinColumns = @JoinColumn(name = "id_orientacao"))
    List<Orientacao> orientacoes;

    @ManyToMany
    @JoinTable(name = "docente_producao", joinColumns = @JoinColumn(name = "id_producao"), inverseJoinColumns = @JoinColumn(name = "id_docente"))
    List<Docente> docentes;

}
