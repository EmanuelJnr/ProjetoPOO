package Interface;

import javax.swing.JPasswordField;
import Funcionalidades.Fontes;

public class CampoDeSenha extends JPasswordField{
	private static final long serialVersionUID = 1L;

	public CampoDeSenha(int x, int y, int comprimento, int altura) {
		setFont(Fontes.padrao());
		setBounds(x, y, comprimento, altura);
	}
}