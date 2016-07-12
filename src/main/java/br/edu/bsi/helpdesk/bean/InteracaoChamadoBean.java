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
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.omnifaces.util.Messages;



import br.edu.bsi.helpdesk.dao.ChamadoDAO;
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
	private InteracaoChamado interacaoAnterior;
	private boolean bloquearResposta;
	private boolean liberarConcluir;
	
	public boolean isLiberarConcluir() {
		return liberarConcluir;
	}
	public void setLiberarConcluir(boolean liberarConcluir) {
		this.liberarConcluir = liberarConcluir;
	}
	public boolean isBloquearResposta() {
		return bloquearResposta;
	}
	public void setBloquearResposta(boolean bloquearResposta) {
		this.bloquearResposta = bloquearResposta;
	}
	public InteracaoChamado getInteracaoAnterior() {
		return interacaoAnterior;
	}
	public void setInteracaoAnterior(InteracaoChamado interacaoAnterior) {
		this.interacaoAnterior = interacaoAnterior;
	}
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
		
		bloquearResposta = false;
		interacaoAnterior = new InteracaoChamado();
		interacaoAnterior.setFuncionario(new Funcionario());
		interacao = new InteracaoChamado();
		interacao.setChamado((Chamado) evento.getComponent().getAttributes().get("chamadoSelecionado"));
		interacao.setFuncionario((Funcionario) evento.getComponent().getAttributes().get("funcionarioLogado"));
		listar(interacao.getChamado());
		
		// Confere se o Chamado Esta Encerrado
		if(interacao.getChamado().getStatus() == 3){
			interacaoAnterior.setResposta("Chamado Encerrado.");
			bloquearResposta = true;
			liberarConcluir = false;
		// Confere se o Chamado Esta em Aberto
		}else if(interacao.getChamado().getStatus() == 1){
			interacaoAnterior.setResposta("Chamado em Aberto, Aguarde.");
			bloquearResposta = true;
			liberarConcluir = false;
		//Condições do Suporte
		}else if(interacao.getFuncionario().getTipo() == 1){
			//Se não tiver nenhuma resposta anterior, libera resposta para suporte
			if(listaInteracao.size() == 0){
				interacaoAnterior.setResposta("Nenhuma resposta foi enviada.");
				bloquearResposta = false;
				liberarConcluir = true;
			//Se a resposta anterior for do suporte, bloqueia resposta
			}else{
				interacaoAnterior = listaInteracao.get(listaInteracao.size()-1);
				if( interacaoAnterior.getFuncionario().getCodigo() == interacao.getFuncionario().getCodigo()){
					interacao.setResposta("Aguarde Resposta.");
					bloquearResposta = true;
					liberarConcluir = false;
				//senão libera a resposta
				}else{
						bloquearResposta = false;
						liberarConcluir = true;
					}
				}
			//Condição para Usuario Comum
		}else{
			//Se não existir resposta, a reposta para o usuario é bloqueada
			if(listaInteracao.size() == 0){
				interacaoAnterior.setResposta("Nenhuma resposta foi enviada.");
				bloquearResposta = true;
				liberarConcluir = false;
				//
			}else{
				//se a resposta anterior for do proprio usuario, resposta é bloqueada
			interacaoAnterior = listaInteracao.get(listaInteracao.size()-1);
			if( interacaoAnterior.getFuncionario().getCodigo() == interacao.getFuncionario().getCodigo()){
				interacao.setResposta("Aguarde Resposta.");
				bloquearResposta = true;
				liberarConcluir = false;
				// senão, o usuario pode efetuar uma resposta
			}else{
					bloquearResposta = false;
					liberarConcluir = true;
				}
			}
		
			}
		}
		
	public void salvar() throws MalformedURLException, EmailException{
		try {
			
			InteracaoChamadoDAO interacaoDAO = new InteracaoChamadoDAO();
			interacaoDAO.salvar(interacao);
			interacao = new InteracaoChamado();

			Messages.addGlobalInfo("Salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}

	}
	
	
	public void listar(Chamado chamado) {
		try {
			InteracaoChamadoDAO interacaoChamadoDAO = new InteracaoChamadoDAO();
			listaInteracao = interacaoChamadoDAO.listar(chamado.getCodigo());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os chamados");
			erro.printStackTrace();
		}
	}

		
	
}
