package com.solbs.unov3.services;

import com.solbs.unov3.dtos.UsuarioDto;
import com.solbs.unov3.entities.Cargo;
import com.solbs.unov3.entities.Usuario;
import com.solbs.unov3.repositories.CargoRepository;
import com.solbs.unov3.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CargoRepository cargoRepository;

    /**
     * Método que cadasra um usuário
     * @param usuarioDto Dados do usuário que será cadastrado
     * @return Caso email já esteja cadastrado será retornado uma mensagem informando que já está cadastrado e o status de conflito, se não será retornado o usuário cadastrado
     */
    public Object cadastrarUsuario(UsuarioDto usuarioDto) {
        if (usuarioRepository.existsByEmail(usuarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        Cargo cargo = cargoRepository.findById(usuarioDto.getCargo()).get();
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuario.setCargo(cargo);
        return usuarioRepository.save(usuario);
    }

    /**
     * Método que procura todos os usuários na base de dados
     * @return Lista de usuários
     */
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    /**
     * Método que procura um usuário a partir de seu id na base de dados
     * @param idUsuario Id do usuário a ser procurado
     * @return Usuario
     */
    public Usuario procurarUsuario(String idUsuario){
        return usuarioRepository.findById(idUsuario).get();
    }

    /**
     * Método que deleta um usuário
     * @param idUsuario Id do usuário a ser deletado
     */
    @Transactional
    public void deletarUsuario(String idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        usuarioRepository.delete(usuario);
    }

    /**
     * Método que altera senha de um usuário
     * @param idUsuario Id do usuário cuja senha será alterada
     * @param novaSenha Nova senha
     * @return Usuário atualizado
     */
    public Usuario alterarSenha(String idUsuario, String novaSenha){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        return usuarioRepository.save(usuario);
    }

}
