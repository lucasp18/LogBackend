package negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Log;
import request.LogRequest;
import util.HibernateUtil;


public class RotinaImpl implements Rotina{	

	@Override
	public void executar() {
		
		ClassLoader classLoader = getClass().getClassLoader();
		
		InputStream inputStream2 = classLoader.getResourceAsStream("logs-file/cingohc.log");
		try {
			
			readFromInputStream(inputStream2);
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		
			
	}
	
	private void salvarOuAtualizar(String linha) {
		Session sessionSave = HibernateUtil.getSessionFactory().openSession();
		
		Pattern p = Pattern.compile("^([a-zA-Z]*) \\|(.*)\\| (.*)");
		 Matcher m = p.matcher(linha);
		 String conteudo = "";
		 if (m.find()) {
		        
		        conteudo = m.group(1) +" "+ m.group(3);
		        
		        List<Log> logsSalvo = sessionSave.createQuery("from Log l where l.conteudo like :conteudoNovo", Log.class).setParameter("conteudoNovo", conteudo).getResultList();
		        		        
		        Transaction tx = null;
				tx  = sessionSave.beginTransaction();
		        		        
		        Log log = new Log();
		        if(logsSalvo.size() > 0) {
		        	
		        	log = logsSalvo.get(0);
		        	
		        	log.setVezes(log.getVezes()+1);
		        	log.setConteudo(log.getConteudo());
		        	log.setId(log.getId());		        			      
		        	
		        	sessionSave.merge(log);
		        	sessionSave.update(log);
		        			      	
		        }else {
		        	
		        	log.setConteudo(conteudo);
		        	log.setVezes(Long.valueOf(1));
		        	
		        	sessionSave.save(log);	
		        }
		 
		        tx.commit();
		        sessionSave.close();
		 }
		
	}
	
	
	private String readFromInputStream(InputStream inputStream)
			  throws IOException {
			    StringBuilder resultStringBuilder = new StringBuilder();
			    try (BufferedReader br
			      = new BufferedReader(new InputStreamReader(inputStream))) {
			        String line;
			        while ((line = br.readLine()) != null) {
			            salvarOuAtualizar(line);
			        }
			    }
			  return resultStringBuilder.toString();
	}	
	
}
