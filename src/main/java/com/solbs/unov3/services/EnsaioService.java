package com.solbs.unov3.services;

import com.solbs.unov3.entities.Ensaio;
import com.solbs.unov3.repositories.EnsaioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnsaioService {
    @Autowired
    private EnsaioRepository ensaioRepository;

    /**
     * Método que salva o ensaio na base de dados
     * @param ensaio Ensaio a ser salvo
     * @return Ensaio salvo
     */
    @Transactional
    public Ensaio salvarEnsaio(Ensaio ensaio) {
        return ensaioRepository.save(ensaio);
    }

    /**
     * Método que retorna uma lista com todos os ensaios cadastrados
     * @return Lista de ensaios
     */
    public List<Ensaio> procurarTodosOsEnsaios(){
        return ensaioRepository.findAll();
    }

    /**
     * Método que retorna um ensaio a partir de seu id
     * @param id Id do ensaio
     * @return Ensaio
     */
    public Ensaio procurarEnsaioPeloId(String id) {
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
