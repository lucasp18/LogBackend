package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import negocio.Rotina;
import negocio.RotinaImpl;

public class AppInit extends ResourceConfig {

	private static final Logger logger = LogManager.getLogger(AppInit.class.getName());
	
	public AppInit() {
		super();
		System.out.println("Testando status online da aplicação");
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {						
		            	
		            	System.out.println("Testando healthcheck da api");
		            	URL url = null;
		        		try {
		        			url = new URL("http://localhost:8080/LogBackend/rest/log");
		        		} catch (MalformedURLException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		        		HttpURLConnection conn = null;
		        		try {
		        			conn = (HttpURLConnection) url.openConnection();
		        			
		        		} catch (IOException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		        		try {
		        			System.out.println("Status da rota healthcheck: " +conn.getResponseCode());
		        			if(conn.getResponseCode() == 200) {
		        				System.out.println("Iniciando importação do arquivo de log");
		        				Rotina r = new RotinaImpl();
				        		r.executar();
				        		super.cancel();
				        		System.out.println("fim da importação do arquivo de log");
				        		System.out.println("Cancelando a rotina de checagem de arquivo de log");
		        			}
		        			
		        		} catch (IOException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}			            		            
		            }
		            		            
		            	}, 
		        0,1000 
		);
								
	}
	
}
