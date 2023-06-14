package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaATAReuniao extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnConfirmar;

	public TelaATAReuniao() {
		super("ATA de Reunião");
		setSize(400,300);
		addLabels();
		textAta();
		addBotoes();
		setVisible(true);
	}
	
	public void addLabels() {
		Label titulo = new Label("Digite a ATA de reunião", 81, 5, 238, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}
	
	public void textAta() {
		JTextArea area= new JTextArea(5,10);
		area.setBounds(8, 35, 370, 180);
		add(area);
	}
	
	public void addBotoes() {
		btnConfirmar = new Botao("Confirmar", 60, 230, 100, 25);
		add(btnConfirmar);
		
		Botao btnVoltar = new Botao("Voltar", 215, 230, 100, 25);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaReunioes");
		add(btnVoltar);
	}
	
	public void ouvinteConfirmar() {
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adiciona o texto da ATA dentro da reunião
				dispose();
				new TelaReunioes();
			}
		});
	}
}