package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
import Ouvintes.OuvinteFocoValor;

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
	private JTextArea taCaracteristicas;
	private JTable tabelaTodos;
	private JTable tabelaAdd;
	private DefaultTableModel modelo;

	public TelaCadastroPacote() {
		super("Cadastrar Pacote");
		addLabels();
		addCamposDeTexto();
		addBotoes();
		addTextArea();
		ouvinteBotoes();
		ouvinteJanela();
		tabelaTodosFornecedores();
		tabelaAddFornecedores();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("Cadastrar Pacote", 300, 20, 200, 25);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Público alvo deste pacote", 607, 43, 200, 30));
		add(new Label("Nome do pacote: ", 70, 100, 160, 25));
		add(new Label("Valor: ", 70, 150, 120, 25));
		add(new Label("Fornecedores deste pacote", 126, 205, 200, 30));
		add(new Label("Todos os fornecedores", 513, 205, 150, 30));
	}

	public void addTextArea() {
		taCaracteristicas = new JTextArea(8,15);
		JScrollPane sp = new JScrollPane(taCaracteristicas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		tfValor.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NUMEROPONTO));
		tfValor.addFocusListener(new OuvinteFocoValor(tfValor));
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
					if(ci.adicionarFornecedoresTemp(ci.buscaFornecedor(cpf_cnpj))) {
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
				try {
					String nomePacote = tfNomePacote.getText();
					float valor = Float.parseFloat(tfValor.getText());
					ArrayList<Fornecedor> fornecedores = ci.getFornecedoresTemp();
					String caracteristicas = taCaracteristicas.getText();

					if(!nomePacote.equals("") && !caracteristicas.equals("")) {
						if(ci.adicionarPacote(new Pacote(nomePacote, valor, fornecedores, caracteristicas))) {
							ci.setFornecedoresTemp(new ArrayList<>());
							p.salvarCentral(ci);
							dispose();
							new TelaPacotes();
						}
						else {
							JOptionPane.showMessageDialog(null, "Um pacote com esse nome já existe no banco de dados!");
							tfNomePacote.setText("");
							return;
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Os campos de textos devem ser preenchidos!");
						return;
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Valor inválido!");
				}
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
	public void ouvinteJanela() {
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				ci.setFornecedoresTemp(new ArrayList<>());
				p.salvarCentral(ci);
			}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		});
	}

	public void tabelaTodosFornecedores() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Física/Jurídica");
		modelo.addColumn("Contratos");

		for(Fornecedor f : ci.getTodosOsFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getQtdContratos();
			modelo.addRow(linha);
		}
		tabelaTodos = new JTable(modelo);
		for(int i=0;i<tabelaTodos.getColumnCount();i++) {
			tabelaTodos.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
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
		modelo.addColumn("Contratos");

		for(Fornecedor f : ci.getFornecedoresTemp()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getQtdContratos();
			modelo.addRow(linha);
		}
		tabelaAdd = new JTable(modelo);
		for(int i=0;i<tabelaTodos.getColumnCount();i++) {
			tabelaTodos.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabelaAdd.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaAdd);
		painelScrow.setBounds(20, 230, 372, 205);
		add(painelScrow);
	}
}