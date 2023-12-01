package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import Controller.AlinhaCelulas;
import Controller.CentralDeInformacoes;
import Controller.Persistencia;
import Controller.ValidaCNPJ;
import Controller.VerificaEmail;
import DAO.Fornecedor;
import DAO.Servico;
import ElementosGraficos.Botao;
import ElementosGraficos.CampoDeTexto;
import ElementosGraficos.Fontes;
import ElementosGraficos.Label;
import ElementosGraficos.RadioButton;

public class TelaDetalharFornecedor extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private RadioButton rbCPF;
	private RadioButton rbCNPJ;
	private Botao btnExcluir;
	private Botao btnEditar;
	private Botao btnAdicionar;
	private Botao btnVoltar;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private JFormattedTextField tfTelefone;
	private JFormattedTextField tfCPF;
	private JFormattedTextField tfCNPJ;
	private JTable tabelaTodos;
	private JTable tabelaAdd;
	private Fornecedor fornecedorTemp = ci.getFornecedorTemp();
	private DefaultTableModel modeloAdd;
	private RadioButton rbFechou;
	private RadioButton rbBloqueado;
	private JTextArea taATA;
	private boolean editavel = true;

	public TelaDetalharFornecedor() {
		super("Detalhes e Edição Fornecedor");
		addRadioButtonDisponivel();
		textAta();
		addCamposDeTexto();
		addLabels();
		addRadioButton();
		addBotoes();
		ouvinteBotoes();
		tabelaTodosServicos();
		tabelaAddServicos();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("DETALHAR/EDITAR FORNECEDOR", 225, 15, 350, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Nome:", 20, 70, 50, 30));
		add(new Label("Comentários", 640, 45, 68, 30));
		add(new Label("E-mail:",275, 70, 50, 30));
		add(new Label("Telefone:", 275, 120, 53, 30));
		add(new Label("Serviços deste fornecedor", 131, 205, 150, 30));
		add(new Label("Todos os Serviços", 527, 205, 100, 30));
	}

	public void textAta() {
		taATA = new JTextArea(8,15);
		taATA.setText(fornecedorTemp.getComentarios());
		JScrollPane sp = new JScrollPane(taATA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel jp = new JPanel();
		jp.add(sp);
		jp.setBounds(580, 65, 192, 135);
		add(jp);
		taATA.setEnabled(false);
	}

	public void addRadioButtonDisponivel() {
		rbFechou = new RadioButton("O fornecedor fechou", 250, 170, 140, 20);
		add(rbFechou);

		rbBloqueado = new RadioButton("O fornecedor foi Bloqueado", 400, 170, 178, 20);
		add(rbBloqueado);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbFechou);
		grupo.add(rbBloqueado);

		if(fornecedorTemp.getDiponivel().equals("O fornecedor fechou")) {
			rbFechou.setSelected(true);
			editavel = false;
		}
		if(fornecedorTemp.getDiponivel().equals("O fornecedor foi Bloqueado")) {
			rbBloqueado.setSelected(true);
			editavel = false;
		}
	}

	public void addCamposDeTexto() {
		tfNome = new CampoDeTexto(fornecedorTemp.getNome(), 68, 70, 180, 30);
		add(tfNome);

		tfEmail = new CampoDeTexto(fornecedorTemp.getEmail(), 335, 70, 240, 30);
		add(tfEmail);

		try {
			MaskFormatter maskTel = new MaskFormatter("(##)#.####-####");
			tfTelefone = new JFormattedTextField(maskTel);
			tfTelefone.setText(fornecedorTemp.getTelefone());
			tfTelefone.setBounds(335, 122, 240, 30);
			add(tfTelefone);

			MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
			tfCPF = new JFormattedTextField(maskCPF);
			tfCPF.setBounds(73, 122, 175, 30);
			add(tfCPF);

			MaskFormatter maskCNPJ = new MaskFormatter("##.###.###/####-##");
			tfCNPJ = new JFormattedTextField(maskCNPJ);
			tfCNPJ.setBounds(73, 162, 175, 30);
			add(tfCNPJ);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		tfNome.setEnabled(editavel);
		tfEmail.setEnabled(editavel);
		tfTelefone.setEnabled(editavel);
		tfCPF.setEnabled(editavel);
		tfCNPJ.setEnabled(editavel);
	}

	public void addRadioButton() {
		rbCPF = new RadioButton("CPF", 15, 130, 50, 15);
		add(rbCPF);

		rbCNPJ = new RadioButton("CNPJ", 15, 170, 56, 15);
		add(rbCNPJ);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbCPF);
		grupo.add(rbCNPJ);

		if(fornecedorTemp.getCPF_CNPJ().length()==14) {
			tfCPF.setEditable(false);
			rbCPF.setSelected(true);
			tfCPF.setText(fornecedorTemp.getCPF_CNPJ());
		}
		else {
			tfCNPJ.setEditable(false);
			rbCNPJ.setSelected(true);
			tfCNPJ.setText(fornecedorTemp.getCPF_CNPJ());

			tfCPF.setEditable(false);
			rbCPF.setEnabled(false);
		}
		rbCPF.setEnabled(editavel);
		rbCNPJ.setEnabled(editavel);
	}

	public void addBotoes() {
		btnExcluir = new Botao("Excluir", 155, 445, 120, 30);
		add(btnExcluir);

		btnAdicionar = new Botao("Adicionar", 520, 445, 120, 30);
		add(btnAdicionar);

		btnEditar = new Botao("Editar", 250, 515, 120, 30);
		add(btnEditar);

		btnVoltar = new Botao("Voltar", 430, 515, 120, 30);
		add(btnVoltar);

		btnExcluir.setEnabled(editavel);
		btnAdicionar.setEnabled(editavel);
		btnEditar.setEnabled(editavel);
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

		for(Servico s: fornecedorTemp.getServicos()) {
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

	public void ouvinteBotoes() {
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaFornecedores();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaAdd.getSelectedRow() != -1) {
					String servico = tabelaAdd.getValueAt(tabelaAdd.getSelectedRow(), 0).toString();
					modeloAdd.removeRow(tabelaAdd.getSelectedRow());
					fornecedorTemp.getServicos().remove(ci.buscaServico(servico));
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodos.getSelectedRow() != -1) {
					String servico = tabelaTodos.getValueAt(tabelaTodos.getSelectedRow(), 0).toString();
					if(fornecedorTemp.adicionarServico(ci.buscaServico(servico))) {
						Object[] row = new Object[1];
						row[0] = ci.buscaServico(servico);
						modeloAdd.addRow(row);
					}
				}
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = tfNome.getText();
				String email = tfEmail.getText();
				String telefone = tfTelefone.getText();
				String novo_cpf_cnpj = "";
				String antigo_cpf_cnpj = "";
				boolean mudou = false;

				if(rbBloqueado.isSelected()) {
					fornecedorTemp.setDiponivel(rbBloqueado.getText());
				}
				if(rbFechou.isSelected()) {
					fornecedorTemp.setDiponivel(rbFechou.getText());
				}

				if(fornecedorTemp.getCPF_CNPJ().length()==14) {
					if(rbCNPJ.isSelected() && ValidaCNPJ.isCNPJ(tfCNPJ.getText())) {
						antigo_cpf_cnpj = tfCPF.getText();
						novo_cpf_cnpj = tfCNPJ.getText();
						mudou = true;
					}
				}

				if(!nome.equals("") && !email.equals("") && !telefone.equals("")) {
					if(email != null) {
						if(VerificaEmail.isValid(email)) {

							Fornecedor novofornecedor;
							if(mudou) {
								novofornecedor = ci.buscaFornecedor(antigo_cpf_cnpj);
								novofornecedor.setCPF_CNPJ(novo_cpf_cnpj);
							}
							else {
								novofornecedor = ci.buscaFornecedor(fornecedorTemp.getCPF_CNPJ());
							}
							novofornecedor.setEmail(email);
							novofornecedor.setNome(nome);
							novofornecedor.setTelefone(telefone);
							novofornecedor.setComentarios(taATA.getText());
							novofornecedor.setQtdContratos(fornecedorTemp.getQtdContratos());
							novofornecedor.setValor(fornecedorTemp.getValor());
							novofornecedor.setServicos(fornecedorTemp.getServicos());
							p.salvarCentral(ci);
							dispose();
							new TelaFornecedores();
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