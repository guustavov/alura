package br.com.casadocodigo.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Carrinho {

    private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

    public void add(CarrinhoItem item){
        itens.put(item, getQuantidade(item)+1)
    }

    private Integer getQuantidade(CarrinhoItem item) {
        if(itens.containsKey(item) )
    }
}
