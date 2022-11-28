package com.solbs.unov3.controllers;

import com.solbs.unov3.dtos.SolicitanteDto;
import com.solbs.unov3.entities.Solicitante;
import com.solbs.unov3.services.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/solicitante")
@CrossOrigin("*")
public class SolicitanteController {
    @Autowired
    SolicitanteService solicitanteService;

    /**
     * Método HTTP que cadastra um solicitante na base de dados
     * @param solicitanteDto Dados do solicitante que será cadastrado
     * @return Entidade de resposta com mensagem de conflito com CNPJ cadastrado ou Entidade de resposta com o solicitante cadastrado
     */
    @PostMapping
    public ResponseEntity<Object> cadastrarSolicitante(@RequestBody @Valid SolicitanteDto solicitanteDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitanteService.cadastrarSolicitante(solicitanteDto));
    }

    /**
     * Método HTTP que retorna todos os solicitantes cadastrados
     * @return Lista com todos Solicitantes
     */
    @GetMapping
    public ResponseEntity<List<Solicitante>> listarSolicitantes(){
        return ResponseEntity.status(HttpStatus.OK).body(solicitanteService.listarSolicitantes());
    }

    /**
     * Método HTTP que retorna um solicitante a partir de seu cnpj
     * @param cnpj CNPJ que será buscado na base de dados
     * @return Solicitante
     */
    @GetMapping("/{cnpj}")
    public ResponseEntity<Solicitante> retornarSolicitantePorCnpj(@PathVariable String cnpj){
        return ResponseEntity.status(HttpStatus.OK).body(solicitanteService.procurarSolicitante(cnpj));
    }

    /**
     * Método HTTP que atualiza dados cadastrais de um solicitante
     * @param cnpj CNPJ do solicitante que terá dados atualizados
     * @param dados Dados que serão atualizados no solicitante
     * @return Entidade de resposta HTTP
     */
    @PutMapping("/{cnpj}")
    public ResponseEntity<Object> atualizarSolicitante(@PathVariable String cnpj, @RequestBody SolicitanteDto dados) {
        return ResponseEntity.status(HttpStatus.OK).body(solicitanteService.atualizarSolicitante(cnpj, dados));
    }
}


