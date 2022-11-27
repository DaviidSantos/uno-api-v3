package com.solbs.unov3.repositories;

import com.solbs.unov3.entities.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, String> {
    boolean existsByCnpj(String cnpj);
}
