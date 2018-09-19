package br.com.casadocodigo.controller;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("produtos")
public class ProdutosController {

    @Autowired
    ProdutoDAO produtoDao;

    @RequestMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView gravar(Produto produto, RedirectAttributes redirectAttributes){
        System.out.print(produto.toString());
        produtoDao.gravar(produto);


        redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");

        return new ModelAndView("redirect:produtos");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar(){
        ModelAndView modelAndView = new ModelAndView("produtos/lista");
        List<Produto> produtos = produtoDao.listar();

        modelAndView.addObject("produtos", produtos);

        return modelAndView;
    }
}
