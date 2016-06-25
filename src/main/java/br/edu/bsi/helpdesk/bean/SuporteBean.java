package br.edu.bsi.helpdesk.bean;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.bsi.helpdesk.dao.FuncionarioDAO;
import br.edu.bsi.helpdesk.dao.SuporteDAO;
import br.edu.bsi.helpdesk.domain.Funcionario;
import br.edu.bsi.helpdesk.domain.Suporte;




@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SuporteBean implements Serializable {

	private Suporte suporte;
	private List <Suporte> suportes;
	private List <Funcionario> funcionarios;
	public List<Suporte> getSuportes() {
		return suportes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void setSuportes(List<Suporte> suportes) {
		this.suportes = suportes;
	}

	public Suporte getSuporte() {
		return suporte;
	}

	public void setSuporte(Suporte suporte) {
		this.suporte = suporte;
	}
	
	public void novo() {	
		try{
			suporte = new Suporte();
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		}catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo suporte");
			erro.printStackTrace();
		}
}
@PostConstruct
public void listar() {
	try {
		SuporteDAO suporteDAO = new SuporteDAO();
		suportes = suporteDAO.listar();
	} catch (RuntimeException erro) {
		Messages.addGlobalError("Ocorreu um erro ao tentar listar os suportes");
		erro.printStackTrace();
	}
}

public void salvar(){
	try {
		SuporteDAO suporteDAO = new SuporteDAO();
		suporteDAO.salvar(suporte);

		suporte = new Suporte();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		funcionarios = funcionarioDAO.listar();
		suportes = suporteDAO.listar();

		Messages.addGlobalInfo("Suporte salvo com sucesso");
	} catch (RuntimeException erro) {
		Messages.addGlobalError("Ocorreu um erro ao tentar salvar o suporte");
		erro.printStackTrace();
	}

}

public void excluir(ActionEvent evento) {
	try {
		suporte = (Suporte) evento.getComponent().getAttributes().get("suporteSelecionado");

		SuporteDAO suporteDAO = new SuporteDAO();
		suporteDAO.excluir(suporte);
		
		suportes = suporteDAO.listar();

		Messages.addGlobalInfo("Suporte removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o suporte");
		erro.printStackTrace();
	}
}

public void editar(ActionEvent evento){
	suporte = (Suporte) evento.getComponent().getAttributes().get("suporteSelecionado");
}
}
