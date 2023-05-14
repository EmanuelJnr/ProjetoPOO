package Interface;

import java.awt.Font;

import javax.swing.JButton;

public class Botao extends JButton{
	private static final long serialVersionUID = 1L;
	
	public Botao(String nome, int x, int y, int comprimento, int altura) {
		super(nome);
		setFont(new Font("Tahoma", Font.CENTER_BASELINE, 12));
		setBounds(x, y, comprimento, altura);
	}
}
