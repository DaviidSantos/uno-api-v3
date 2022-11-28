package com.solbs.unov3.services;

import com.solbs.unov3.dtos.EnsaioDto;
import com.solbs.unov3.entities.Amostra;
import com.solbs.unov3.entities.Ensaio;
import com.solbs.unov3.repositories.EnsaioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnsaioService {
    @Autowired
    private EnsaioRepository ensaioRepository;

    @Autowired
    private AmostraService amostraService;

    /**
     * Método que cadastra um ensaio na base de dados
     * @param ensaioDto
     * @return
     */
    @Transactional
    public Ensaio cadastrarEnsaio(EnsaioDto ensaioDto) {
        Ensaio ensaio = new Ensaio();
        Amostra amostra = amostraService.procurarAmostra(ensaioDto.getAmostra());
        BeanUtils.copyProperties(ensaioDto, ensaio);
        ensaio.setAmostra(amostra);
        return ensaioRepository.save(ensaio);
    }

    /**
     * Método que cadastra um resultado de amostra
     * @param idEnsaio Id do ensaio que terá resultado
     * @param resultado Resultado do ensaio
     * @return Ensaio salvo
     */
    @Transactional
    public Ensaio resultadoEnsaio(String idEnsaio, EnsaioDto resultado) {
        Ensaio ensaio = ensaioRepository.findById(idEnsaio).get();
        ensaio.setResultadoDoEnsaio(resultado.getResultadoDoEnsaio());
        return ensaioRepository.save(ensaio);
    }

    /**
     * Método que retorna uma lista com todos os ensaios cadastrados
     * @return Lista de ensaios
     */
    public List<Ensaio> listarEnsaios(){
        return ensaioRepository.findAll();
    }

    /**
     * Método que retorna um ensaio a partir de seu id
     * @param id Id do ensaio
     * @return Ensaio
     */
    public Ensaio procurarEnsaio(String id) {
        return ensaioRepository.findById(id).get();
    }

    /**
     * Método que retorna uma lista de ensaios cadastrados a uma amostra
     * @param idAmostra Amostra que terá seus ensaios retornados
     * @return Lista de Ensaios
     */
    public List<Ensaio> procurarEnsaiosPorAmostra(Long idAmostra) {
        return ensaioRepository.findEnsaioByAmostra(idAmostra);
    }

}
