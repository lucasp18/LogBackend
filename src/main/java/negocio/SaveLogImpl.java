package negocio;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Log;
import request.LogRequest;
import util.HibernateUtil;

public class SaveLogImpl implements SaveLog {
	
	@Override
	public Log save(LogRequest logRequest) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List<Log> logsSalvo = session.createQuery("from Log l where l.conteudo like :conteudoNovo", Log.class).setParameter("conteudoNovo", logRequest.getConteudo()).getResultList();
		
		Transaction tx = null;
		tx  = session.beginTransaction();
		Log log = new Log();
		if(logsSalvo.isEmpty()) {
					
			log.setConteudo(logRequest.getConteudo());
			log.setVezes(logRequest.getVezes());
			session.save(log);
			
		}else {
			log = logsSalvo.get(0);
			log.setVezes(logsSalvo.get(0).getVezes() + logRequest.getVezes());
			log.setConteudo(logRequest.getConteudo());
			session.merge(log);
			session.update(log);
		}
		
        tx.commit();				
		session.close();
		
		return log;
	}	
	
}
