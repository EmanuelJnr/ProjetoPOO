package Telas;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TelaPadrao extends JFrame{
	private static final long serialVersionUID = 1L;
	
	final Font fonte = new Font("Tahoma", Font.BOLD, 12);
	
	public TelaPadrao(String nome) {
		super(nome);
		setSize(800, 600);
		setLayout(null);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagens/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}