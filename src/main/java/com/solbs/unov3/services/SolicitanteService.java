package com.solbs.unov3.services;

import com.solbs.unov3.dtos.SolicitanteDto;
import com.solbs.unov3.entities.Solicitante;
import com.solbs.unov3.repositories.SolicitanteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SolicitanteService {
    @Autowired
    SolicitanteRepository solicitanteRepository;

    /**
     * Método que cadastra um solicitante na base de dados
     * @param solicitanteDto Dados do solicitante que será cadastrado
     * @return Caso cnpj já esteja cadastrado uma mensagem de erro com o status de conflito será retornado, caso contrário será retornado o solicitante
     */
    public Object cadastrarSolicitante(SolicitanteDto solicitanteDto) {
        if (solicitanteRepository.existsByCnpj(solicitanteDto.getCnpj())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CNPJ já cadastrado");
        }
        Solicitante solicitante = new Solicitante();
        BeanUtils.copyProperties(solicitanteDto, solicitante);
        return solicitanteRepository.save(solicitante);
    }

    /**
     * Método que retorna uma lista com todos os solicitantes
     * @return Lista de Solicitantes
     */
    public List<Solicitante> listarSolicitantes(){
        return solicitanteRepository.findAll();
    }

    /**
     * Método que retorna um solicitante a partir de seu CNPJ
     * @param cnpj CNPJ do solicitante
     * @return Solicitantee
     */
    public Solicitante procurarSolicitante(String cnpj){
        return solicitanteRepository.findById(cnpj).get();
    }

    /**
     * Método que atualiza os dados de um solicitante na base de dados
     * @param cnpj Cnpj do solicitante que será atualizado
     * @param dados Dados que serão atualizados
     * @return Solicitante atualizado
     */
    @Transactional
    public Object atualizarSolicitante(String cnpj, SolicitanteDto dados) {
        Solicitante solicitante = solicitanteRepository.findById(cnpj).get();
        return solicitanteRepository.save(atualizarDados(solicitante, dados));
    }

    /**
     * Método que atualiza os dados de um solicitante
     * @param solicitante Solicitante que terá os dados atualizados
     * @param dados Dados do solicitante que serão atualizados
     * @return Solicitante atualizado
     */
    private Solicitante atualizarDados(Solicitante solicitante, SolicitanteDto dados) {
        if (dados.getNomeFantasia() != null) {
            solicitante.setNomeFantasia(dados.getNomeFantasia());
        }

        if (dados.getCep() != null) {
            solicitante.setCep(dados.getCep());
        }

        if (dados.getEndereco() != null) {
            solicitante.setEndereco(dados.getEndereco());
        }

        if(dados.getCidade() != null){
            solicitante.setCidade(dados.getCidade());
        }

        if (dados.getEstado() != null) {
            solicitante.setEstado(dados.getEstado());
        }

        if (dados.getEmailComercial() != null) {
            solicitante.setEmailComercial(dados.getEmailComercial());
        }

        if (dados.getTelefoneComercial() != null) {
            solicitante.setTelefoneComercial(dados.getTelefoneComercial());
        }
        return solicitante;
    }
}
