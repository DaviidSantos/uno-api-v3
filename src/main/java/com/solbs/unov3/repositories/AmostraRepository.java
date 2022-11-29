package com.solbs.unov3.repositories;

import com.solbs.unov3.entities.Amostra;
import com.solbs.unov3.entities.SolicitacaoDeAnalise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, String> {
    /**
     * Método query que retorna uma lista de amostras com status de análise finalizada
     * @return Lista de Amostras
     */
    @Query(value = "SELECT * FROM TB_AMOSTRA WHERE STATUS_AMOSTRA = 0", nativeQuery = true)
    List<Amostra> findAmostrasAnaliseFinalizada();

    /**
     * Método query que retorna uma lista de amostras com status de amostra em análise
     * @return Lista de Amostras
     */
    @Query(value = "SELECT * from TB_AMOSTRA WHERE STATUS_AMOSTRA = 1 ", nativeQuery = true)
    List<Amostra> findAmostraEmAnalise();

    /**
     * Método query que retorna uma lista de amostras com status aguardando análise
     * @return Lista de amostra
     */
    @Query(value = "SELECT * FROM TB_AMOSTRA WHERE STATUS_AMOSTRA = 2", nativeQuery = true)
    List<Amostra> findAmostraAguardandoAnalise();

    /**
     * Método query que retorna uma lista de amostras com status amostra em falta
     * @return Lista de amostra
     */
    @Query(value = "SELECT * FROM TB_AMOSTRA WHERE STATUS_AMOSTRA = 3", nativeQuery = true)
    List<Amostra> findAmostraEmFalta();

    /**
     * Método query que a quantidade de amostras em status análise finalizada
     * @return Quantidade de amostras
     */
    @Query(value = "SELECT count(*) FROM TB_AMOSTRA WHERE STATUS_AMOSTRA = 0", nativeQuery = true)
    int quantidadeFinalizada();

    /**
     * Método query que a quantidade de amostras em status em análise
     * @return Quantidade de amostras
     */
    @Query(value = "SELECT count(*) from TB_AMOSTRA WHERE STATUS_AMOSTRA = 1 ", nativeQuery = true)
    int quantidadeEmAnalise();

    /**
     * Método query que a quantidade de amostras em status aguardando análise
     * @return Quantidade de amostras
     */
    @Query(value = "SELECT count(*) FROM TB_AMOSTRA WHERE STATUS_AMOSTRA = 2", nativeQuery = true)
    int quantidadeAguardandoAnalise();

    /**
     * Método que retorna uma lista de amostras a partida de uma solicitação de análise
     * @param solicitacaoDeAnalise Solicitação de Análise que será parte do select da amostra
     * @return Lista de Amostras
     */
    List<Amostra> findAmostraBySolicitacaoDeAnalise(SolicitacaoDeAnalise solicitacaoDeAnalise);
}
