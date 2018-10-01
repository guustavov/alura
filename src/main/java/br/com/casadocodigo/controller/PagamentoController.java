package br.com.casadocodigo.controller;

import br.com.casadocodigo.model.Carrinho;
import br.com.casadocodigo.model.DadosPagamento;
import br.com.casadocodigo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.Callable;

@Controller
@RequestMapping(value = "/pagamento")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode= ScopedProxyMode.TARGET_CLASS)
public class PagamentoController {

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailSender sender;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model){

        return () -> {
            try {
                String uri = "http://book-payment.herokuapp.com/payment";
                String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);

                model.addFlashAttribute("sucesso", response);
                System.out.println(response);

//                enviaEmailCompraProduto(usuario);

                return new ModelAndView("redirect:/produtos");
            } catch (RestClientException e) {
                e.printStackTrace();
                model.addFlashAttribute("falha", "Valor maior que o permitido (R$500,00)!");
                return new ModelAndView("redirect:/produtos");
            }
        };

    }

    private void enviaEmailCompraProduto(Usuario usuario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Compra finalizada com sucesso");
//        email.setTo(usuario.getEmail());
        email.setTo("gustavo.vieira@pti.org.br");
        email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
        email.setFrom("compras@casadocodigo.com.br");

        sender.send(email);
    }
}
