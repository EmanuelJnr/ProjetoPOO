package Interface;

import javax.swing.JLabel;
import Funcionalidades.Fontes;

public class Label extends JLabel{
	private static final long serialVersionUID = 1L;
	
	public Label(String nome, int x, int y, int comprimento, int altura) {
		super(nome);
		setFont(Fontes.padrao());
		setBounds(x, y, comprimento, altura);
	}
}
