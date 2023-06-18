package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Interface.Botao;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Fornecedor;
import Logica.Persistencia;
import Ouvintes.OuvinteNovaTela;

public class TelaFornecedores extends TelaPadrao{
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private static final long serialVersionUID = 1L;
	private Botao btnExcluir;
	private Botao btnDetalhar;
	private JTable tabela;
	private DefaultTableModel modelo;

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
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Física/Jurídica");
		modelo.addColumn("Quantidade de contratos");

		for(Fornecedor f : ci.getTodosOsFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getQtdContratos();
			modelo.addRow(linha);
		}
		tabela = new JTable(modelo);
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabela.setRowSorter(sorter);
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
				if(tabela.getSelectedRow() != -1) {
					ci.setFornecedorTemp(ci.getTodosOsFornecedores().get(tabela.getSelectedRow()));
					p.salvarCentral(ci);
					dispose();
					new TelaDetalharFornecedor();
				}
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					ci.getTodosOsFornecedores().remove(tabela.getSelectedRow());
					modelo.removeRow(tabela.getSelectedRow());
					p.salvarCentral(ci);
				}
			}
		});
	}
}