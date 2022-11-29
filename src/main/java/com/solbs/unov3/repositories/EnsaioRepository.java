package com.solbs.unov3.repositories;

import com.solbs.unov3.entities.Ensaio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnsaioRepository extends JpaRepository<Ensaio, String> {
    /**
     * Método query que retorna uma lista de ensaios a partir de sua amostra
     * @param id Id da amostra
     * @return Lista de ensaios
     */
    @Query(value = "SELECT * FROM TB_ENSAIO WHERE ID_AMOSTRA = ?1", nativeQuery = true)
    List<Ensaio> findEnsaioByAmostra(Long id);
}
