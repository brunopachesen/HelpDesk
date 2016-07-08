package br.edu.bsi.helpdesk.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@SuppressWarnings("serial")
@Entity
public class Chamado extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String natureza;
	
	@Column(length = 50, nullable = false)
	private short status = 1;

	@Column(length = 140, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Date dataAbertura = new Date();
	

	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Transient
	public String conversao(){
		String status = null;
		if(getStatus() == 1){
			status = "Aberto";
		}else if(getStatus() == 2){
			status = "Andamento";
		}else if(getStatus() == 3){
			status = "Encerrado";
		}
		return status;
	}
	
	
}
