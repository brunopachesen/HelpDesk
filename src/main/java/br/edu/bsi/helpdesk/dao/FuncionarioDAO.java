package br.edu.bsi.helpdesk.dao;

import org.apache.shiro.crypto.hash.SimpleHash;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.bsi.helpdesk.domain.Funcionario;
import br.edu.bsi.helpdesk.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario>{

	public Funcionario autenticar(String usuario, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			//criação do apelido da classe funcionario para chamada dentro do método
			

			//comparação de igualdade entre o usuario recebido pelo método 
			// e o usuario presente na tabela/classe/objeto pessoa
			consulta.add(Restrictions.eq("login", usuario));

			//algortimo de criptografia
			//utilização do algortimo md5
			//gera um sequência hexadecimal de 32 caracteres
			SimpleHash hash = new SimpleHash("md5", senha);
			//toHex() é o método utilizado para aplicar o algortimo md5 na senha
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			//retorna apenas um resultado e faz um "cast" para usuário
			Funcionario resultado = (Funcionario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}