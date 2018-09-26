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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarrinhoItem other = (CarrinhoItem) obj;
        if (produto == null) {
            if (other.produto != null)
                return false;
        } else if (!produto.equals(other.produto))
            return false;
        if (tipoPreco != other.tipoPreco)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        /*int result = produto != null ? produto.hashCode() : 0;
        result = 31 * result + (tipoPreco != null ? tipoPreco.hashCode() : 0);
        return result;*/

        final int prime = 31;
        int result = 1;
        result = prime * result + ((produto == null) ? 0 : produto.hashCode());
        result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
        return result;
    }

    public BigDecimal getPreco(){
        return produto.getPrecoPorTipoPreco(tipoPreco);
    }

    public BigDecimal getTotal(int quantidade) {
        return this.getPreco().multiply(new BigDecimal(quantidade));
    }
}
