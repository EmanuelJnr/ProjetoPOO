package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import Funcionalidades.Fontes;
import Funcionalidades.NomeTela;
import Interface.Botao;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaDetalhamentoPacote extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	private Label lbNome;
	private Label lbPreco;
	private JTextArea area;
	private Botao btnExcluir;
	
	public TelaDetalhamentoPacote() {
		super("Detalhamento do pacote");
		addLabels();
		textArea();
		addBotoes();
		setVisible(true);
	}
	
	public void addLabels() {
		Label titulo = new Label("Pacotes", 345, 30, 120, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
		
		add(new Label("Nome: ", 120, 120, 120, 25));
		add(new Label("Preço: ", 120, 170, 120, 25));
		add(new Label("Serviços prestados: ", 120, 220, 200, 25));
		
		lbNome = new Label("Nome do pacote", 300, 115, 120, 25);
		add(lbNome);
		lbPreco = new Label("Preço do pacote", 300, 165, 120, 25);
		add(lbPreco);
	}
	
	public void textArea() {
		area = new JTextArea(5,10);
		area.setBounds(300, 220, 300, 200);
		add(area);
	}
	
	public void addBotoes() {
		Botao btnVoltar = new Botao("Voltar", 450, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PACOTES);
		add(btnVoltar);
		
		btnExcluir = new Botao("Excluir", 220, 500, 120 ,30);
		add(btnExcluir);
	}
	public void ouvinteExcluir() {
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO exclui o pacote selecinado do banco.
				dispose();
				new TelaPacotes();
			}
		});
	}
}