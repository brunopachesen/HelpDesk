package br.edu.bsi.helpdesk.bean;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.bsi.helpdesk.dao.UsuarioDAO;
import br.edu.bsi.helpdesk.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{

	Usuario usuario;
	List <Usuario> usuarios;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void novo() {	
		usuario = new Usuario();
}
@PostConstruct
public void listar() {
	try {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.listar();
	} catch (RuntimeException erro) {
		Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuarios");
		erro.printStackTrace();
	}
}

public void salvar(){
	try {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

		usuario = new Usuario();
		usuarios = usuarioDAO.listar();

		Messages.addGlobalInfo("Usuario salvo com sucesso");
	} catch (RuntimeException erro) {
		Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Usuario");
		erro.printStackTrace();
	}

}

public void excluir(ActionEvent evento) {
	try {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
		
		usuarios = usuarioDAO.listar();

		Messages.addGlobalInfo("Usuario removido com sucesso");
	} catch (RuntimeException erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o usuario");
		erro.printStackTrace();
	}
}

public void editar(ActionEvent evento){
	usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
}
}
