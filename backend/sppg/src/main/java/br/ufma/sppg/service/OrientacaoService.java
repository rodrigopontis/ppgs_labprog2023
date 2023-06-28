package br.ufma.sppg.service;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD

import br.ufma.sppg.dto.OrientacaoResponse;
=======
import org.springframework.stereotype.Service;

>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
import br.ufma.sppg.model.Docente;
import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Producao;
import br.ufma.sppg.model.Programa;
import br.ufma.sppg.model.Tecnica;
import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.repo.OrientacaoRepository;
import br.ufma.sppg.repo.ProducaoRepository;
import br.ufma.sppg.repo.ProgramaRepository;
import br.ufma.sppg.repo.TecnicaRepository;

<<<<<<< HEAD
public class OrientacaoService implements IOrientacao {
=======
@Service
public class OrientacaoService  {
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a

    @Autowired
    private OrientacaoRepository orientacaoRepository;

    @Autowired
<<<<<<< HEAD
    private DocenteRepository docenteRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Autowired
    private ProducaoRepository producaoRepository;

    @Override
    public ArrayList<OrientacaoResponse> obterOrientacaoPPG(Integer id) {

        var orientacoes = orientacaoRepository.findAllById(id);
        var responses = new ArrayList<OrientacaoResponse>();
        for (var orientacao : orientacoes) {
            var response = new OrientacaoResponse(orientacao);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public ArrayList<OrientacaoResponse> obterOrientacaoDocentes(Integer idDocente) {
        var orientacoes = orientacaoRepository.findAllById(idDocente);
        var responses = new ArrayList<OrientacaoResponse>();
        for (var orientacao : orientacoes) {
            var response = new OrientacaoResponse(orientacao);
            responses.add(response);
        }
        return responses;

=======
    private ProgramaRepository programaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ProducaoRepository producaoRepository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    public List<Orientacao> obterOrientacaoPPG(Integer id, Integer anoIni, Integer anoFim) {

        validarOrientacoesPpg(id, anoIni, anoFim);
        List<Orientacao> orientacoes = orientacaoRepository.findByPPG(id, anoIni, anoFim).get();

        return orientacoes;
    }

    public List<Orientacao> obterOrientacaoDocente(Integer id, Integer anoIni, Integer anoFim) {

        validarOrientacoesDoc(id, anoIni, anoFim);
        List<Orientacao> orientacoes = orientacaoRepository.findByPPG(id, anoIni, anoFim).get();

        return orientacoes;
    }

    public Orientacao associarOrientacaoProducao(Integer idOri, Integer idProd) {
        validarOriProd(idOri, idProd);

        Orientacao orientacao = orientacaoRepository.findById(idOri).get();
        Producao prod = producaoRepository.findById(idProd).get();

        orientacao.getProducoes().add(prod);
        return orientacaoRepository.save(orientacao);
    }

    public Orientacao associarOrientacaoTecnica(Integer idOri, Integer idTec) {
        validarOriTec(idOri, idTec);

        Orientacao orientacao = orientacaoRepository.findById(idOri).get();
        Tecnica tec = tecnicaRepository.findById(idTec).get();

        orientacao.getTecnicas().add(tec);
        return orientacaoRepository.save(orientacao);
    }

    private void validarOrientacoesPpg(Integer idPrograma, Integer anoIni, Integer anoFim) {

        Optional<Programa> programa = programaRepository.findById(idPrograma);

        Optional<List<Orientacao>> orientacoes = orientacaoRepository.findByPPG(idPrograma,  anoIni, anoFim);
        
        if (programa.isEmpty())
            throw new RuntimeException("Não foram encontrados  programas com este Id.");
        if (orientacoes.isEmpty())
            throw new RuntimeException("Não foram encontradas orientações para este docente.");
    }

    private void validarOrientacoesDoc(Integer idDocente Integer anoIni, Integer anoFim) {

        Optional<Docente> docente = docenteRepository.findById(idDocente);

        Optional<List<Orientacao>> orientacoes = orientacaoRepository.findByDocente(idDocente, anoIni, anoFim);

        if (docente.isEmpty())
            throw new RuntimeException("Não foram encontrados  programas com este Id.");
        if (orientacoes.isEmpty())
            throw new RuntimeException("Não foram encontradas orientações para este docente.");
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
    }

    private void validarOriProd(Integer idOri, Integer idProd) {

        Optional<Producao> prod = producaoRepository.findById(idProd);

        Optional<Orientacao> orientacao = orientacaoRepository.findById(idOri);

        if (prod.isEmpty())
            throw new RuntimeException("Não foram existe produção.");
        if (orientacao.isEmpty())
            throw new RuntimeException("Não foram existe orientação.");
    }

<<<<<<< HEAD
    // ##############################################################################################################
    public List<Orientacao> obterTodasOrientacoes() {
        return orientacaoRepository.findAll();
    }

    public List<Orientacao> obterOrientacoesPPG(Integer idPrograma, Integer anoInicio, Integer anoFim) {

        validarOrientacoes(idPrograma, anoInicio, anoFim);
        List<Orientacao> orientacoes = orientacaoRepository.findByPPG(idPrograma, anoInicio, anoFim).get();

        return orientacoes;

    }

    public List<Orientacao> obterOrientacoesDocente(Integer idDocente, Integer anoInicio, Integer anoFim) {

        validarOrientacoes(idDocente, anoInicio, anoFim);
        List<Orientacao> orientacoes = orientacaoRepository.findByDocente(idDocente, anoInicio, anoFim).get();

        return orientacoes;
    }

    public Orientacao associarOrientacaoProducao(Integer idProducao, Integer idOrientacao) {
        Optional<Orientacao> orientacao = orientacaoRepository.findById(idProducao);
        Optional<Producao> producao = producaoRepository.findById(idOrientacao);
        //
        orientacao.get().getProducoes().add(producao.get());
        return orientacaoRepository.save(orientacao.get());
    }

    public Orientacao associarOrientacaoTecnica(Integer idTecnica, Integer idOrientacao) {
        Optional<Orientacao> orientacao = orientacaoRepository.findById(idTecnica);
        Optional<Tecnica> tecnica = tecnicaRepository.findById(idOrientacao);

        orientacao.get().getTecnicas().add(tecnica.get());
        return orientacaoRepository.save(orientacao.get());

    }

    private void validarProducoes(Integer idProducao) {
        Optional<Producao> producao = producaoRepository.findById(idProducao);
        if (producao.isEmpty())
            throw new RuntimeException("Nao foram encontrados Producoes com este Id");
    }

    private void validarOrientacoes(Integer idPrograma, Integer anoInicio, Integer anoFim) {
        // identifica programa
        // dentro da lista de orientacoes é feito um query no BD e busca se existe o
        // programa
        Optional<Programa> programa = programaRepository.findById(idPrograma);
        Optional<List<Orientacao>> orientacoes = orientacaoRepository.findByPPG(idPrograma, anoInicio, anoFim);

        if (programa.isEmpty())
            throw new RuntimeException("Não foram encontrados programas com este Id. ");
        if (orientacoes.isEmpty())
            throw new RuntimeException("Nao foram encontradas orientações para este docente");
    }

    private void validarDocente(Integer idDocente) {
        Optional<Docente> docente = docenteRepository.findById(idDocente);
        if (docente.isEmpty())
            throw new RuntimeException("Não foram encontrados Docentes com este Id");
    }

    private void validarTecnica(Integer idTecnica) {
        Optional<Tecnica> tecnica = tecnicaRepository.findById(idTecnica);
        if (tecnica.isEmpty())
            throw new RuntimeException("Não foram encontrados Tecnicas com este Id");
    }

    private void validarProducao(Integer idProducao) {
        Optional<Producao> producao = producaoRepository.findById(idProducao);
        if (producao.isEmpty())
            throw new RuntimeException("Não foram encontrados Producoes com este Id");
=======
    private void validarOriTec(Integer idOri, Integer idTec) {

        Optional<Tecnica> tec = tecnicaRepository.findById(idTec);

        Optional<Orientacao> orientacao = orientacaoRepository.findById(idOri);

        if (tec.isEmpty())
            throw new RuntimeException("Não foram existe tecnica.");
        if (orientacao.isEmpty())
            throw new RuntimeException("Não foram existe orientação.");
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
    }

}
