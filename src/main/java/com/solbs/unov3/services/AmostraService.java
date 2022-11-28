package com.solbs.unov3.services;

import com.solbs.unov3.dtos.AmostraDto;
import com.solbs.unov3.dtos.QuantidadeDeAmostraPorStatusDto;
import com.solbs.unov3.entities.Amostra;
import com.solbs.unov3.entities.SolicitacaoDeAnalise;
import com.solbs.unov3.entities.enums.StatusAmostra;
import com.solbs.unov3.repositories.AmostraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class AmostraService {
    @Autowired
    private AmostraRepository amostraRepository;

    @Autowired
    private SolicitacaoDeAnaliseService solicitacaoDeAnaliseService;

    /**
     * Método que cadastra uma amostra na base de dados
     * @param amostraDto Dados da amostra que será cadastrada
     * @return Amostra cadastrada
     */
    @Transactional
    public Amostra cadastrarAmostra(AmostraDto amostraDto) {
        Amostra amostra = new Amostra();
        SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseService.procurarSolicitacaoDeAnalise(amostraDto.getSolicitacaoDeAnalise());
        BeanUtils.copyProperties(amostraDto, amostra);
        StatusAmostra statusAmostra = StatusAmostra.Aguardando_Análise;
        amostra.setStatusAmostra(statusAmostra);
        amostra.setSolicitacaoDeAnalise(solicitacaoDeAnalise);
        amostra.setDataDeEntrada(Instant.now());
        return amostraRepository.save(amostra);
    }

    /**
     * Método que atualiza o status de uma amostra
     * @param idAmostra Id da amostra que será atualizada
     * @param status código do status
     * @return Amostra atualizada
     */
    @Transactional
    public Amostra atualizarStatus(String idAmostra, int status) {
        Amostra amostra = amostraRepository.findById(idAmostra).get();
        StatusAmostra statusAmostra = StatusAmostra.valor(status);
        amostra.setStatusAmostra(statusAmostra);
        return amostra;
    }

    /**
     * Método que retorna todas as amostras cadastradas
     * @return Lista de amostras
     */
    public List<Amostra> listarAmostrar(){
        return amostraRepository.findAll();
    }

    /**
     * Método que retorna uma amostra a partir do id
     * @param idAmostra Id da amostra
     * @return Amostra
     */
    public Amostra procurarAmostra(String idAmostra) {
        return amostraRepository.findById(idAmostra).get();
    }

    /**
     * Método que retorna todas as amostras com status: análise finalizada
     * @return Lista de amostras com analise finalizada
     */
    public List<Amostra> procurarAmostrasFinalizadas() {
        return amostraRepository.findAmostrasAnaliseFinalizada();
    }

    /**
     * Método que retorna todas as amostras com status: em análise
     * @return Lista de amostras em análise
     */
    public List<Amostra> procurarAmostrasEmAnalise(){
        return amostraRepository.findAmostraEmAnalise();
    }

    /**
     * Método que retorna todas as amostras com status: aguardando análise
     * @return Lista de amostras aguardando análise
     */
    public List<Amostra> procurarAmostrasAguardandoAnalise() {
        return amostraRepository.findAmostraAguardandoAnalise();
    }

    /**
     * Método que retorna todas as amostras com status: em falta
     * @return Lista de amostras em falta
     */
    public List<Amostra> procurarAmostrasEmFalta() {
        return amostraRepository.findAmostraEmFalta();
    }

    /**
     * Método que retorna todas as amostras de uma solicitação de análise
     * @param solicitacaoDeAnalise Solicitação de Análise
     * @return Lista de Amostras
     */
    public List<Amostra> procurarAmostraPorSolicitacaoDeAnalise(SolicitacaoDeAnalise solicitacaoDeAnalise){
        return amostraRepository.findAmostraBySolicitacaoDeAnalise(solicitacaoDeAnalise);
    }

    /**
     * Método que retorna a quatidade de amostras em cada Status
     * @return QuantidadeStatusAmostra
     */
    public QuantidadeDeAmostraPorStatusDto retornarQuantidadeAmostraPorStatus(){
        QuantidadeDeAmostraPorStatusDto quantidadeStatusAmostra = new QuantidadeDeAmostraPorStatusDto();
        quantidadeStatusAmostra.setAmostraFinalizada(amostraRepository.quantidadeFinalizada());
        quantidadeStatusAmostra.setAmostraEmAnalise(amostraRepository.quantidadeEmAnalise());
        quantidadeStatusAmostra.setAmostraAguardandoAnalise(amostraRepository.quantidadeAguardandoAnalise());
        return quantidadeStatusAmostra;
    }
}
