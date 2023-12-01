package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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

import ElementosGraficos.Botao;
import ElementosGraficos.CampoDeTexto;
import ElementosGraficos.Fontes;
import ElementosGraficos.Label;
import ElementosGraficos.RadioButton;
import Controller.AlinhaCelulas;
import Controller.CentralDeInformacoes;
import Controller.OuvinteDeFoco;
import Controller.OuvinteFocoFormatted;
import Controller.Persistencia;
import Controller.ValidaCNPJ;
import Controller.ValidaCPF;
import Controller.VerificaEmail;
import DAO.Fornecedor;
import DAO.Servico;

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
	private Fornecedor novoFornecedor = new Fornecedor();

	public TelaCadastroFornecedor() {
		super("Cadastrar Fornecedor");
		addLabels();
		addCamposDeTexto();
		addRadioButton();
		addBotoes();
		OuvinteBotoes();
		tabelaTodosServicos();
		tabelaAddServicos();
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

		for(Servico s: novoFornecedor.getServicos()) {
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

	public void OuvinteBotoes() {
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaAdd.getSelectedRow() != -1) {
					String servico = tabelaAdd.getValueAt(tabelaAdd.getSelectedRow(), 0).toString();
					modeloAdd.removeRow(tabelaAdd.getSelectedRow());
					novoFornecedor.getServicos().remove(ci.buscaServico(servico));
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodos.getSelectedRow() != -1) {
					String servico = tabelaTodos.getValueAt(tabelaTodos.getSelectedRow(), 0).toString();
					if(novoFornecedor.adicionarServico(ci.buscaServico(servico))) {
						Object[] row = new Object[1];
						row[0] = ci.buscaServico(servico);
						modeloAdd.addRow(row);
					}
				}
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				if(novoFornecedor.getServicos().size()==0) {
					JOptionPane.showMessageDialog(null, "Adicione pelo menos um Serviço!");
					return;
				}

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
							novoFornecedor.setNome(nome);
							novoFornecedor.setEmail(email);
							novoFornecedor.setCPF_CNPJ(cpf_cnpj);
							novoFornecedor.setTelefone(telefone);
							if(ci.adicionarFornecedor(novoFornecedor)) {
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