package pedidos.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class PedidosUtil {
	
	public static void addMessage(String tipo, String resumo, String mensagem){
		
		Severity tipoMensagem = null;
		
		if("success".equals(tipo)){
			tipoMensagem = FacesMessage.SEVERITY_INFO;
		}else if("danger".equals(tipo)){
			tipoMensagem = FacesMessage.SEVERITY_FATAL;
		}else if("warn".equals(tipo)){
			tipoMensagem = FacesMessage.SEVERITY_WARN;
		}
		
		FacesMessage message = new FacesMessage(tipoMensagem, resumo, mensagem);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
