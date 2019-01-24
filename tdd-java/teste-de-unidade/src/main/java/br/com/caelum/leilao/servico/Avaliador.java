package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Avaliador {

    private Lance maiorLance;
    private Lance menorLance;
    private Double valorMedio;
    private List<Lance> maiores;

    public void avalia(Leilao leilao){

        List<Lance> lances = Optional.of(leilao.getLances()).orElseThrow(() -> new RuntimeException("Não existem lances neste leilão"));

        maiorLance = lances.stream()
                .max(Comparator.comparing(Lance::getValor))
                .orElseThrow(RuntimeException::new);

        menorLance = lances.stream()
                .min(Comparator.comparing(Lance::getValor))
                .orElseThrow(RuntimeException::new);

        valorMedio = lances.stream()
                .mapToDouble(Lance::getValor).average()
                .orElseThrow(RuntimeException::new);

        maiores = lances.stream()
                .sorted(Comparator.comparing(Lance::getValor).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public Lance getMaiorLance() {
        return maiorLance;
    }

    public Lance getMenorLance() {
        return menorLance;
    }

    public Double getValorMedio() {
        return valorMedio;
    }

    public List<Lance> getMaioresLances() {
        return maiores;
    }
}
