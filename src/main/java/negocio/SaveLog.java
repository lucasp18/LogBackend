package negocio;

import model.Log;
import request.LogRequest;

public interface SaveLog {
	
	public Log save(LogRequest logRequest);

}
