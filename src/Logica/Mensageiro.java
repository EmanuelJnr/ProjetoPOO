package Logica;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro extends SimpleEmail{	
	public Mensageiro() throws EmailException {
		setHostName("smtp.gmail.com");
		setSmtpPort(587);
		setAuthenticator(new DefaultAuthenticator("gerenciador.festas54325@gmail.com", "wsmjbljpfbenawys"));
		setSSLOnConnect(true);
		setFrom("gerenciador.festas54325@gmail.com");
	}

	public boolean enviarEmail(String destinatario, String titulo, String mensagem) throws EmailException {
		setSubject(titulo);
		setMsg(mensagem);
		addTo(destinatario);
		send();
		return true;
	}
}