package br.edu.bsi.helpdesk.bean;
import java.io.Serializable;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import br.edu.bsi.helpdesk.domain.Suporte;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SuporteBean implements Serializable {

	Suporte suporte;

	public Suporte getSuporte() {
		return suporte;
	}

	public void setSuporte(Suporte suporte) {
		this.suporte = suporte;
	}
	
}
