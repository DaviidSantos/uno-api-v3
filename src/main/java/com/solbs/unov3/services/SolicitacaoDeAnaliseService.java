package com.solbs.unov3.services;

import com.solbs.unov3.dtos.SolicitacaoDeAnaliseDto;
import com.solbs.unov3.entities.SolicitacaoDeAnalise;
import com.solbs.unov3.entities.Solicitante;
import com.solbs.unov3.repositories.SolicitacaoDeAnaliseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SolicitacaoDeAnaliseService {
    @Autowired
    private SolicitacaoDeAnaliseRepository solicitacaoDeAnaliseRepository;

    @Autowired
    private SolicitanteService solicitanteService;

    /**
     * Método que cadastra uma Solicitação de Análise na base de dados
     * @param solicitacaoDeAnaliseDto Dados da Solicitação de Análise
     * @return Solicitação de Análise cadastrada
     */
    @Transactional
    public SolicitacaoDeAnalise cadastrarSolicitacaoDeAnalise(SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto) {
        SolicitacaoDeAnalise solicitacaoDeAnalise = new SolicitacaoDeAnalise();
        BeanUtils.copyProperties(solicitacaoDeAnaliseDto, solicitacaoDeAnalise);
        Solicitante solicitante = solicitanteService.procurarSolicitante(solicitacaoDeAnaliseDto.getCnpj());;
        solicitacaoDeAnalise.setSolicitante(solicitante);
        return solicitacaoDeAnaliseRepository.save(solicitacaoDeAnalise);
    }

    /**
     * Método que retorna uma lista com todas as Solicitações de Análise
     * @return Lista de Solicitações de Análise
     */
    public List<SolicitacaoDeAnalise> listarSolicitacoesDeAnalise(){
        return solicitacaoDeAnaliseRepository.findAll();
    }

    /**
     * Método que retorna uma Solicitação de Análise a partir de seu ID
     * @param idSA ID da Solicitação de Análise
     * @return Solicitação de Análise
     */
    public SolicitacaoDeAnalise procurarSolicitacaoDeAnalise(String idSA) {
        return solicitacaoDeAnaliseRepository.findById(idSA).get();
    }

    /**
     * Método que retorna uma lista de Solicitações de Análise de um Solicitante
     * @param cnpj Cnpj do Solicitante das Solicitações de Análise
     * @return Lista de Solicitações de Análise
     */
    public List<SolicitacaoDeAnalise> procurarSAPeloSolicitante(String cnpj){
        Solicitante solicitante = solicitanteService.procurarSolicitante(cnpj);
        return solicitacaoDeAnaliseRepository.findSolicitacaoDeAnaliseBySolicitante(solicitante);
    }

    /**
     * Método que atualiza os dados de uma Solicitação de Análise
     * @param idSA Id da Solicitação de Análise que será atualizada
     * @param solicitacaoDeAnaliseDto Dados que serão atualizados
     * @return Solicitação de Análise Atualizada
     */
    @Transactional
    public SolicitacaoDeAnalise atualizarSolicitacaoDeAnalise(String idSA, SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto) {
        SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseRepository.findById(idSA).get();
        return solicitacaoDeAnaliseRepository.save(atualizarDadosSolicitacaoDeAnalise(solicitacaoDeAnalise, solicitacaoDeAnaliseDto));
    }

    /**
     * Método que atualiza os dados de uma Solicitação de Análise
     * @param solicitacaoDeAnalise Solicitação de Análise que terá os dados atualizados
     * @param solicitacaoDeAnaliseDto Dados que serão atualizados na Solicitação de Análise
     * @return Solicitação de Análise Atualizada
     */
    public SolicitacaoDeAnalise atualizarDadosSolicitacaoDeAnalise(SolicitacaoDeAnalise solicitacaoDeAnalise, SolicitacaoDeAnaliseDto solicitacaoDeAnaliseDto) {
        if(solicitacaoDeAnaliseDto.getTipoDeAnalise() != null){
            solicitacaoDeAnalise.setTipoDeAnalise(solicitacaoDeAnaliseDto.getTipoDeAnalise());
        }

        if(solicitacaoDeAnaliseDto.getConsideracoesGerais() != null){
            solicitacaoDeAnalise.setConsideracoesGerais(solicitacaoDeAnaliseDto.getConsideracoesGerais());
        }

        if (solicitacaoDeAnaliseDto.getInformacoesAdicionais() != null){
            solicitacaoDeAnalise.setInformacoesAdicionais(solicitacaoDeAnaliseDto.getInformacoesAdicionais());
        }

        return solicitacaoDeAnalise;
    }
}
