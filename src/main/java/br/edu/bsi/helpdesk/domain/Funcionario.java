package br.edu.bsi.helpdesk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain{
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@Column(length = 15, nullable = false, unique = true)
	private String telefone;
	
	@Column(length = 15, nullable = false, unique = true)
	private String cpf;
	
	@Column(length = 50, nullable = false)
	private String setor;
	
	@Column(length = 50, nullable = false, unique = true)
	private String login;
	
	@Column(length = 50, nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private int tipo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String conversao(){
		String tipo = null;
		if(getTipo() == 1){
			tipo = "Suporte";
		}else{
			tipo = "Usuario";
		}
		return tipo;
	}
	
}
