package com.solbs.unov3.controllers;

import com.solbs.unov3.dtos.EnsaioDto;
import com.solbs.unov3.entities.Ensaio;
import com.solbs.unov3.services.AmostraService;
import com.solbs.unov3.services.EnsaioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ensaio")
@CrossOrigin("*")
public class EnsaioController {
    @Autowired
    private EnsaioService ensaioService;

    @Autowired
    private AmostraService amostraService;

    /**
     * Método HTTP que cadastra um ensaio na base de dados
     * @param ensaioDto Dados do ensaio a ser cadastrado
     * @return Entidadde de resposta com o ensaio cadastrado
     */
    @PostMapping
    public ResponseEntity<Ensaio> cadastrarEnsaio(@RequestBody EnsaioDto ensaioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ensaioService.cadastrarEnsaio(ensaioDto));
    }

    /**
     * Método HTTP que retorna uma lista de todos os ensaios cadastrados na base de dados
     * @return Entidade de resposta com lista de ensaios
     */
    @GetMapping
    public ResponseEntity<List<Ensaio>> retornarTodosEnsaios(){
        return ResponseEntity.status(HttpStatus.OK).body(ensaioService.listarEnsaios());
    }

    /**
     * Método HTTP que retorna um ensaio a partir de seu id
     * @param idEnsaio Id do ensaio
     * @return Entidade de resposta com o ensaio
     */
    @GetMapping("/{idEnsaio}")
    public ResponseEntity<Ensaio> retornarEnsaioPorId(@PathVariable String idEnsaio){
        return ResponseEntity.status(HttpStatus.OK).body(ensaioService.procurarEnsaio(idEnsaio));
    }

    /**
     * Método HTTP que retorna uma lista de ensaios a partir de uma amostra
     * @param idAmostra Id da Amostra
     * @return Lista de Ensaio
     */
    @GetMapping("/por-amostra/{idAmostra}")
    public ResponseEntity<List<Ensaio>> retornarEnsaioPorAmostra(@PathVariable Long idAmostra){
        return ResponseEntity.status(HttpStatus.OK).body(ensaioService.procurarEnsaiosPorAmostra(idAmostra));
    }

    /**
     * Método HTTP que cadastra o resultado do ensaio
     * @param resultado Resultado do ensaio
     * @return Entidade de resposta com o ensaio atualizado
     */
    @PutMapping("/{idEnsaio}")
    public ResponseEntity<Ensaio> resultadoDoEnsaio(@PathVariable String idEnsaio, @RequestBody EnsaioDto resultado){
        return ResponseEntity.status(HttpStatus.OK).body(ensaioService.resultadoEnsaio(idEnsaio, resultado));
    }
}
