package ElementosGraficos;

import javax.swing.JButton;

public class Botao extends JButton{
	private static final long serialVersionUID = 1L;

	public Botao(String nome, int x, int y, int largura, int altura) {
		super(nome);
		setFont(Fontes.padrao());
		setBounds(x, y, largura, altura);
	}
}