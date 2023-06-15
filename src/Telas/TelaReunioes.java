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
import Ouvintes.OuvinteNovaTela;

public class TelaReunioes extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	private Botao btnATA;
	private Botao btnReuniao;

	public TelaReunioes() {
		super("Reuniões");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBotoes();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("REUNIÕES", 346, 30, 108, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome do Cliente");
		modelo.addColumn("Data e Hora");

		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.

		/** TODO Adicionar na lista os devidos atributos. 
		for() {
			Object[] linha = new Object[3];

			linha[0] = //Nome do Cliente
			linha[1] = //Data e Hora

			modelo.addRow(linha);
		}
		 */	
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
	public static void main(String[] args) {
		new TelaReunioes();
	}
}