package br.com.casadocodigo.controller;

import br.com.casadocodigo.model.Carrinho;
import br.com.casadocodigo.model.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/pagamento")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class PagamentoController {

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public ModelAndView finalizar(RedirectAttributes model){
        try {
            String uri = "http://book-payment.herokuapp.com/payment";
            String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);

            model.addFlashAttribute("sucesso", response);
            System.out.println(response);

            return new ModelAndView("redirect:/produtos");
        } catch (RestClientException e) {
            e.printStackTrace();
            model.addFlashAttribute("falha", "Valor maior que o permitido (R$500,00)!");
            return new ModelAndView("redirect:/produtos");
        }
    }
}
