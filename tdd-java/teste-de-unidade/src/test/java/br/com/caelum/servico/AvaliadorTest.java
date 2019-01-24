package test.java.br.com.caelum.servico;

import main.java.br.com.caelum.leilao.dominio.Lance;
import main.java.br.com.caelum.leilao.dominio.Leilao;
import main.java.br.com.caelum.leilao.dominio.Usuario;
import main.java.br.com.caelum.leilao.servico.Avaliador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste unitário - Avaliador de Leilão")
class AvaliadorTest {
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

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("deve retornar o maior lance existente")
    void maiorLance() {
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 250.0));

        leiloeiro.avalia(leilao);

        Double valorMaiorLance = leiloeiro.getMaiorLance().getValor();
        assertEquals(400.0, valorMaiorLance);
    }

    @Test
    @DisplayName("deve retornar o menor lance existente")
    void menorLance() {
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 250.0));

        leiloeiro.avalia(leilao);

        Double valorMenorLance = leiloeiro.getMenorLance().getValor();

        assertEquals(250.0, valorMenorLance);
    }

    @Test
    @DisplayName("deve retornar a médio dos valores de lances")
    void valorMedioLances(){
        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 400.0));
        leilao.propoe(new Lance(maria, 300.0));

        leiloeiro.avalia(leilao);

        List<Lance> lances = leilao.getLances();

        Double valorMedioEsperado = lances
                .stream()
                .mapToDouble(Lance::getValor).average()
                .orElseThrow(RuntimeException::new);

        assertEquals(valorMedioEsperado, leiloeiro.getValorMedio());
    }

    @Test
    @DisplayName("deve suportar leilão com apenas um lance")
    void apenasUmLance(){
        leilao.propoe(new Lance(joao, 400.0));

        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance().getValor());
        assertEquals(400.0, leiloeiro.getMenorLance().getValor());
    }

    @Test
    @DisplayName("deve retornar no máximo os três maiores lances")
    void tresMaioresLances(){
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(maria, 100.0));
        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(maria, 300.0));

        leiloeiro.avalia(leilao);

        List<Lance> maioresLances = leiloeiro.getMaioresLances();

        assertEquals(3, maioresLances.size());
        assertEquals(400.00, maioresLances.get(0).getValor());
        assertEquals(300.00, maioresLances.get(1).getValor());
        assertEquals(200.00, maioresLances.get(2).getValor());
    }


    @Test
    @DisplayName("deve retornar os maiores lances, mesmo que existam menos do que três lances")
    void maioresLances(){
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(maria, 100.0));

        leiloeiro.avalia(leilao);

        List<Lance> maioresLances = leiloeiro.getMaioresLances();

        assertEquals(2, maioresLances.size());
        assertEquals(400.00, maioresLances.get(0).getValor());
        assertEquals(100.00, maioresLances.get(1).getValor());
    }

    @Test
    @DisplayName("deve disparar exceção quando não houverem lances")
    void leilaoSemLances(){
        Executable avaliacaoDeLeilaoSemLances = () -> leiloeiro.avalia(leilao);
        assertThrows(RuntimeException.class, avaliacaoDeLeilaoSemLances, "Não existem lances neste leilão");
    }

}