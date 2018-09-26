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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
        CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
        
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView itens(){
        return new ModelAndView("/carrinho/itens");
    }

    @RequestMapping(value = "/remover")
    public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco, RedirectAttributes redirectAttributes){
        carrinho.remover(produtoId, tipoPreco);

        ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
        redirectAttributes.addFlashAttribute("sucesso", "Produto removido do carrinho com sucesso!");

        return modelAndView;
    }

    private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
        Produto produto = produtoDao.find(produtoId);

        CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);

        carrinho.add(carrinhoItem);

        return carrinhoItem;
    }
}
