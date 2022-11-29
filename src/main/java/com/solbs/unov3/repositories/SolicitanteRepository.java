package com.solbs.unov3.repositories;

import com.solbs.unov3.entities.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, String> {
    /**
     * Método que verifica se um solicitante com um devido cpf já está cadastrado
     * @param cnpj cnpj que fará parte da consulta
     * @return True or False
     */
    boolean existsByCnpj(String cnpj);
}
