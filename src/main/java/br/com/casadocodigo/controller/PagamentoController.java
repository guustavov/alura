package br.com.casadocodigo.controller;

import br.com.casadocodigo.model.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/pagamento")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class PagamentoController {

    @Autowired
    private Carrinho carrinho;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public ModelAndView finalizar(RedirectAttributes model){

        System.out.println(carrinho.getTotal());

        model.addFlashAttribute("sucesso", "Pagamento realizado com sucesso!");

        return new ModelAndView("redirect:/produtos");
    }
}
