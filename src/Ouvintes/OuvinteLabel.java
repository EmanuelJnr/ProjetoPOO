package Ouvintes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import Interface.Label;

public abstract class OuvinteLabel {
	public static void ouvinteLabel(Label l, JFrame tela) {
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
			public void mouseClicked(MouseEvent arg0) {
				//Deve mandar um e-mail para recuperar a senha do Admin,
				//se conseguir enviar o email, ele setSenha() - nova senha enviada,
				//(showMessageDialog "Foi enviado um e-mail para recuperar a senha").
				
				//se não conseguir (showInputDialog "Não foi possível enviar uma nova senha, cadastre um novo e-mail"),
				//validação para ser e-mail válido e não ser o mesmo antigo,
				//setEmail() = showInputDialog.
			}
		});
	}
}