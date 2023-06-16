package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Fontes;
import Interface.Label;
import Interface.RadioButton;
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Fornecedor;
import Logica.Persistencia;
import Logica.Servico;
import Logica.ValidaCNPJ;
import Logica.ValidaCPF;
import Logica.VerificaEmail;
import Ouvintes.OuvinteDeFoco;
import Ouvintes.OuvinteFocoFormatted;

public class TelaCadastroFornecedor extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private RadioButton rbCPF;
	private RadioButton rbCNPJ;
	private Botao btnExcluir;
	private Botao btnCadastrar;
	private Botao btnAdicionar;
	private Botao btnVoltar;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private JFormattedTextField tfTelefone;
	private JFormattedTextField tfCPF;
	private JFormattedTextField tfCNPJ;
	public JTable tabelaTodos;
	private JTable tabelaAdd;
	private DefaultTableModel modeloAdd;

	public TelaCadastroFornecedor() {
		super("Cadastrar Fornecedor");
		addLabels();
		addCamposDeTexto();
		addRadioButton();
		addBotoes();
		OuvinteBotoes();
		tabelaTodosServicos();
		tabelaAddServicos();
		ouvinteJanela();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("CADASTRAR FORNECEDOR", 265, 15, 270, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Nome:", 125, 70, 50, 30));
		add(new Label("E-mail:",380, 70, 50, 30));
		add(new Label("Telefone:", 380, 120, 53, 30));
		add(new Label("Serviços deste fornecedor", 121, 205, 150, 30));
		add(new Label("Todos os Serviços", 530, 205, 100, 30));
	}

	public void addCamposDeTexto() {
		tfNome = new CampoDeTexto("", 173, 70, 180, 30);
		tfNome.addFocusListener(new OuvinteDeFoco(tfNome));
		add(tfNome);

		tfEmail = new CampoDeTexto("", 440, 70, 240, 30);
		tfEmail.addFocusListener(new OuvinteDeFoco(tfEmail));
		add(tfEmail);

		try {
			MaskFormatter maskTel = new MaskFormatter("(##)#.####-####");
			tfTelefone = new JFormattedTextField(maskTel);
			tfTelefone.addFocusListener(new OuvinteFocoFormatted(tfTelefone));
			tfTelefone.setBounds(440, 122, 240, 30);
			add(tfTelefone);

			MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
			tfCPF = new JFormattedTextField(maskCPF);
			tfCPF.addFocusListener(new OuvinteFocoFormatted(tfCPF));
			tfCPF.setBounds(178, 122, 175, 30);
			add(tfCPF);

			MaskFormatter maskCNPJ = new MaskFormatter("##.###.###/####-##");
			tfCNPJ = new JFormattedTextField(maskCNPJ);
			tfCNPJ.addFocusListener(new OuvinteFocoFormatted(tfCNPJ));
			tfCNPJ.setBounds(178, 162, 175, 30);
			add(tfCNPJ);
			tfCNPJ.setEditable(false);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	public void addRadioButton() {
		rbCPF = new RadioButton("CPF", 120, 130, 50, 15);
		add(rbCPF);

		rbCNPJ = new RadioButton("CNPJ", 120, 170, 56, 15);
		add(rbCNPJ);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbCPF);
		grupo.add(rbCNPJ);
		rbCPF.setSelected(true);

		rbCPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCPF.setEditable(true);
				tfCPF.setText("");
				tfCNPJ.setEditable(false);
			}
		});

		rbCNPJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCNPJ.setEditable(true);
				tfCNPJ.setText("");
				tfCPF.setEditable(false);
			}
		});
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

	public void tabelaTodosServicos() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");

		for(Servico s: ci.getTodosOsServicos()) {
			Object[] linha = new Object[1];
			linha[0] = s.getServico();
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

	public void tabelaAddServicos() {
		modeloAdd = new DefaultTableModel();
		modeloAdd.addColumn("Nome");

		for(Servico s: ci.getServicosTemp()) {
			Object[] linha = new Object[1];
			linha[0] = s.getServico();
			modeloAdd.addRow(linha);
		}

		tabelaAdd = new JTable(modeloAdd);
		for(int i=0;i<tabelaAdd.getColumnCount();i++) {
			tabelaAdd.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloAdd);
		tabelaAdd.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaAdd);
		painelScrow.setBounds(20, 230, 372, 205);
		add(painelScrow);
	}

	public void ouvinteJanela() {
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				ci.setServicosTemp(new ArrayList<>());
				p.salvarCentral(ci);
			}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		});
	}

	public void OuvinteBotoes() {
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaAdd.getSelectedRow() != -1) {
					String servico = tabelaAdd.getValueAt(tabelaAdd.getSelectedRow(), 0).toString();
					modeloAdd.removeRow(tabelaAdd.getSelectedRow());
					ci.getServicosTemp().remove(ci.buscaServico(servico));
					p.salvarCentral(ci);
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodos.getSelectedRow() != -1) {
					String servico = tabelaTodos.getValueAt(tabelaTodos.getSelectedRow(), 0).toString();
					if(ci.adicionarServicosTemp(ci.buscaServico(servico))) {
						Object[] row = new Object[1];
						row[0] = ci.buscaServico(servico);
						modeloAdd.addRow(row);
						p.salvarCentral(ci);
					}
				}
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ci.setServicosTemp(new ArrayList<>());
				p.salvarCentral(ci);
				dispose();
				new TelaFornecedores();
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = tfNome.getText();
				String email = tfEmail.getText();
				String telefone = tfTelefone.getText();
				String cpf_cnpj = "";

				if(rbCPF.isSelected() && ValidaCPF.isCPF(tfCPF.getText())) {
					cpf_cnpj = tfCPF.getText();
				}
				else if (rbCPF.isSelected() && ValidaCPF.isCPF(tfCPF.getText()) == false){
					JOptionPane.showMessageDialog(null, "CPF vazio ou inválido!");
					return;
				}
				else if(rbCNPJ.isSelected() && ValidaCNPJ.isCNPJ(tfCNPJ.getText())) {
					cpf_cnpj = tfCNPJ.getText();
				}
				else{
					JOptionPane.showMessageDialog(null, "CNPJ vazio ou inválido!");
					return;
				}

				if(!nome.equals("") && !email.equals("") && !telefone.equals("")) {
					if(email != null) {
						if(VerificaEmail.isValid(email)) {
							if(ci.adicionarFornecedor(new Fornecedor(nome, email, cpf_cnpj, telefone, ci.getServicosTemp()))) {
								ci.setServicosTemp(new ArrayList<>());
								p.salvarCentral(ci);
								dispose();
								new TelaFornecedores();
							}
							else {
								JOptionPane.showMessageDialog(null, "Esse CPF ou CNPJ já existe no banco de dados!");
								tfCPF.setText("");
								tfCNPJ.setText("");
								return;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "E-mail inválido!");
							return;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Os campos de textos devem ser preenchidos!");
					return;
				}
			}
		});
	}
}