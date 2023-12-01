package ElementosGraficos;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton{
	private static final long serialVersionUID = 1L;
	
	public RadioButton(String nome, int x, int y, int comprimento, int altura) {
		super(nome);
		setFont(Fontes.padrao());
		setBounds(x, y, comprimento, altura);
	}
}