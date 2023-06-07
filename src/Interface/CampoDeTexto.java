package Interface;

import javax.swing.JTextField;
import Funcionalidades.Fontes;

public class CampoDeTexto extends JTextField{
	private static final long serialVersionUID = 1L;
	
	public CampoDeTexto(String nome, int x, int y, int comprimento, int altura) {
		super(nome);
		setFont(Fontes.padrao());
		setBounds(x, y, comprimento, altura);
	}
}
