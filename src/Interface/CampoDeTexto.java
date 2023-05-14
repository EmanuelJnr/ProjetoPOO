package Interface;

import java.awt.Font;

import javax.swing.JTextField;

public class CampoDeTexto extends JTextField{
	private static final long serialVersionUID = 1L;
	
	public CampoDeTexto(String nome, int x, int y, int comprimento, int altura) {
		super(nome);
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		setBounds(x, y, comprimento, altura);
	}
}
