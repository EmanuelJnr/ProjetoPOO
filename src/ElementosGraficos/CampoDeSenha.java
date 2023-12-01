package ElementosGraficos;

import javax.swing.JPasswordField;

public class CampoDeSenha extends JPasswordField{
	private static final long serialVersionUID = 1L;

	public CampoDeSenha(int x, int y, int comprimento, int altura) {
		setFont(Fontes.padrao());
		setBounds(x, y, comprimento, altura);
	}
}