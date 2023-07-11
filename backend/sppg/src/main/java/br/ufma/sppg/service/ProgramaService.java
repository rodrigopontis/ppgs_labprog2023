package br.ufma.sppg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufma.sppg.domain.dto.Indice;
import br.ufma.sppg.domain.dto.QualisStatsDTO;
import br.ufma.sppg.domain.enums.QualisEnum;
import br.ufma.sppg.domain.model.Docente;
import br.ufma.sppg.domain.model.Orientacao;
import br.ufma.sppg.domain.model.Producao;
import br.ufma.sppg.domain.model.Programa;
import br.ufma.sppg.domain.model.Tecnica;
import br.ufma.sppg.repo.ProgramaRepository;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@Service
public class ProgramaService {

    @Autowired
    ProgramaRepository repository;

    public List<Programa> findAll() {
        return repository.findAll();
    }

    public List<QualisStatsDTO> getQualisStats(Integer idPrograma, Integer anoIni, Integer anoFim) {

        verificarId(idPrograma);
        verificarData(anoIni, anoFim);

        List<Docente> docentes = repository.obterDocentes(idPrograma);
        List<QualisStatsDTO> stats = new ArrayList<QualisStatsDTO>();

        for (QualisEnum value : QualisEnum.values()) {
            stats.add(new QualisStatsDTO(value, 0));
        }

        for (Docente docente : docentes) {
            for (Producao prod : docente.getProducoes()) {
                if (prod.getAno() >= anoIni && prod.getAno() <= anoFim) {
                    if (prod.getQualis() != null) {
                        for (QualisStatsDTO stat : stats) {
                            if (stat.getType().toString().equals(prod.getQualis())) {
                                stat.setQtd(stat.getQtd() + 1);
                            }
                        }
                    }
                }
            }
        }

        return stats;
    }

    public List<Programa> obterPrograma(String nome) {
        verificarNome(nome);
        return repository.findByNome(nome);
    }

    public List<Docente> obterDocentesPrograma(Integer idPrograma) {
        verificarId(idPrograma);
        return repository.obterDocentes(idPrograma);
    }

    public Indice obterProducaoIndices(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Docente> docentes = repository.obterDocentes(idPrograma);
        Double iRestrito = 0.0;
        Double iNRestrito = 0.0;
        Double iGeral = 0.0;
        List<Producao> producoes = new ArrayList<>();
        ArrayList<Integer> indicesProd = new ArrayList<>();

        for (Docente docente : docentes) {

            producoes = docente.getProducoes();

            for (Producao producao : producoes) {

                if (producao.getAno() >= anoIni && producao.getAno() <= anoFin
                        && !indicesProd.contains(producao.getId())) {

                    indicesProd.add(producao.getId());
                    switch (producao.getQualis()) {
                        case "A1":
                            iRestrito += 1.0f;
                            break;

                        case "A2":
                            iRestrito += 0.85;
                            break;

                        case "A3":
                            iRestrito += 0.725;
                            break;

                        case "A4":
                            iRestrito += 0.625;
                            break;

                        case "B1":
                            iNRestrito += 0.5;
                            break;

                        case "B2":
                            iNRestrito += 0.25;
                            break;

                        case "B3":
                            iNRestrito += 0.1;
                            break;

                        case "B4":
                            iNRestrito += 0.05;
                            break;

                        default:
                            throw new ServicoRuntimeException("Uma das produções possui o Qualis inválido");
                    }
                }
            }
        }
        iGeral = iRestrito + iNRestrito;

        return new Indice(iRestrito, iNRestrito, iGeral);
    }

    // devolve uma List<Orientacao> de um dado programa dentro de um periodo
    public List<Orientacao> obterOrientacoes(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Orientacao> orientacoes = new ArrayList<>();
        List<Docente> docentes = repository.obterDocentes(idPrograma);
        List<Orientacao> orientacoesDoc = new ArrayList<>();
        ArrayList<Integer> idOrientacoes = new ArrayList<>();

        // verifica as Orientacões de cada docente e filtra as repetidas para não
        // adicona-las mais de uma vez
        for (Docente docente : docentes) {

            orientacoesDoc = docente.getOrientacoes();
            for (Orientacao orientacao : orientacoesDoc) {

                if (orientacao.getAno() >= anoIni && orientacao.getAno() <= anoFin
                        && !idOrientacoes.contains(orientacao.getId())) {

                    idOrientacoes.add(orientacao.getId());
                    orientacoes.add(orientacao);
                }
            }
        }

        return orientacoes;
    }

    // devolve uma List<Producao> de um dado programa dentro de um periodo
    public List<Producao> obterProducoes(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Producao> producoes = new ArrayList<>();
        List<Docente> docentes = repository.obterDocentes(idPrograma);
        List<Producao> producoesDoc = new ArrayList<>();
        ArrayList<Integer> idProducoes = new ArrayList<>();

        // verifica as producões de cada docente e filtra as repetidas para não
        // adicona-las mais de uma vez
        for (Docente docente : docentes) {

            producoesDoc = docente.getProducoes();
            for (Producao producao : producoesDoc) {

                if (producao.getAno() >= anoIni && producao.getAno() <= anoFin
                        && !idProducoes.contains(producao.getId())) {

                    idProducoes.add(producao.getId());
                    producoes.add(producao);
                }
            }
        }

        return producoes;
    }

    // devolve uma List<Tecnica> de um dado programa dentro de um periodo
    public List<Tecnica> obterTecnicas(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Tecnica> tecnicas = new ArrayList<>();
        List<Docente> docentes = repository.obterDocentes(idPrograma);
        List<Tecnica> tecnicasDoc = new ArrayList<>();
        ArrayList<Integer> idTecnicas = new ArrayList<>();

        // verifica as tecnicas de cada docente e filtra as repetidas para não
        // adicona-las mais de uma vez
        for (Docente docente : docentes) {

            tecnicasDoc = docente.getTecnicas();
            for (Tecnica tecnica : tecnicasDoc) {

                if (tecnica.getAno() >= anoIni && tecnica.getAno() <= anoFin && !idTecnicas.contains(tecnica.getId())) {

                    idTecnicas.add(tecnica.getId());
                    tecnicas.add(tecnica);
                }
            }
        }

        return tecnicas;
    }

    // conta quantas orientações possuem ao menos 1 producao e devolve quantas
    // cumprem essa meta
    public Integer quantitativoOrientacaoProducao(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Docente> docentes = repository.obterDocentes(idPrograma);
        List<Orientacao> orientacoesDoc = new ArrayList<>();
        ArrayList<Integer> idOrientacoes = new ArrayList<>();

        // verifica as orientações de cada docente que pertence ao programa
        for (Docente docente : docentes) {

            orientacoesDoc = docente.getOrientacoes();
            for (Orientacao orientacao : orientacoesDoc) {

                if (orientacao.getAno() >= anoIni && orientacao.getAno() <= anoFin
                        && !idOrientacoes.contains(orientacao.getId()) && !orientacao.getProducoes().isEmpty()) {

                    idOrientacoes.add(orientacao.getId());
                }
            }
        }

        return idOrientacoes.size();
    }

    // conta quantas orientações possuem ao menos 1 tecnica e devolve quantas
    // cumprem essa meta
    public Integer quantitativoOrientacaoTecnica(Integer idPrograma, Integer anoIni, Integer anoFin) {
        verificarId(idPrograma);
        verificarData(anoIni, anoFin);
        List<Docente> docentes = repository.obterDocentes(idPrograma);
        List<Orientacao> orientacoesDoc = new ArrayList<>();
        ArrayList<Integer> idOrientacoes = new ArrayList<>();

        // verifica as orientações de cada docente que pertence ao programa
        for (Docente docente : docentes) {

            orientacoesDoc = docente.getOrientacoes();
            for (Orientacao orientacao : orientacoesDoc) {

                if (orientacao.getAno() >= anoIni && orientacao.getAno() <= anoFin
                        && !idOrientacoes.contains(orientacao.getId()) && !orientacao.getTecnicas().isEmpty()) {

                    idOrientacoes.add(orientacao.getId());
                }
            }
        }

        return idOrientacoes.size();
    }

    private void verificarNome(String nome) {
        if (nome == null) {
            throw new ServicoRuntimeException("Nome do Programa inválido");
        }
        if (nome.trim().equals("")) {
            throw new ServicoRuntimeException("Nome do Programa inválido");
        }
    }

    private void verificarId(Integer idPrograma) {
        verificarNumero(idPrograma);
        if (!repository.existsById(idPrograma)) {
            throw new ServicoRuntimeException("Id do Programa não está registrado");
        }
    }

    private void verificarData(Integer data1, Integer data2) {
        verificarNumero(data1);
        verificarNumero(data2);
        if (data1 > data2) {
            throw new ServicoRuntimeException("Data inical maior que a data final");
        }
    }

    private void verificarNumero(Integer numero) {
        if (numero == null) {
            throw new ServicoRuntimeException("Número Inválido");
        }

    }

}