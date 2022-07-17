package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Log;
import negocio.ListaLogs;
import negocio.ListaLogsImpl;
import negocio.SaveLog;
import negocio.SaveLogImpl;
import request.LogRequest;

@Path("/log")
public class LogController {
		
	private final SaveLog saveLogImpl;
	private final ListaLogs listaLogsImpl;
	
	public LogController() {
		this.saveLogImpl = new SaveLogImpl();
		this.listaLogsImpl = new ListaLogsImpl();
	}
		
	@POST
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	public Log inserir(LogRequest logRequest) {		
		return this.saveLogImpl.save(logRequest);
	}	
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Log> listar() {		
		return this.listaLogsImpl.executar();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String healthcheck() {		
		return "OK";
	}
	
}
