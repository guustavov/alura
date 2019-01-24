package test.java.br.com.caelum.leilao.builder;

import main.java.br.com.caelum.leilao.dominio.Lance;
import main.java.br.com.caelum.leilao.dominio.Leilao;
import main.java.br.com.caelum.leilao.dominio.Usuario;

public class LeilaoBuilder {
    private Leilao leilao;

    public LeilaoBuilder leilaoDe(String descricao) {
        leilao = new Leilao(descricao);
        return this;
    }

    public LeilaoBuilder lance(Usuario usuario, double valor) {
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao build() {
        return leilao;
    }
}
