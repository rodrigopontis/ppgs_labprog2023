package br.ufma.sppg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.domain.dto.TrocarDocenteDTO;
import br.ufma.sppg.domain.model.Orientacao;
import br.ufma.sppg.service.OrientacaoService;
import br.ufma.sppg.service.ProducaoService;
import br.ufma.sppg.service.TecnicaService;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@RestController
@RequestMapping("/api/v1/orientacao")
@CrossOrigin(origins = "*")
public class OrientacaoController {

  @Autowired
  OrientacaoService orientacaoService;

  @Autowired
  ProducaoService producaoService;

  @Autowired
  TecnicaService tecnicaService;

  @GetMapping("/{id}")
  public Orientacao getAll(@PathVariable Integer id) {
    return orientacaoService.findById(id);
  }

  @GetMapping("/obterOrientacoesDocenteComTecnica")
  public ResponseEntity<?> obterOrientacoesDocenteComTecnica(
      @RequestParam("docente") Integer idDocente, Integer dataInicio, Integer dataFim) {
    try {
      Optional<List<Orientacao>> orientacoes = orientacaoService.obterOrientacoesComTecnicaPorPeriodo(idDocente,
          dataInicio, dataFim);
      return new ResponseEntity<>(
          orientacoes.get(),
          HttpStatus.OK);
    } catch (ServicoRuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/obterOrientacoesDocenteComProducao")
  public ResponseEntity<?> obterOrientacaoDocenteComProducao(
      @RequestParam("docente") Integer idDocente, Integer dataInicio, Integer dataFim) {
    try {
      Optional<List<Orientacao>> orientacoes = orientacaoService.obterOrientacoesComProducaoPorPeriodo(idDocente,
          dataInicio, dataFim);

      return new ResponseEntity<>(
          orientacoes.get(),
          HttpStatus.OK);
    } catch (ServicoRuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/trocarDiscente/{idOri}")
  public ResponseEntity<?> trocarDiscente(@PathVariable Integer idOri, @RequestBody TrocarDocenteDTO discente) {
    try {
      Orientacao oriAlterada = orientacaoService.trocarDiscente(idOri, discente.getDiscente());

      ResponseEntity<Orientacao> res = new ResponseEntity<Orientacao>(oriAlterada, HttpStatus.OK);

      return res;
    } catch (Exception e) {
      // TODO: handle exception

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}