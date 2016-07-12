package br.edu.bsi.helpdesk.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.bsi.helpdesk.domain.Chamado;
import br.edu.bsi.helpdesk.domain.Funcionario;

public class ChamadoDAOTest {

	@Ignore
	public void salvar(){
		Funcionario funcionario = new Funcionario();
		Chamado chamado = new Chamado();
		funcionario.setCodigo((long) 6);
		
		chamado.setDataAbertura(new Date());
		chamado.setDescricao("Problema de Impressora");
		chamado.setFuncionario(funcionario);
		chamado.setNatureza("Manutenção de Impressora");
		chamado.setStatus((short) 1);
	
		
		ChamadoDAO cdao = new ChamadoDAO();
		cdao.salvar(chamado);
	}
	
	@Ignore
	public void listar(){
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		//Lista que recebe os resultados do metodo
		List<Chamado> resultado = chamadoDAO.listar();
		
		System.out.println("Total de Registros Encontrados: "+resultado.size());
		//foreach para percorrer a lista
		for(Chamado chamado : resultado){
			System.out.println(chamado.getCodigo()+ " - " + 
							   chamado.getFuncionario().getCodigo() + " - " +
							   chamado.getDescricao() + " - " +
							   chamado.getNatureza() + " - " +
							   chamado.getDataAbertura() + " - " +
							   chamado.getStatus() + "\n");
		}
	}
	
	@Test
	public void buscar(){
		Long codigo = 1L;
		
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		//acessar o método buscar e passar o parametro codigo
		Chamado chamado = chamadoDAO.buscar(codigo);
		if(chamado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado");
			System.out.println(chamado.getCodigo()+ " - " + 
					   chamado.getFuncionario().getCodigo() + " - " +
					   chamado.getDescricao() + " - " +
					   chamado.getNatureza() + " - " +
					   chamado.getDataAbertura() + " - " +
					   chamado.getStatus() + "\n");
		}
	}
	
	@Ignore
	public void editar(){
		Long codigoChamado = 1L;
	
		
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		Chamado chamado = chamadoDAO.buscar(codigoChamado);
		
	
		
		System.out.println("Funcionario a ser Editado");
		System.out.println(chamado.getCodigo()+ " - " + 
				   chamado.getFuncionario().getCodigo() + " - " +
				   chamado.getDescricao() + " - " +
				   chamado.getNatureza() + " - " +
				   chamado.getDataAbertura() + " - " +
				   chamado.getStatus() + "\n");
		
		chamado.setStatus((short) 3);
		
		chamadoDAO.editar(chamado);
		
		System.out.println("Funcionario Editado");
		System.out.println(chamado.getCodigo()+ " - " + 
				   chamado.getFuncionario().getCodigo() + " - " +
				   chamado.getDescricao() + " - " +
				   chamado.getNatureza() + " - " +
				   chamado.getDataAbertura() + " - " +
				   chamado.getStatus() + "\n");
	}
	
	@Ignore
	public void excluir(){
		Long codigo =1L;
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		Chamado chamado = chamadoDAO.buscar(codigo);
		
		if(chamado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			chamadoDAO.excluir(chamado);
			System.out.println("Registro removido: ");
			
		}
		
	}
	
}
