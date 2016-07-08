package br.edu.bsi.helpdesk.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.edu.bsi.helpdesk.dao.FuncionarioDAO;
import br.edu.bsi.helpdesk.domain.Funcionario;



//permite manipular o bean através da tela
@ManagedBean
//Cria uma session que dura enquanto a aplicatição estiver aberta
@SessionScoped

public class AutenticacaoBean {
	private Funcionario funcionario;
	private Funcionario funcionarioLogado;
	private boolean suporteLogado = false;
	private boolean usuarioLogado = false;
	
	

	public boolean isSuporteLogado() {
		return suporteLogado;
	}

	public void setSuporteLogado(boolean suporteLogado) {
		this.suporteLogado = suporteLogado;
	}

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}
	
	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	//inicializa o usuário e a pessoa atrelada
	@PostConstruct
	public void iniciar() {
		funcionario = new Funcionario();
	}

	public void autenticar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			//usuarioLogado recebe o resultado do método autenticar que está no UsuarioDAO
			funcionarioLogado = funcionarioDAO.autenticar(funcionario.getLogin(), funcionario.getSenha());
			
			if(funcionarioLogado == null){
				Messages.addGlobalError("Usuario e/ou senha incorretos");
				return;
			}
			habilitarTela();
			Faces.redirect("./faces/pages/chamado.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	public void habilitarTela(){
	if(funcionarioLogado.getTipo() == 1){
		suporteLogado = true;
		usuarioLogado = false;
	}else{
		suporteLogado = false;
		usuarioLogado = true;
	}

		
}
}
