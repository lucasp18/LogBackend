package negocio;

import java.util.List;

import org.hibernate.Session;

import model.Log;
import util.HibernateUtil;

public class ListaLogsImpl implements ListaLogs {

	@Override
	public List<Log> executar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Log> lista = session.createQuery("from Log order by vezes desc ", Log.class).getResultList();
		
		session.close();
		return lista;
	}
}
