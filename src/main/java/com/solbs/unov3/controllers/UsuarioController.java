package com.solbs.unov3.controllers;

import com.solbs.unov3.dtos.UsuarioDto;
import com.solbs.unov3.entities.Usuario;

import com.solbs.unov3.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Método HTTP que cadastra um usuário na base de dados
     * @param usuarioDto Dados do usuário que será cadastrado
     * @return Entidade de Resposta com Usuário cadastrado
     */
    @PostMapping
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuarioDto));
    }

    /**
     * Método HTTP que retorna todos os usuários cadastrados na base de dados
     * @return Entidade de Resposta com Lista de Usuários Cadastrados
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    /**
     * Método HTTP que retora um usuário a partir de seu id
     * @param idUsuario Id do Usuário que será retornado
     * @return Entidade de Resposta com Usuário
     */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> procurarUsuario(@PathVariable String idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.procurarUsuario(idUsuario));
    }

    /**
     * Método HTTP que deleta um usuário da base de dados
     * @param idUsuario Id do Usuário que será deletado
     * @return Entidade de Resposta
     */
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable String idUsuario){
        usuarioService.deletarUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário Deletado com Sucesso!");
    }

    /**
     * Método HTTP que altera a senha de um usuário
     * @param novaSenha Nova senha do usuário
     * @param idUsuario Id do usuário cuja senha será alteraa
     * @return Entidade de Resposta com usuário atualizado
     */
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> alterarSenha(@RequestBody String novaSenha, @PathVariable String idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.alterarSenha(idUsuario, novaSenha));
    }

}
