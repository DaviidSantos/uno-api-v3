package com.solbs.unov3.controllers;

import com.solbs.unov3.entities.Amostra;
import com.solbs.unov3.entities.SolicitacaoDeAnalise;
import com.solbs.unov3.entities.Solicitante;
import com.solbs.unov3.services.RelatorioDeAnaliseService;
import com.solbs.unov3.services.SolicitacaoDeAnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("/gerar-relatorio")
@CrossOrigin("*")
public class RelatorioDeAnaliseController {

    @Autowired
    RelatorioDeAnaliseService relatorioDeAnaliseService;

    @Autowired
    SolicitacaoDeAnaliseService solicitacaoDeAnaliseService;

    @GetMapping("/{idSA}")
    public void gerarRelatorioDeAnalise(@PathVariable String idSA, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + idSA + ".pdf";
        response.setHeader(headerKey, headerValue);
        SolicitacaoDeAnalise solicitacaoDeAnalise = solicitacaoDeAnaliseService.procurarSolicitacaoDeAnalise(idSA);
        Solicitante solicitante = solicitacaoDeAnalise.getSolicitante();
        Set<Amostra> amostras = solicitacaoDeAnalise.getAmostras();
        relatorioDeAnaliseService.export(response, solicitante, solicitacaoDeAnalise, amostras);
    }
}
