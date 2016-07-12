package br.edu.bsi.helpdesk.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.bsi.helpdesk.domain.Funcionario;

@SuppressWarnings("unused")
public class FuncionarioDAOTest {
	@Ignore
	public void salvar(){
		Funcionario funcionario = new Funcionario();
		
	
		funcionario.setNome("Joao da Silva");
		funcionario.setCpf("123.123.123-13");
		funcionario.setEmail("joaodasilva@email.com");
		funcionario.setLogin("joaodasilva");
		funcionario.setSenha("123456");
		funcionario.setSetor("TI");
		funcionario.setTelefone("4612341234");
		funcionario.setTipo(1);
		
	
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		fdao.salvar(funcionario);
	}
	
	@Ignore
	public void listar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		//Lista que recebe os resultados do metodo
		List<Funcionario> resultado = funcionarioDAO.listar();
		
		System.out.println("Total de Registros Encontrados: "+resultado.size());
		//foreach para percorrer a lista
		for(Funcionario funcionario : resultado){
			System.out.println(funcionario.getCodigo()+ " - " + 
							   funcionario.getNome() + " - " +
							   funcionario.getCpf() + " - " +
							   funcionario.getEmail() + " - " +
							   funcionario.getSetor() + " - " +
							   funcionario.getTelefone() + " - " +
							   funcionario.getTipo() + " - " +
							   funcionario.getLogin() + " - " +
							   funcionario.getSenha() + "\n");
		}
	}
	
	@Ignore
	public void buscar(){
		Long codigo = 9L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		//acessar o método buscar e passar o parametro codigo
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		if(funcionario == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado");
			System.out.println(funcionario.getCodigo()+ " - " + 
					   funcionario.getNome() + " - " +
					   funcionario.getCpf() + " - " +
					   funcionario.getEmail() + " - " +
					   funcionario.getSetor() + " - " +
					   funcionario.getTelefone() + " - " +
					   funcionario.getTipo() + " - " +
					   funcionario.getLogin() + " - " +
					   funcionario.getSenha());
		}
	}
	
	@Ignore
	public void editar(){
		Long codigoFuncionario = 1L;
	
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);
		
	
		
		System.out.println("Funcionario a ser Editado");
		System.out.println(funcionario.getCodigo()+ " - " + 
				   funcionario.getNome() + " - " +
				   funcionario.getCpf() + " - " +
				   funcionario.getEmail() + " - " +
				   funcionario.getSetor() + " - " +
				   funcionario.getTelefone() + " - " +
				   funcionario.getTipo() + " - " +
				   funcionario.getLogin() + " - " +
				   funcionario.getSenha());
		
		funcionario.setTipo(2);
		
		funcionarioDAO.editar(funcionario);
		
		System.out.println("Funcionario Editado");
		System.out.println(funcionario.getCodigo()+ " - " + 
				   funcionario.getNome() + " - " +
				   funcionario.getCpf() + " - " +
				   funcionario.getEmail() + " - " +
				   funcionario.getSetor() + " - " +
				   funcionario.getTelefone() + " - " +
				   funcionario.getTipo() + " - " +
				   funcionario.getLogin() + " - " +
				   funcionario.getSenha());
	}
	
	@Ignore
	public void excluir(){
		Long codigo =1L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		
		if(funcionario == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			funcionarioDAO.excluir(funcionario);
			System.out.println("Registro removido: ");
			
		}
		
	}
}
