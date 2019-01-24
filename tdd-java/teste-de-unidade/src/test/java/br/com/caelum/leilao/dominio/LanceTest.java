package br.com.caelum.leilao.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Teste unitário - Lance")
class LanceTest {

    private Usuario joao;

    @BeforeEach
    void setUp() {
        joao = new Usuario("João");
    }

    @Test
    @DisplayName("deve disparar exceção quando valor do lance for menor ou igual a zero")
    void lanceInvalido(){
        Executable lanceValorZero = () -> new Lance(joao, 0.0);
        Executable lanceValorNegativo = () -> new Lance(joao, -1.0);

        assertThrows(IllegalArgumentException.class, lanceValorZero);
        assertThrows(IllegalArgumentException.class, lanceValorNegativo);
    }
}