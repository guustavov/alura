package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.LeilaoBuilder;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste unitário - Avaliador de Leilão")
class AvaliadorTest {
    private Avaliador leiloeiro;
    private LeilaoBuilder leilaoBuilder;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @BeforeEach
    void setUp() {
        joao = new Usuario("João");
        jose = new Usuario("José");
        maria = new Usuario("Maria");

        leilaoBuilder = new LeilaoBuilder().leilaoDe("Playstation 4 Novo");

        leiloeiro = new Avaliador();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("deve retornar o maior lance existente")
    void maiorLance() {
        Leilao leilao = leilaoBuilder
            .lance(joao, 400.0)
            .lance(jose, 250.0)
            .build();

        leiloeiro.avalia(leilao);

        Double valorMaiorLance = leiloeiro.getMaiorLance().getValor();

        assertThat(valorMaiorLance, equalTo(400.0));
    }

    @Test
    @DisplayName("deve retornar o menor lance existente")
    void menorLance() {
        Leilao leilao = leilaoBuilder
            .lance(joao, 400.0)
            .lance(jose, 250.0)
            .build();

        leiloeiro.avalia(leilao);

        Double valorMenorLance = leiloeiro.getMenorLance().getValor();

        assertThat(valorMenorLance, equalTo(250.0));
    }

    @Test
    @DisplayName("deve retornar a médio dos valores de lances")
    void valorMedioLances(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 400.0)
            .lance(jose, 250.0)
            .lance(maria, 300.0)
            .build();

        leiloeiro.avalia(leilao);

        List<Lance> lances = leilao.getLances();

        Double valorMedioEsperado = lances
                .stream()
                .mapToDouble(Lance::getValor).average()
                .orElseThrow(RuntimeException::new);

        assertThat(leiloeiro.getValorMedio(), equalTo(valorMedioEsperado));
    }

    @Test
    @DisplayName("deve suportar leilão com apenas um lance")
    void apenasUmLance(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 400.0)
            .build();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance().getValor(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance().getValor(), equalTo(400.0));
    }

    @Test
    @DisplayName("deve retornar no máximo os três maiores lances")
    void tresMaioresLances(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 400.0)
            .lance(maria, 200.0)
            .lance(joao, 100.0)
            .lance(maria, 300.0)
            .build();

        leiloeiro.avalia(leilao);

        List<Lance> maioresLances = leiloeiro.getMaioresLances();

        assertThat(maioresLances.size(), equalTo(3));
        assertThat(maioresLances, hasItems(
                new Lance(joao, 400.0),
                new Lance(maria, 300.0),
                new Lance(maria, 200.0)
        ));
    }


    @Test
    @DisplayName("deve retornar os maiores lances, mesmo que existam menos do que três lances")
    void maioresLances(){
        Leilao leilao = leilaoBuilder
                .lance(joao, 400.0)
                .lance(maria, 100.0)
                .build();

        leiloeiro.avalia(leilao);

        List<Lance> maioresLances = leiloeiro.getMaioresLances();

        assertThat(maioresLances.size(), equalTo(2));
        assertThat(maioresLances, hasItems(
                new Lance(joao, 400.0),
                new Lance(maria, 100.0)
        ));
    }

    @Test
    @DisplayName("deve disparar exceção quando não houverem lances")
    void leilaoSemLances(){
        Leilao leilao = leilaoBuilder.build();

        Executable avaliacaoDeLeilaoSemLances = () -> leiloeiro.avalia(leilao);

        assertThrows(RuntimeException.class, avaliacaoDeLeilaoSemLances, "Não existem lances neste leilão");
    }

}