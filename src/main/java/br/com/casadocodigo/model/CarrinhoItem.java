package br.com.casadocodigo.model;

import java.math.BigDecimal;

public class CarrinhoItem {

    private Produto produto;
    private TipoPreco tipoPreco;

    public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
        this.produto = produto;
        this.tipoPreco = tipoPreco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TipoPreco getTipoPreco() {
        return tipoPreco;
    }

    public void setTipoPreco(TipoPreco tipoPreco) {
        this.tipoPreco = tipoPreco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarrinhoItem that = (CarrinhoItem) o;

        if (produto != null ? !produto.equals(that.produto) : that.produto != null) return false;
        return tipoPreco == that.tipoPreco;
    }

    @Override
    public int hashCode() {
        int result = produto != null ? produto.hashCode() : 0;
        result = 31 * result + (tipoPreco != null ? tipoPreco.hashCode() : 0);
        return result;
    }

    public BigDecimal getPreco(){
        return produto.getPrecoPorTipoPreco(tipoPreco);
    }

    public BigDecimal getTotal(int quantidade) {
        return this.getPreco().multiply(new BigDecimal(quantidade));
    }
}
