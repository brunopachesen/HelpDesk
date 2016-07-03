package br.edu.bsi.helpdesk.bean;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.bsi.helpdesk.dao.ChamadoDAO;
import br.edu.bsi.helpdesk.dao.FuncionarioDAO;
import br.edu.bsi.helpdesk.domain.Chamado;
import br.edu.bsi.helpdesk.domain.Funcionario;




@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChamadoBean implements Serializable {
	
	static final short ABERTO = 1;
	static final short ANDAMENTO = 2;
	static final short ENCERRADO = 3;
	private Chamado chamado;
	private List<Chamado> chamados;
	private List<Funcionario> funcionarios;
	private boolean status;
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void novo() {	
		chamado = new Chamado();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
}
@PostConstruct
public void listar() {
	try {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		chamados = chamadoDAO.listar();
	} catch (RuntimeException erro) {
		Messages.addGlobalError("Ocorreu um erro ao tentar listar os chamados");
		erro.printStackTrace();
	}
}

public void salvar(){
	try {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		chamadoDAO.salvar(chamado);

		chamado = new Chamado();
		
		funcionarios = funcionarioDAO.listar();
		chamados = chamadoDAO.listar();

		Messages.addGlobalInfo("Chamado salvo com sucesso");
	} catch (RuntimeException erro) {
		Messages.addGlobalError("Ocorreu um erro ao tentar salvar o chamado");
		erro.printStackTrace();
	}

}

public void excluir(ActionEvent evento) {
	try {
		chamado = (Chamado) evento.getComponent().getAttributes().get("chamadoSelecionado");

		ChamadoDAO chamadoDAO = new ChamadoDAO();
		chamadoDAO.excluir(chamado);
		
		chamados = chamadoDAO.listar();

		Messages.addGlobalInfo("Chamado removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o chamado");
		erro.printStackTrace();
	}
}

public void editar(ActionEvent evento){
	chamado = (Chamado) evento.getComponent().getAttributes().get("chamadoSelecionado");
}

public void encerrar(ActionEvent evento){
	chamado = (Chamado) evento.getComponent().getAttributes().get("chamadoSelecionado");
	chamado.setStatus(ENCERRADO);
}

public void salvarAndamento(ActionEvent evento){
	if(chamado.getStatus() != ENCERRADO){
		if( status == true){
			chamado.setStatus(ANDAMENTO);
			this.salvar();
		}else{
			chamado.setStatus(ABERTO);
			this.salvar();
		}
	}
}


}
