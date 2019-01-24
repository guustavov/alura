package main.java.br.com.caelum.leilao.dominio;

import org.omg.SendingContext.RunTime;

import java.util.ArrayList;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<>();
	}
	
	public void propoe(Lance lance) {
	    if(lances.isEmpty() || usuarioPodeDarLance(lance.getUsuario())){
            lances.add(lance);
        }
	}

    public void dobraUltimoLance(Usuario usuario) {
	    if(lances.isEmpty()){
	        throw new RuntimeException("Não é possível dobrar último lance de leilão sem lances");
        }

        Lance ultimoLance = lances.get(lances.size() - 1);
        this.propoe(new Lance(usuario, ultimoLance.getValor() * 2));
    }

    private boolean usuarioPodeDarLance(Usuario usuario) {
        return !usuarioUltimoLance(usuario) && quantidadeLances(usuario) < 5;
    }

    private boolean usuarioUltimoLance(Usuario usuario) {
        return lances.get(lances.size() - 1).getUsuario().equals(usuario);
    }

    private long quantidadeLances(Usuario usuario) {
        return lances.stream()
                .filter(lanceExistente -> lanceExistente.getUsuario().equals(usuario))
                .count();
    }

    public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return lances;
	}
}
