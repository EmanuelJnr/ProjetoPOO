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

public class TelaAddFornecedor extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnAdicionar;
	private Botao btnVoltar;

	public TelaAddFornecedor() {
		super("Fornecedores");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBtnAdicionar();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("FORNECEDORES", 318, 30, 164, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Física/Jurídica");
		modelo.addColumn("Quantidade de contratos");

		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.

		/** TODO Adicionar na lista os devidos atributos. 
		for() {
			Object[] linha = new Object[3];

			linha[0] = //Nome
			linha[1] = //Física/Jurídica
			linha[2] = //Quantidade

			modelo.addRow(linha);
		}
		 */	
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,100,745,350);
		add(painelScrow);
	}

	public void addBotoes() {
		btnAdicionar = new Botao("Adicionar",200,500,120,30);
		add(btnAdicionar);

		btnVoltar = new Botao("Voltar",480,500,120,30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaCadastroOrcamento");
		add(btnVoltar);
	}
	public void ouvinteBtnAdicionar() {
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Adiciona fornecedores no orçamento
				dispose();
				new TelaCadastroOrcamento();
			}
		});
	}
}