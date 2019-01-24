package br.com.caelum.leilao.servico;

import main.java.br.com.caelum.leilao.servico.AnoBissexto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Teste Unitário - Ano Bissexto")
class AnoBissextoTest {

    private AnoBissexto bissexto;

    @BeforeEach
    void setUp(){
        bissexto = new AnoBissexto();
    }

    @Test
    @DisplayName("deve retornar verdadeiro para ano divisível por 400")
    void divisivelPor400(){
        assertTrue(bissexto.ehBissexto(2000));
        assertTrue(bissexto.ehBissexto(2400));
    }

    @Test
    @DisplayName("deve retornar verdadeiro para ano divisível por 4 e não divisível por 100")
    void divisivelPor4NaoPor100(){
        assertTrue(bissexto.ehBissexto(2020));
        assertTrue(bissexto.ehBissexto(2024));
    }

    @Test
    @DisplayName("deve retornar falso para ano divisível por 100 e 4 e não divisível por 400")
    void divisivelPor100e4NaoPor400(){
        assertFalse(bissexto.ehBissexto(2100));
        assertFalse(bissexto.ehBissexto(2200));
    }
}
