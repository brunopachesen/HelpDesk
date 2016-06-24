package br.edu.bsi.helpdesk.bean;
import java.io.Serializable;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import br.edu.bsi.helpdesk.domain.Chamado;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChamadoBean implements Serializable {

	private Chamado chamado;

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
}
