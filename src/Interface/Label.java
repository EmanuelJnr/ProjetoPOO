package Interface;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel{
	private static final long serialVersionUID = 1L;
	
	public Label(String nome, int x, int y, int comprimento, int altura) {
		super(nome);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setBounds(x, y, comprimento, altura);
	}
}
