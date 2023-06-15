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

public class TelaFornecedores extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnExcluir;
	private Botao btnDetalhar;
	
	public TelaFornecedores() {
		super("Fornecedores");
		addBotoes();
		addLabels();
		addTabela();
		ouvintesBotoes();
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
		painelScrow.setBounds(20,100,745,350);
		add(painelScrow);
	}
	
	public void addBotoes() {
		Botao btnFiltrar = new Botao("Filtrar", 130, 60, 120, 30);
		OuvinteNovaTela.proximaTela(btnFiltrar, this, NomeTela.TELA_FILTRAR_FORNECEDOR);
		add(btnFiltrar);
		
		Botao btnCadastrar = new Botao("Cadastrar", 140, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnCadastrar, this, NomeTela.TELA_CADASTRO_FORNECEDOR);
		add(btnCadastrar);
		
		btnDetalhar = new Botao("Detalhar/Editar", 280, 500, 120, 30);
		add(btnDetalhar);
		
		btnExcluir = new Botao("Excluir", 420, 500, 120, 30);
		add(btnExcluir);
		
		Botao btnVoltar = new Botao("Voltar", 560, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
		add(btnVoltar);
	}
	
	public void ouvintesBotoes() {
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO pega um objeto da tabela.
				dispose();
				new TelaDetalharFornecedor();
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO exclui um fornecedor do banco de dados
				dispose();
				new TelaFornecedores();
			}
		});
	}
}