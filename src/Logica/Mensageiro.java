package Logica;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro extends SimpleEmail{	
	public Mensageiro() throws EmailException {
		setHostName("smtp.gmail.com");
		setSmtpPort(587);
		setAuthenticator(new DefaultAuthenticator("partyhelper49@gmail.com", "yqwhcxtabgodzebb"));
		setSSLOnConnect(true);
		setFrom("partyhelper49@gmail.com");
	}

	public boolean enviarEmail(String destinatario, String titulo, String mensagem) throws EmailException {
		setSubject(titulo);
		setMsg(mensagem);
		addTo(destinatario);
		send();
		return true;
	}
}