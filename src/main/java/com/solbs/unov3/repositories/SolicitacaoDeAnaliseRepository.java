package com.solbs.unov3.repositories;

import com.solbs.unov3.entities.SolicitacaoDeAnalise;
import com.solbs.unov3.entities.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoDeAnaliseRepository extends JpaRepository<SolicitacaoDeAnalise, String> {
    List<SolicitacaoDeAnalise> findSolicitacaoDeAnaliseBySolicitante(Solicitante solicitante);
}
