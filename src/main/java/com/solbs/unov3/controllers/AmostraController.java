package com.solbs.unov3.controllers;

import com.solbs.unov3.dtos.AmostraDto;
import com.solbs.unov3.dtos.QuantidadeDeAmostraPorStatusDto;
import com.solbs.unov3.entities.Amostra;
import com.solbs.unov3.entities.SolicitacaoDeAnalise;
import com.solbs.unov3.services.AmostraService;
import com.solbs.unov3.services.SolicitacaoDeAnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amostra")
@CrossOrigin("*")
public class AmostraController {
    @Autowired
    private AmostraService amostraService;

    @Autowired
    private SolicitacaoDeAnaliseService solicitacaoDeAnaliseService;

    /**
     * Método HTTP que cadastra uma amostra na base de dados
     * @param amostraDto dados da amostra a ser cadastrada
     * @return Entidade de resposta com a amostra cadastrada
     */
    @PostMapping
    public ResponseEntity<Amostra> cadastrarAmostra(@RequestBody AmostraDto amostraDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(amostraService.cadastrarAmostra(amostraDto));
    }


    /**
     * Método HTTP que retorna uma lista com todas as amostras na base de dados
     * @return Entidade de resposta com a lista com amostras
     */
    @GetMapping
    public ResponseEntity<List<Amostra>> retornarTodasAmostras(){
        return ResponseEntity.status(HttpStatus.OK).body(amostraService.listarAmostrar());
    }

    /**
     * Método HTTP que retorna uma amostra a partir do id
     * @param idAmostra id da amostra a ser retornada
     * @return Entidade de resposta com a amostra
     */
    @GetMapping("/{idAmostra}")
    public ResponseEntity<Amostra> retornarAmostraPorId(@PathVariable String idAmostra){
        Amostra amostra = amostraService.procurarAmostra(idAmostra);
        return ResponseEntity.status(HttpStatus.OK).body(amostra);
    }

    /**
     * Método HTTP que atualiza o Status da amostra
     * @param idAmostra id da amostra que terá o status atualizado
     * @param status código int do novo status
     * @return Entidade de resposta com a amostra atualizada
     */
    @PutMapping("/{idAmostra}")
    public ResponseEntity<Amostra> atualizarStatusDaAmostra(@PathVariable String idAmostra, @RequestBody AmostraDto amostraDto){
        return ResponseEntity.status(HttpStatus.OK).body(amostraService.atualizarStatus(idAmostra, amostraDto.getStatusAmostra()));
    }

    /**
     * Método HTTP que retorna uma lista de amostras com analises concluídas
     * @return entidade de resposta com lista de amostras
     */
    @GetMapping("/concluido")
    public ResponseEntity<List<Amostra>> procurarAmostrasFinalizadas(){
        List<Amostra> amostrasConcluidas = amostraService.procurarAmostrasFinalizadas();
        return ResponseEntity.status(HttpStatus.OK).body(amostrasConcluidas);
    }

    /**
     * Método HTTP que retorna uma lista com amostras em análise
     * @return entidade de resposta com lista de amostras
     */
    @GetMapping("/em-analise")
    public ResponseEntity<List<Amostra>> procurarAmostrasEmAnalise(){
        List<Amostra> amostrasEmAnalise = amostraService.procurarAmostrasEmAnalise();
        return ResponseEntity.status(HttpStatus.OK).body(amostrasEmAnalise);
    }

    /**
     * Método HTTP que retorna uma lista com amostras aguardando análise
     * @return Entidade de resposta com lista de amostras
     */
    @GetMapping("/aguardando-analise")
    public ResponseEntity<List<Amostra>> procurarAmostrasAguardandoAnalise(){
        List<Amostra> amostrasAguardandoAnalise = amostraService.procurarAmostrasAguardandoAnalise();
        return ResponseEntity.status(HttpStatus.OK).body(amostrasAguardandoAnalise);
    }

    /**
     * Método HTTP que retorna uma lista de amostras em falta
     * @return Entidade de resposta com lista de amostras
     */
    @GetMapping("/amostra-em-falta")
    public ResponseEntity<List<Amostra>> procurarAmostrasEmFalta(){
        List<Amostra> amostrasEmFalta = amostraService.procurarAmostrasEmFalta();
        return ResponseEntity.status(HttpStatus.OK).body(amostrasEmFalta);
    }

    /**
     * Método HTTP que retorna a quantidade de amostra em cada status
     * @return Entidade de resposta com a quantidade de amostra em cada status
     */
    @GetMapping("/quantidade-status")
    public ResponseEntity<QuantidadeDeAmostraPorStatusDto> quantidadeAmostrasPorStatus(){
        return ResponseEntity.status(HttpStatus.OK).body(amostraService.retornarQuantidadeAmostraPorStatus());
    }

    /**
     * Método HTTP que retorna uma lista de amostras a partir de sua solicitação de análise
     * @param idSA Id da solicitação de análise
     * @return Lista de Amostra
     */
    @GetMapping("/solicitacao-de-analise/{idSA}")
    public ResponseEntity<List<Amostra>> procurarAmostraPorSolicitacaoDeAnalise(@PathVariable String idSA){
        SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseService.procurarSolicitacaoDeAnalise(idSA);
        return ResponseEntity.status(HttpStatus.OK).body(amostraService.procurarAmostraPorSolicitacaoDeAnalise(solicitacaoDeAnalise));
    }
}
