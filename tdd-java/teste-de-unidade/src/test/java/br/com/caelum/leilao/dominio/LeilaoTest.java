package br.com.caelum.leilao.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import br.com.caelum.leilao.builder.LeilaoBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Teste unitário - Leilão")
public class LeilaoTest {
    private LeilaoBuilder leilaoBuilder;
    private Usuario joao;
    private Usuario jose;

    @BeforeEach
    void setUp(){
        leilaoBuilder = new LeilaoBuilder().leilaoDe("Ar Condicionado 9000");
        joao = new Usuario("João");
        jose = new Usuario("José");
    }

    @Test
    @DisplayName("deve adicionar um lance ao leilão")
    void adicionaUmLance(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 200.0)
            .build();

        List<Lance> lances = leilao.getLances();

        assertEquals(1, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
    }

    @Test
    @DisplayName("deve adicionar dois lances ao leilão")
    void adicionaDoisLances(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 200.0)
            .lance(jose, 300.0)
            .build();

        List<Lance> lances = leilao.getLances();

        assertEquals(2, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
        assertEquals(300.0, lances.get(1).getValor());
    }

    @Test
    @DisplayName("deve ignorar dois lances seguidos de um mesmo usuário")
    void ignoraLancesSeguidos(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 200.0)
            .lance(joao, 300.0)
            .build();

        List<Lance> lances = leilao.getLances();

        assertEquals(1, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
    }

    @Test
    @DisplayName("deve considerar apenas primeiros cinco lances de um mesmo usuário")
    void consideraPrimeirosCinco(){
        Leilao leilao = leilaoComUsuarioImpossibilitadoDeDarLances();

        //ignora sexto lance
        leilao.propoe(new Lance(joao, 1200.0));

        List<Lance> lances = leilao.getLances();

        assertEquals(10, lances.size());
        assertEquals(1100.0, lances.get(9).getValor());
    }

    @Test
    @DisplayName("deve dobrar último lance")
    void dobraUltimoLance(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 200.0)
            .build();

        leilao.dobraUltimoLance(jose);

        List<Lance> lances = leilao.getLances();

        assertEquals(2, lances.size());
        assertEquals(400.0, lances.get(1).getValor());
        assertEquals(jose, lances.get(1).getUsuario());
    }

    @Test
    @DisplayName("dispara exceção na tentativa de dobrar último lance de um leilão sem lances")
    void dobraUltimoLanceSemLances(){
        Leilao leilao = leilaoBuilder.build();
        Executable tentativaDeDobrarSemLances = () -> leilao.dobraUltimoLance(jose);
        assertThrows(RuntimeException.class, tentativaDeDobrarSemLances, "Não é possível dobrar último lance de leilão sem lances");
    }

    @Test
    @DisplayName("não deve dobrar último lance caso seja o mesmo usuário")
    void dobraUltimoLanceMesmoUsuario(){
        Leilao leilao = leilaoBuilder
            .lance(joao, 200.0)
            .build();

        leilao.dobraUltimoLance(joao);

        List<Lance> lances = leilao.getLances();

        assertEquals(1, lances.size());
        assertEquals(200.0, lances.get(0).getValor());
        assertEquals(joao, lances.get(0).getUsuario());
    }

    @Test
    @DisplayName("não deve dobrar último lance caso usuário já tenha dado cinco lances")
    void dobraUltimoLanceComCincoLances(){
        Leilao leilao = leilaoComUsuarioImpossibilitadoDeDarLances();

        //ignora tentativa de dobra do último lance
        leilao.dobraUltimoLance(joao);

        List<Lance> lances = leilao.getLances();

        assertEquals(10, lances.size());
        assertEquals(1100.0, lances.get(9).getValor());
        assertEquals(jose, lances.get(9).getUsuario());


    }

    private Leilao leilaoComUsuarioImpossibilitadoDeDarLances(){
        return leilaoBuilder
                .lance(joao, 200.0)
                .lance(jose, 300.0)
                .lance(joao, 400.0)
                .lance(jose, 500.0)
                .lance(joao, 600.0)
                .lance(jose, 700.0)
                .lance(joao, 800.0)
                .lance(jose, 900.0)
                .lance(joao, 1000.0)
                .lance(jose, 1100.0)
                .build();
    }
}
