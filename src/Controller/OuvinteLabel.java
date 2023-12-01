package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;
import ElementosGraficos.Label;
import View.TelaLoginAdmin;

public abstract class OuvinteLabel {
	public static void ouvinteLabel(Label l, JFrame tela) {
		Persistencia p = new Persistencia();
		CentralDeInformacoes ci = p.recuperarCentral();
		l.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
				l.setForeground(Color.BLACK);;
			}
			public void mouseEntered(MouseEvent arg0) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				l.setForeground(Color.RED);;
				l.setCursor(cursor);
			}

			Mensageiro msg;
			public void mouseClicked(MouseEvent arg0) {
				try {
					msg = new Mensageiro();
				} catch (EmailException e1) {
					JOptionPane.showMessageDialog(null, "O e-mail do programa não está mais válido!");
				}
				String novaSenha = GerarSenha.senhaAletoria(6);

				try {
					msg.enviarEmail(ci.getAdmin().getEmail(), "Recuperação de senha do Admin", "Sue nova senha é: "+novaSenha);
					ci.getAdmin().setSenha(novaSenha);
					p.salvarCentral(ci);
					JOptionPane.showMessageDialog(null, "Um e-mail foi enviado com a nova senha!");
					tela.dispose();
					new TelaLoginAdmin();
				} catch (EmailException e) {
					String novoEmail;
					novoEmail = JOptionPane.showInputDialog(null, "Não foi possível enviar uma nova senha, cadastre um novo e-mail");
					if(novoEmail != null) {
						if(VerificaEmail.isValid(novoEmail)) {
							ci.getAdmin().setEmail(novoEmail);
							p.salvarCentral(ci);
							tela.dispose();
							new TelaLoginAdmin();
						}
						else {
							JOptionPane.showMessageDialog(null, "E-mail inválido!");
						}
					}
				}
			}
		});
	}
}