package com.solbs.unov3.controllers;

import com.solbs.unov3.dtos.SolicitacaoDeAnaliseDto;
import com.solbs.unov3.entities.SolicitacaoDeAnalise;
import com.solbs.unov3.entities.Solicitante;
import com.solbs.unov3.services.SolicitacaoDeAnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacao-de-analise")
@CrossOrigin("*")
public class SolicitacaoDeAnaliseController {
    @Autowired
    private SolicitacaoDeAnaliseService solicitacaoDeAnaliseService;

    /**
     * Método HTTP que cadastra uma Solicitação de Análise na base de dados
     * @param solicitacaoDeAnaliseDto Dados da Solicitação de Análise a ser cadastrada
     * @return Entidade de resposta com a solicitação de análise cadastrada
     */
    @PostMapping
    public ResponseEntity<SolicitacaoDeAnalise> cadastrarSolicitacaoDeAnalise(@RequestBody SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoDeAnaliseService.cadastrarSolicitacaoDeAnalise(solicitacaoDeAnaliseDto));
    }

    /**
     * Método HTTP que retorna uma lista de Solicitações de Análise
     * @return Entidade de resposta com a lista de Solicitações de Análise
     */
    @GetMapping
    public ResponseEntity<List<SolicitacaoDeAnalise>> retornarTodosSolicitacaoDeAnalises(){
        return ResponseEntity.ok().body(solicitacaoDeAnaliseService.listarSolicitacoesDeAnalise());
    }

    /**
     * Método HTTP que retorna uma Solicitação de Análise a partir de seu id
     * @param idSA Id da Solicitação de Análise a ser retornada
     * @return Entidade de resposta com a solicitação de análise retornada
     */
    @GetMapping("/{idSA}")
    public ResponseEntity<SolicitacaoDeAnalise> retornarSolicitacaoDeAnalisePorId(@PathVariable String idSA){
        return ResponseEntity.status(HttpStatus.OK).body(solicitacaoDeAnaliseService.procurarSolicitacaoDeAnalise(idSA));
    }

    /**
     * Método HTTP que atualiza dados da Solicitação de Análise
     * @param idSA id da Solicitação que será atualizada
     * @param solicitacaoDeAnaliseDto dados que serão atualizados na Solicitação de Análise
     * @return Entidade de resposta com a Solicitação de Análise atualizada
     */
    @PutMapping("/{idSA}")
    public ResponseEntity<SolicitacaoDeAnalise> atualizarSolicitacaoDeAnalise(@PathVariable String idSA, @RequestBody SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto){
        return ResponseEntity.status(HttpStatus.OK).body(solicitacaoDeAnaliseService.atualizarSolicitacaoDeAnalise(idSA, solicitacaoDeAnaliseDto));
    }

    /**
     * Método HTTP que retorna uma lista de Solicitações de Análise de um solicitante
     * @param cnpj Cnpj do Solicitante das Solicitações de Análise
     * @return Entidade de resposta com lista de Solicitações de Análise
     */
    @GetMapping("/solicitante/{solicitante}")
    public ResponseEntity<List<SolicitacaoDeAnalise>> solicitacaoDeAnalisePorSolicitante(@PathVariable String cnpj){
        return ResponseEntity.status(HttpStatus.OK).body(solicitacaoDeAnaliseService.procurarSAPeloSolicitante(cnpj));
    }
}
