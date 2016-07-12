package br.edu.bsi.helpdesk.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.bsi.helpdesk.domain.Chamado;
import br.edu.bsi.helpdesk.domain.InteracaoChamado;
import br.edu.bsi.helpdesk.util.HibernateUtil;

public class InteracaoChamadoDAO  extends GenericDAO<InteracaoChamado>{
	public List<InteracaoChamado> listar(Long codigoChamado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(InteracaoChamado.class);
			consulta.add(Restrictions.eq("chamado.codigo", codigoChamado));
			List<InteracaoChamado> resultado = consulta.list();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
