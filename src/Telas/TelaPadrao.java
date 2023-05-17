package Telas;

import java.awt.Font;

import javax.swing.JFrame;

public class TelaPadrao extends JFrame{
	private static final long serialVersionUID = 1L;
	
	final Font fonte = new Font("Tahoma", Font.BOLD, 12);
	
	public TelaPadrao(String nome) {
		super(nome);
		setSize(800, 600);
		setLayout(null);
		//setIconImage(#a definir);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}