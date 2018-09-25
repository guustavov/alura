package br.com.casadocodigo.controller;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.model.Carrinho;
import br.com.casadocodigo.model.CarrinhoItem;
import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

    @Autowired
    private ProdutoDAO produtoDao;

    @Autowired
    private Carrinho carrinho;

    @RequestMapping("/add")
    public ModelAndView add(Integer produtoId, TipoPreco tipoPreco){
        ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
        CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
        
        return modelAndView;
    }

    private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
        Produto produto = produtoDao.find(produtoId);

        CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);

        carrinho.add(carrinhoItem);

        return carrinhoItem;
    }
}
