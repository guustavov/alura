package br.com.caelum.leilao.matchers;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {
    private final Lance lance;

    public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }

    @Override
    protected boolean matchesSafely(Leilao leilao) {
        return leilao.getLances().stream().anyMatch((lanceExistente) -> lanceExistente.equals(lance));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("leilão com lance " + lance.getValor());
    }

    public static Matcher<Leilao> possuiLance(Lance lance){
        return new LeilaoMatcher(lance);
    }
}
