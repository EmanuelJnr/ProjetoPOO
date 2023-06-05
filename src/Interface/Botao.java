package Interface;

import java.awt.Font;

import javax.swing.JButton;

public class Botao extends JButton{
	private static final long serialVersionUID = 1L;
	
	public Botao(String nome, int tamanho, int x, int y, int largura, int altura) {
		super(nome);
		setFont(new Font("Tahoma", Font.CENTER_BASELINE, tamanho));
		setBounds(x, y, largura, altura);
	}
}
