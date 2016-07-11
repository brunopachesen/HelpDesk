package br.edu.bsi.helpdesk.bean;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.omnifaces.util.Messages;


import br.edu.bsi.helpdesk.dao.InteracaoChamadoDAO;
import br.edu.bsi.helpdesk.domain.Chamado;
import br.edu.bsi.helpdesk.domain.Funcionario;
import br.edu.bsi.helpdesk.domain.InteracaoChamado;
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class InteracaoChamadoBean implements Serializable{

	private InteracaoChamado interacao;
	private List<InteracaoChamado> listaInteracao;
	
	
	public InteracaoChamado getInteracao() {
		return interacao;
	}
	public void setInteracao(InteracaoChamado interacao) {
		this.interacao = interacao;
	}
	public List<InteracaoChamado> getListaInteracao() {
		return listaInteracao;
	}
	public void setListaInteracao(List<InteracaoChamado> listaInteracao) {
		this.listaInteracao = listaInteracao;
	}
	
	public void novo(ActionEvent evento) {	
		interacao = new InteracaoChamado();
		interacao.setChamado((Chamado) evento.getComponent().getAttributes().get("chamadoSelecionado"));
		interacao.setFuncionario((Funcionario) evento.getComponent().getAttributes().get("funcionarioLogado"));
}
	public void salvar() throws MalformedURLException, EmailException{
		try {
			
			InteracaoChamadoDAO interacaoDAO = new InteracaoChamadoDAO();
			interacaoDAO.salvar(interacao);
			enviarEmail();
			interacao = new InteracaoChamado();

			Messages.addGlobalInfo("Salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
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
		email.setSubject("nao@responda - Resposta Chamado " + interacao.getChamado().getCodigo());
		email.setMsg("Prezado "+ interacao.getChamado().getFuncionario().getNome()+ "\n\n"+interacao.getResposta()+
					 "\n\nAtt,\n" + interacao.getFuncionario().getNome());
		email.addTo(interacao.getChamado().getFuncionario().getEmail());
		email.send();
		
	}
}
