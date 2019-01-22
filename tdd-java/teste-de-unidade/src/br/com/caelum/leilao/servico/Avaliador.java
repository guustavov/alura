package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.Comparator;
import java.util.Optional;

public class Avaliador {

    private Optional<Lance> maiorLance;

    public void avalia(Leilao leilao){
        maiorLance = leilao.getLances().stream().max(Comparator.comparing(Lance::getValor));
    }

    public Lance getMaiorLance() {
        return maiorLance.orElseThrow(RuntimeException::new);
    }
}
