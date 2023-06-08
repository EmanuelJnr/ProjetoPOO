package Telas;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaPacotes extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnCadastrar;
	private Botao btnDetalhar;
	private Botao btnVoltar;
	
	public TelaPacotes() {
		super("Pacotes");
		addBotoes();
		addLabels();
		addTabela();
		setVisible(true);
	}
	
	public void addLabels() {
		Label titulo = new Label("Pacotes",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}
	
	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome do pacote");
		modelo.addColumn("Valor");
		modelo.addColumn("Serviços");
		modelo.addColumn("Disponibilidade");
		
		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.
		
		/** TODO Adicionar na lista os devidos atributos. 
		for() {
			Object[] linha = new Object[4];
			
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
		btnCadastrar = new Botao("Cadastrar",200,500,120,30);
		//OuvinteNovaTela.proximaTela(btnCadastrar, this, "TelaCadastroFornecedor");
		add(btnCadastrar);
		
		btnDetalhar = new Botao("Detalhar/Deletar",335,500,130,30);
		//OuvinteNovaTela.proximaTela(btnDetalhar, this, "TelaDetalharFornecedor");
		add(btnDetalhar);
		
		btnVoltar = new Botao("Voltar",480,500,120,30);
		//OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaPrincipal");
		add(btnVoltar);
	}
	
	public static void main(String[] args) {
		new TelaPacotes();
	}
}