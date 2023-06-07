package Logica;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro extends SimpleEmail{	
	public Mensageiro() {
		setHostName("smtp.gmail.com");
		setSmtpPort(587);
		setAuthenticator(new DefaultAuthenticator("gerenciador.festas54325@gmail.com", "wsmjbljpfbenawys"));
		setSSLOnConnect(true);
		try {
			setFrom("gerenciador.festas54325@gmail.com");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarEmailParaCliente(Evento evento, String mensagem) {
		setSubject("Contrato");
		try {
			addTo(evento.getCliente().getEmail());
			setMsg(evento.toString()+"\n"+mensagem);
			send();
		} catch (EmailException e) {
			System.out.println(e);;
		}
	}
	public boolean enviarEmail(String destinatario, String titulo, String mensagem) {
		setSubject(titulo);
		try {
			setMsg(mensagem);
			addTo(destinatario);
			send();
			return true;
		} catch (EmailException e) {
			return false;
		}
	}
}