package test.java.br.com.caelum.leilao.dominio;

import main.java.br.com.caelum.leilao.dominio.Lance;
import main.java.br.com.caelum.leilao.dominio.Leilao;
import main.java.br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Teste unitário - Leilão")
public class LeilaoTest {
    private Leilao leilao;
    private Usuario joao;
    private Usuario jose;

    @BeforeEach
    void setUp(){
        leilao = new Leilao("Ar Condicionado 9000");
        joao = new Usuario("João");
        jose = new Usuario("José");
    }

    @Test
    @DisplayName("deve adicionar um lance ao leilão")
    void adicionaUmLance(){
        leilao.propoe(new Lance(joao, 200.0));

        List<Lance> lances = leilao.getLances();

        assertEquals(1, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
    }

    @Test
    @DisplayName("deve adicionar dois lances ao leilão")
    void adicionaDoisLances(){
        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(jose, 300.0));

        List<Lance> lances = leilao.getLances();

        assertEquals(2, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
        assertEquals(300.0, lances.get(1).getValor());
    }

    @Test
    @DisplayName("deve ignorar dois lances seguidos de um mesmo usuário")
    void ignoraLancesSeguidos(){
        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(joao, 300.0));

        List<Lance> lances = leilao.getLances();

        assertEquals(1, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
    }

    @Test
    @DisplayName("deve considerar apenas primeiros cinco lances de um mesmo usuário")
    void consideraPrimeirosCinco(){
        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 500.0));
        leilao.propoe(new Lance(joao, 600.0));
        leilao.propoe(new Lance(jose, 700.0));
        leilao.propoe(new Lance(joao, 800.0));
        leilao.propoe(new Lance(jose, 900.0));
        leilao.propoe(new Lance(joao, 1000.0));
        leilao.propoe(new Lance(jose, 1100.0));

        //ignora sexto lance
        leilao.propoe(new Lance(joao, 1200.0));

        List<Lance> lances = leilao.getLances();

        assertEquals(10, lances.size());
        assertEquals(1100.0, lances.get(9).getValor());
    }

    @Test
    @DisplayName("deve dobrar último lance")
    void dobraUltimoLance(){
        leilao.propoe(new Lance(joao, 200.0));
        leilao.dobraUltimoLance(jose);

        List<Lance> lances = leilao.getLances();

        assertEquals(2, lances.size());
        assertEquals(400.0, lances.get(1).getValor());
        assertEquals(jose, lances.get(1).getUsuario());
    }

    @Test
    @DisplayName("dispara exceção na tentativa de dobrar último lance de um leilão sem lances")
    void dobraUltimoLanceSemLances(){
        Executable tentativaDeDobrarSemLances = () -> leilao.dobraUltimoLance(jose);
        assertThrows(RuntimeException.class, tentativaDeDobrarSemLances, "Não é possível dobrar último lance de leilão sem lances");
    }

    @Test
    @DisplayName("não deve dobrar último lance caso seja o mesmo usuário")
    void dobraUltimoLanceMesmoUsuario(){
        leilao.propoe(new Lance(joao, 200.0));
        leilao.dobraUltimoLance(joao);

        List<Lance> lances = leilao.getLances();

        assertEquals(1, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
        assertEquals(joao, lances.get(0).getUsuario());
    }

    @Test
    @DisplayName("não deve dobrar último lance caso usuário já tenha dado cinco lances")
    void dobraUltimoLanceComCincoLances(){
        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 500.0));
        leilao.propoe(new Lance(joao, 600.0));
        leilao.propoe(new Lance(jose, 700.0));
        leilao.propoe(new Lance(joao, 800.0));
        leilao.propoe(new Lance(jose, 900.0));
        leilao.propoe(new Lance(joao, 1000.0));
        leilao.propoe(new Lance(jose, 1100.0));

        //ignora tentativa de dobra do último lance
        leilao.dobraUltimoLance(joao);

        List<Lance> lances = leilao.getLances();

        assertEquals(10, lances.size());
        assertEquals(1100.0, lances.get(9).getValor());
        assertEquals(jose, lances.get(9).getUsuario());
    }
}
