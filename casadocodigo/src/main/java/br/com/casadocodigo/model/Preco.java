package br.com.casadocodigo.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Preco {

    private BigDecimal valor;
    private TipoPreco tipo;

    public TipoPreco getTipo() {
        return tipo;
    }

    public void setTipo(TipoPreco tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
