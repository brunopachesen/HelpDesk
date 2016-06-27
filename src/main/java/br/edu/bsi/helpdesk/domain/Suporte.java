package br.edu.bsi.helpdesk.domain;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



@SuppressWarnings("serial")
@Entity


public class Suporte extends GenericDomain  {

	@OneToOne
	@JoinColumn(nullable = false, unique = true)
	private Funcionario funcionario;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
