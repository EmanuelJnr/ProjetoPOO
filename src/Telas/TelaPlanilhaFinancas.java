package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interface.Botao;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Ouvintes.OuvinteNovaTela;

public class TelaPlanilhaFinancas extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnFornecedores;
	private String faltaCoisas;

	public TelaPlanilhaFinancas() {
		super("Planilha de Finanças");
		addLabels();
		addTabela();
		addBotoes();
		ouvinteFornecedores();
		setVisible(true);
	}
	public static void main(String[] args) {////////////////////////////////////////////////////////////
		new TelaPlanilhaFinancas();
	}
	
	public void addLabels() {
		Label titulo = new Label("EVENTOS", 343, 30, 120, 25);
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
		btnFornecedores = new Botao("Listar Fornecedores", 150, 500, 150, 30);
		add(btnFornecedores);
		
		Botao btnVoltar = new Botao("Voltar", 490, 500, 150, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
		add(btnVoltar);
	}
	
	public void ouvinteFornecedores() {
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO pega um objeto da tabela eventos.
				dispose();
				new TelaFornecedoresFinancas();
			}
		});
	}
}