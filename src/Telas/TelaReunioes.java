package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interface.Botao;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.CentralDeInformacoes;
import Logica.Persistencia;
import Logica.Reuniao;
import Ouvintes.OuvinteNovaTela;

public class TelaReunioes extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Botao btnATA;
	private Botao btnReuniao;
	private String faltaCoisas;

	public TelaReunioes() {
		super("Reuniões");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBotoes();
		setVisible(true);
	}
	public static void main(String[] args) {////////////////////////////////////
		new TelaReunioes();
	}

	public void addLabels() {
		Label titulo = new Label("REUNIÕES", 346, 30, 108, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Data e hora");
		modelo.addColumn("ATA");

		for(Reuniao r: ci.getTodasAsReunioes()) {
			Object[] linha = new Object[2];

			linha[0] = String.valueOf(r.getDataHora());
			linha[1] = r.getAta();

			modelo.addRow(linha);
		}

		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,100,745,350);
		add(painelScrow);
	}

	public void addBotoes() {
		btnATA = new Botao("ATA", 180, 500, 120, 30);
		add(btnATA);

		btnReuniao = new Botao("Marcar Reunião", 340, 500, 120, 30);
		add(btnReuniao);

		Botao btnVoltar = new Botao("Voltar", 500, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_ORCAMENTOS);
		add(btnVoltar);
	}
	public void ouvinteBotoes() {
		btnReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataHora = JOptionPane.showInputDialog(null,"Data e Horário da reunião", "dd/MM/yyyy HH:mm ");
				//TODO Guardar dataHora e marcar a reunião.
			}
		});

		btnATA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO pega um objeto da tabela
				dispose();
				new TelaATAReuniao();
			}
		});
	}
}