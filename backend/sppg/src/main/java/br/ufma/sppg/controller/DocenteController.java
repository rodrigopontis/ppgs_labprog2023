package br.ufma.sppg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.domain.dto.FilterDocenteQualisStatsDTO;
import br.ufma.sppg.domain.dto.QualisDocenteDTO;
// import br.ufma.sppg.domain.model.Docente;
import br.ufma.sppg.domain.model.Orientacao;
import br.ufma.sppg.domain.model.Producao;
import br.ufma.sppg.domain.model.Tecnica;
import br.ufma.sppg.service.DocenteService;
// import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.service.OrientacaoService;
import br.ufma.sppg.service.ProducaoService;
import br.ufma.sppg.service.ProgramaService;
import br.ufma.sppg.service.TecnicaService;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@RestController
@RequestMapping("/api/v1/docente")
@CrossOrigin(origins = "*")
public class DocenteController {
    @Autowired
    TecnicaService tecnicaServivce;

    @Autowired
    ProducaoService producaoServivce;

    @Autowired
    OrientacaoService orientacaoServivce;

    @Autowired
    ProgramaService programaService;

    @Autowired
    DocenteService service;

    // @Autowired
    // DocenteRepository repo;
    // todo: rota de login

    // todo: rota de cadastro

    // @GetMapping
    // public List<Docente> getAll() {
    // System.out.println("Todos os docentes");
    // return repo.findAll();
    // }

    @GetMapping("/nome/{id}")
    public ResponseEntity<?> getNomeById(@PathVariable(value = "id", required = true) Integer idDocente) {
        try {
            return ResponseEntity.ok(service.obterDocente(idDocente));
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_producoes/{id}/{data1}/{data2}")
    public ResponseEntity<?> obterProducoesDeDocente(@PathVariable(value = "id", required = true) Integer idDocente,
            @PathVariable(value = "data1", required = true) Integer data1,
            @PathVariable(value = "data2", required = true) Integer data2) {

        try {
            List<Producao> producaoDocente = producaoServivce.obterProducoesDocente(idDocente, data1, data2);
            return ResponseEntity.ok(producaoDocente);
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/qualis/stats/{id}/{data1}/{data2}")
    public ResponseEntity<?> obterStatusDeProducaoPorDocente(
            @PathVariable(value = "id", required = true) Integer idDocente,
            @PathVariable(value = "data1", required = true) Integer data1,
            @PathVariable(value = "data2", required = true) Integer data2) {

        try {
            List<QualisDocenteDTO> res = programaService.obterStatsPorDocente(idDocente, data1, data2);
            return ResponseEntity.ok(res);
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/qualis/stats/{idProg}/{idDoc}/{data1}/{data2}")
    public ResponseEntity<?> obterStatusDeProducaoDoDocente(
            @PathVariable(value = "idProg", required = true) Integer idProg,
            @PathVariable(value = "idDoc", required = true) Integer idDocente,
            @PathVariable(value = "data1", required = true) Integer data1,
            @PathVariable(value = "data2", required = true) Integer data2) {

        try {
            List<FilterDocenteQualisStatsDTO> res = programaService.obterDocenteQualisStats(idProg, idDocente, data1,
                    data2);

            return ResponseEntity.ok(res);
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_orientacoes/{id}/{data1}/{data2}")
    public ResponseEntity<?> obterOrientacoesDeDocente(@PathVariable(value = "id", required = true) Integer idDocente,
            @PathVariable(value = "data1", required = true) Integer data1,
            @PathVariable(value = "data2", required = true) Integer data2) {

        try {
            List<Orientacao> orientacaoDocente = orientacaoServivce.obterOrientacaoDocente(idDocente, data1, data2);
            return ResponseEntity.ok(orientacaoDocente);
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_tecnicas/{id}/{data1}/{data2}")
    public ResponseEntity<?> obterTecnicasDeDocente(@PathVariable(value = "id", required = true) Integer idDocente) {

        try {
            List<Tecnica> tecnicaDocente = tecnicaServivce.obterTecnicasDocente(idDocente);
            return ResponseEntity.ok(tecnicaDocente);
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}