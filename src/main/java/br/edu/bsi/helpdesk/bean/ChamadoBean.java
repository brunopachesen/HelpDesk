package br.edu.bsi.helpdesk.bean;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
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

	public void novo(ActionEvent evento) {
	
		chamado = new Chamado();
		chamado.setFuncionario((Funcionario) evento.getComponent().getAttributes().get("funcionarioLogado"));
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

public void salvarAndamento(ActionEvent evento) throws EmailException, MalformedURLException{
	if(chamado.getStatus() != ENCERRADO){
		if( status == true){
			chamado.setStatus(ANDAMENTO);
			enviarEmail();
			this.salvar();

			
		}else{
			chamado.setStatus(ABERTO);
			this.salvar();
		}
	}
}

public void enviarEmail() throws EmailException, MalformedURLException{
	URL url = new URL("http://localhost:8080/HelpDesk/faces/pages/chamado.xhtml");
	SimpleEmail email = new SimpleEmail();
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("helpdesk.suipe@gmail.com", "weeaboo2016"));
	email.setSSLOnConnect(true);
	email.setFrom("helpdesk.suipe@gmail.com");
	email.setSubject("nao@responda - Alteração de Status de Chamado");
	email.setMsg("Prezado "+ chamado.getFuncionario().getNome()+ "\n\n\tO chamado "+chamado.getCodigo()
			+" encontra-se agora com nossa equipe de suporte SUiPE. \n Acesse "+url+" para interagir.");
	email.addTo(chamado.getFuncionario().getEmail());
	email.send();
	
}


}
