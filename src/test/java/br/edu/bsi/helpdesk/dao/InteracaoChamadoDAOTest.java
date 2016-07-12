package br.edu.bsi.helpdesk.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.bsi.helpdesk.domain.Chamado;
import br.edu.bsi.helpdesk.domain.Funcionario;
import br.edu.bsi.helpdesk.domain.InteracaoChamado;

public class InteracaoChamadoDAOTest {
	@Ignore
	public void salvar(){
		Funcionario funcionario = new Funcionario();
		Chamado chamado = new Chamado();
		InteracaoChamado interacao = new InteracaoChamado();
		funcionario.setCodigo((long) 6);
		chamado.setCodigo((long) 6);
		
		interacao.setChamado(chamado);
		interacao.setFuncionario(funcionario);
		interacao.setDataInteracao(new Date());
		interacao.setResposta("Cartucho trocado");

	
		
		InteracaoChamadoDAO idao = new InteracaoChamadoDAO();
		idao.salvar(interacao);
	}
	
	@Ignore
	public void listar(){
		InteracaoChamadoDAO interacaoDAO = new InteracaoChamadoDAO();
		//Lista que recebe os resultados do metodo
		List<InteracaoChamado> resultado = interacaoDAO.listar();
		
		System.out.println("Total de Registros Encontrados: "+resultado.size());
		//foreach para percorrer a lista
		for(InteracaoChamado interacao : resultado){
			System.out.println(interacao.getCodigo()+ " - " + 
							   interacao.getFuncionario().getCodigo() + " - " +
							   interacao.getChamado().getCodigo() + " - " +
							   interacao.getResposta() + " - " +
							   interacao.getDataInteracao() + "\n");
		}
	}
	
	@Test
	public void buscar(){
		Long codigo = 1L;
		
		InteracaoChamadoDAO interacaoDAO = new InteracaoChamadoDAO();
		//acessar o método buscar e passar o parametro codigo
		InteracaoChamado interacao = interacaoDAO.buscar(codigo);
		if(interacao == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado");
			System.out.println(interacao.getCodigo()+ " - " + 
					   interacao.getFuncionario().getCodigo() + " - " +
					   interacao.getChamado().getCodigo() + " - " +
					   interacao.getResposta() + " - " +
					   interacao.getDataInteracao() + "\n");
		}
	}
	
	@Ignore
	public void editar(){
		Long codigoInteracao = 1L;
	
		
		InteracaoChamadoDAO interacaoDAO = new InteracaoChamadoDAO();
		InteracaoChamado interacao = interacaoDAO.buscar(codigoInteracao);
		
	
		
		System.out.println("Funcionario a ser Editado");
		System.out.println(interacao.getCodigo()+ " - " + 
				   interacao.getFuncionario().getCodigo() + " - " +
				   interacao.getChamado().getCodigo() + " - " +
				   interacao.getResposta() + " - " +
				   interacao.getDataInteracao() + "\n");
		
		interacao.setResposta("Trocado cabo da impressora");
		
		interacaoDAO.editar(interacao);
		
		System.out.println("Funcionario Editado");
		System.out.println(interacao.getCodigo()+ " - " + 
				   interacao.getFuncionario().getCodigo() + " - " +
				   interacao.getChamado().getCodigo() + " - " +
				   interacao.getResposta() + " - " +
				   interacao.getDataInteracao() + "\n");
	}
	
	@Ignore
	public void excluir(){
		Long codigo =1L;
		InteracaoChamadoDAO interacaoDAO = new InteracaoChamadoDAO();
		InteracaoChamado interacao = interacaoDAO.buscar(codigo);
		
		if(interacao == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			interacaoDAO.excluir(interacao);
			System.out.println("Registro removido: ");
			
		}
		
	}
	
}
