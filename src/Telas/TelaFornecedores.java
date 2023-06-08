package Telas;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaFornecedores extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnCadastrar;
	private Botao btnDetalhar;
	private Botao btnExcluir;
	private Botao btnVoltar;
	private Botao btnFiltrar;
	
	public TelaFornecedores() {
		super("Fornecedores");
		addBotoes();
		addLabels();
		addTabela();
		setVisible(true);
	}
	
	public void addLabels() {
		Label titulo = new Label("Fornecedores",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
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
		painelScrow.setBounds(130,100,540,350);
		add(painelScrow);
	}
	
	public void addBotoes() {
		btnCadastrar = new Botao("Cadastrar",130,500,120,30);
		OuvinteNovaTela.proximaTela(btnCadastrar, this, "TelaCadastroFornecedor");
		add(btnCadastrar);
		
		btnDetalhar = new Botao("Detalhar",270,500,120,30);
		add(btnDetalhar);
		
		btnExcluir = new Botao("Excluir",410,500,120,30);
		add(btnExcluir);
		
		btnVoltar = new Botao("Voltar",550,500,120,30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaPrincipal");
		add(btnVoltar);
		
		btnFiltrar = new Botao("Filtrar",130,60,120,30);
		OuvinteNovaTela.proximaTela(btnFiltrar, this, "TelaFiltrarFornecedor");
		add(btnFiltrar);
	}
	
	public static void main(String[] args) {
		new TelaFornecedores();
	}
}