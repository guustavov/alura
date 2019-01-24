package test.java.br.com.caelum.servico;

import main.java.br.com.caelum.leilao.dominio.Lance;
import main.java.br.com.caelum.leilao.dominio.Leilao;
import main.java.br.com.caelum.leilao.dominio.Usuario;
import main.java.br.com.caelum.leilao.servico.Avaliador;
import main.java.br.com.caelum.leilao.servico.FiltroDeLances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste unitário - Filtro de Lances")
class FiltroDeLancesTest {
    private Avaliador leiloeiro;
    private Leilao leilao;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @BeforeEach
    void setUp() {
        joao = new Usuario("João");
        jose = new Usuario("José");
        maria = new Usuario("Maria");

        leilao = new Leilao("Playstation 4 Novo");

        leiloeiro = new Avaliador();
    }


    @Test
    @DisplayName("deve selecionar lances entre 1000 e 3000")
    void deveSelecionarLancesEntre1000E3000() {

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,2000),
                new Lance(joao,1000),
                new Lance(joao,3000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor());
    }

    @Test
    @DisplayName("deve selecionar lances entre 500 e 700")
    void deveSelecionarLancesEntre500E700() {

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,600),
                new Lance(joao,500),
                new Lance(joao,700),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0).getValor());
    }

    @Test
    @DisplayName("deve selecionar lances maiores que 5000")
    void deveSelecionarLancesMaioresQue5000() {

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,10000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(10000, resultado.get(0).getValor());
    }

    @Test
    @DisplayName("não deve selecionar menores que 500")
    void deveEliminarMenoresQue500() {

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,400),
                new Lance(joao, 300)));

        assertEquals(0, resultado.size());
    }

    @Test
    @DisplayName("não deve selecionar entre 700 e 1000")
    void deveEliminarEntre700E1000() {

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 800),
                new Lance(joao, 1000),
                new Lance(joao, 700),
                new Lance(joao, 900)));

        assertEquals(0, resultado.size());
    }

    @Test
    @DisplayName("não deve selecionar entre 3000 e 5000")
    void deveEliminarEntre3000E5000() {

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,4000),
                new Lance(joao, 3500)));

        assertEquals(0, resultado.size());
    }
}
