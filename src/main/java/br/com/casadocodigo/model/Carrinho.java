package br.com.casadocodigo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class Carrinho {

    private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();

    public void add(CarrinhoItem item){
        itens.put(item, getQuantidade(item)+1);
    }

    private Integer getQuantidade(CarrinhoItem item) {
        if(!itens.containsKey(item) ){
            itens.put(item, 0);
        }
        return itens.get(item);
    }

    public int getQuantidade(){
        return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
    }
}
