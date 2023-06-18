package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import Interface.Botao;
import Interface.Fontes;
import Interface.Label;
import Logica.CentralDeInformacoes;
import Logica.Persistencia;
import Logica.Reuniao;

public class TelaATAReuniao extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Botao btnConfirmar;
	private JTextArea taATA;
	private Botao btnVoltar;

	public TelaATAReuniao() {
		super("ATA de Reunião");
		setSize(400,300);
		setLocationRelativeTo(null);
		addLabels();
		textAta();
		addBotoes();
		ouvinteBotoes();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("Digite a ATA de reunião", 81, 5, 238, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void textAta() {
		taATA = new JTextArea(5,10);
		taATA.setBounds(8, 35, 370, 180);
		add(taATA);
	}

	public void addBotoes() {
		btnConfirmar = new Botao("Confirmar", 60, 230, 100, 25);
		add(btnConfirmar);

		btnVoltar = new Botao("Voltar", 215, 230, 100, 25);
		add(btnVoltar);
	}

	public void ouvinteBotoes() {
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ci.getReuniaoTemp().setAta(taATA.getText());
				p.salvarCentral(ci);
				dispose();
				new TelaReunioes();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ci.setReuniaoTemp(new Reuniao(null));
				dispose();
				new TelaReunioes();
			}
		});
	}
}