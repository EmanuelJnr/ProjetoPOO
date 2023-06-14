package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaReunioes extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	private Botao btnATA;

	public TelaReunioes() {
		super("Reuniões");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBtnATA();
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
		
		Botao btnListarClientes = new Botao("Listar Clientes", 340, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnListarClientes, this, "TelaClientes");
		add(btnListarClientes);

		Botao btnVoltar = new Botao("Voltar", 500, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaPrincipal");
		add(btnVoltar);
	}
	public void ouvinteBtnATA() {
		btnATA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pega um objeto da tabela
				dispose();
				new TelaATAReuniao();
			}
		});
	}
}