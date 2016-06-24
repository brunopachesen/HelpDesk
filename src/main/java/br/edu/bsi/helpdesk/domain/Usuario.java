package br.edu.bsi.helpdesk.domain;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;







@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain{
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
	


	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	

	
	
}
