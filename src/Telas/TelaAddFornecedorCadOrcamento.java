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

public class TelaAddFornecedorCadOrcamento extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnAdicionar;
	private Botao btnVoltar;

	public TelaAddFornecedorCadOrcamento() {
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
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_CADASTRO_ORCAMENTO);
		add(btnVoltar);
	}
	public void ouvinteBtnAdicionar() {
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opc = JOptionPane.showConfirmDialog(null, "Deseja adicionar o valor ao fornecedor?", null,  JOptionPane.YES_NO_OPTION);
				int valor = 0;
				try {
					if(opc==JOptionPane.YES_OPTION) {
						valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor")); 
					}
				} catch(NumberFormatException nfe) { //TODO looping no exception para não digitar string.
					valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor válido"));
				}
				//TODO O fornecedor em questão recebe o valor.
				//TODO Adiciona fornecedores no cadastramento do orçamento
				dispose();
				new TelaCadastroOrcamento();
			}
		});
	}
	public static void main(String[] args) {
		new TelaAddFornecedorCadOrcamento();
	}
}