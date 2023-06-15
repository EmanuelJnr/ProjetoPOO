package Interface;

import javax.swing.JCheckBox;

public class CheckBox extends JCheckBox{
	private static final long serialVersionUID = 1L;

	public CheckBox(String nome, int x, int y, int largura, int altura) {
		super(nome);
		setFont(Fontes.padrao());
		setBounds(x, y, largura, altura);
	}
}