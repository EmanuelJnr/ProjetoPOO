package Telas;

import java.awt.Image;

import javax.swing.ImageIcon;

import Interface.Botao;
import Interface.Label;

public class TelaPrincipal extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Label logo;

	public TelaPrincipal(String nome) {
		super(nome);
		addBotoes();
		addLabel();
		setVisible(true);
	}
	
	public void addBotoes() {
		add(new Botao("Orçamentos/Contratos", 10, 230, 281, 160, 36));
		
		add(new Botao("Fornecedores", 12, 410, 281, 160, 36));
		
		add(new Botao("Reuniões", 12, 230, 338, 160, 36));
		
		add(new Botao("Serviços", 12, 410, 338, 160, 36));
		
		add(new Botao("Pacotes", 12, 230, 396, 160, 36));
		
		add(new Botao("Clientes", 12, 410, 396, 160, 36));
		
		add(new Botao("Gerar Planilha", 12, 313, 449, 160, 36));
		
		add(new Botao("Logout", 12, 620, 510, 160, 36));
	}
	public void addLabel() {
		logo = new Label("", 200, -60, 400, 400);
		logo.setIcon(getImg("PartyHelper.png",400,400));
		add(logo);
	}
	public ImageIcon getImg(String nome, int x, int y) {
		String caminhoDaImagem = "Imagens/" + nome;
		ClassLoader classLoader = this.getClass().getClassLoader();
		ImageIcon icone = new ImageIcon(classLoader.getResource(caminhoDaImagem));
		Image image = icone.getImage();
		Image newimg = image.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH);
		return icone = new ImageIcon(newimg);
	}
	public static void main(String[] args) {
		new TelaPrincipal("Tela Principal");
	}
}