package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Interface.*;
import Logica.*;

public class TelaCadastroPacote extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CampoDeTexto tfNomePacote;
	private CampoDeTexto tfValor;
	private Botao btnCadastrar;
	private Botao btnVoltar;
	private Botao btnExcluir;
	private Botao btnAdicionar;
	private JTextArea taATA;
	private JTable tabelaTodos;
	private JTable tabelaAdd;
	private DefaultTableModel modelo;
	//private ArrayList<Fornecedor> fornecedoresAdd = ci.getFornecedoresTemp();

	public TelaCadastroPacote() {
		super("Cadastrar Pacote");
		addLabels();
		addCamposDeTexto();
		addBotoes();
		textAta();
		tabelaTodosFornecedores();
		tabelaAddFornecedores();
		ouvinteBotoes();
		setVisible(true);
	}
	public static void main(String[] args) {////////////////////////////////////////////////
		new TelaCadastroPacote();
	}

	public void addLabels() {
		Label titulo = new Label("Cadastrar Pacote", 300, 20, 200, 25);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Nome do pacote: ", 70, 100, 160, 25));
		add(new Label("Valor: ", 70, 150, 120, 25));

		add(new Label("Fornecedores deste pacote", 126, 205, 200, 30));
		add(new Label("Todos os fornecedores", 513, 205, 150, 30));
	}
	public void textAta() {
		taATA = new JTextArea(8,15);
		JScrollPane sp = new JScrollPane(taATA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel jp = new JPanel();
		jp.add(sp);
		jp.setBounds(580, 65, 192, 135);
		add(jp);
	}

	public void addCamposDeTexto() {
		tfNomePacote = new CampoDeTexto("", 220, 100, 350, 25);
		tfNomePacote.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NOME));
		add(tfNomePacote);

		tfValor = new CampoDeTexto("", 220, 150, 350, 25);
		tfValor.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NUMEROIDADE));
		add(tfValor);
	}

	public void addBotoes() {		
		btnExcluir = new Botao("Excluir", 155, 445, 120, 30);
		add(btnExcluir);

		btnAdicionar = new Botao("Adicionar", 520, 445, 120, 30);
		add(btnAdicionar);

		btnCadastrar = new Botao("Cadastrar", 250, 515, 120, 30);
		add(btnCadastrar);

		btnVoltar = new Botao("Voltar", 430, 515, 120, 30);
		add(btnVoltar);
	}
	public void ouvinteBotoes() {
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaAdd.getSelectedRow() != -1) {
					String cpf_cnpj = tabelaAdd.getValueAt(tabelaAdd.getSelectedRow(), 1).toString();
					modelo.removeRow(tabelaAdd.getSelectedRow());
					ci.getFornecedoresTemp().remove(ci.buscaFornecedor(cpf_cnpj));
					p.salvarCentral(ci);
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodos.getSelectedRow() != -1) {
					String cpf_cnpj = tabelaTodos.getValueAt(tabelaTodos.getSelectedRow(), 1).toString();
					if(ci.adicionarFornecedorTemp(ci.buscaFornecedor(cpf_cnpj))) {
						Fornecedor f = ci.buscaFornecedor(cpf_cnpj);
						Object[] row = new Object[3];
						row[0] = f.getNome();
						row[1] = f.getCPF_CNPJ();
						row[2] = f.getQtdContratos();
						modelo.addRow(row);
						p.salvarCentral(ci);
					}
				}
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO cadastrar o pacote
				
				
				
				ci.setFornecedoresTemp(new ArrayList<>());
				p.salvarCentral(ci);
				dispose();
				new TelaPacotes();
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ci.setFornecedoresTemp(new ArrayList<>());
				p.salvarCentral(ci);
				dispose();
				new TelaPacotes();
			}
		});
	}

	public void tabelaTodosFornecedores() {
		DefaultTableModel modelo = new DefaultTableModel();
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
		tabelaTodos = new JTable(modelo);
		tabelaTodos.getColumnModel().getColumn(0).setCellRenderer(AlinhaCelulas.alinhar());
		tabelaTodos.getColumnModel().getColumn(1).setCellRenderer(AlinhaCelulas.alinhar());
		tabelaTodos.getColumnModel().getColumn(2).setCellRenderer(AlinhaCelulas.alinhar());
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabelaTodos.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaTodos);
		painelScrow.setBounds(393, 230, 372, 205);
		add(painelScrow);
	}

	public void tabelaAddFornecedores() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Física/Jurídica");
		modelo.addColumn("Quantidade de contratos");

		for(Fornecedor f : ci.getFornecedoresTemp()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getQtdContratos();
			modelo.addRow(linha);
		}
		tabelaAdd = new JTable(modelo);
		tabelaAdd.getColumnModel().getColumn(0).setCellRenderer(AlinhaCelulas.alinhar());
		tabelaAdd.getColumnModel().getColumn(1).setCellRenderer(AlinhaCelulas.alinhar());
		tabelaAdd.getColumnModel().getColumn(2).setCellRenderer(AlinhaCelulas.alinhar());
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabelaAdd.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaAdd);
		painelScrow.setBounds(20, 230, 372, 205);
		add(painelScrow);
	}
}