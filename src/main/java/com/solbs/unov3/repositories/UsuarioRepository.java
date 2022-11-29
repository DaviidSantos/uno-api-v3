package com.solbs.unov3.repositories;

import com.solbs.unov3.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    /**
     * Método que retorna um optional de um usuário a partir de seu email
     * @param email email do usuário
     * @return Optional do Usuário
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Método verifica se um devido email já está cadastrado
     * @param email Email a ser verificado
     * @return True or False
     */

    boolean existsByEmail(String email);
}
