package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
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
import Logica.Cliente;
import Logica.Fornecedor;
import Logica.LimitaCaracteres;
import Logica.Orcamento;
import Logica.Pacote;
import Logica.Persistencia;

public class TelaCadastroOrcamento extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CampoDeTexto tfNomeEvento;
	private JFormattedTextField tfDataHora;
	private CampoDeTexto tfLocalEvento;
	private CampoDeTexto tfQtdConvidados;
	private Botao btnCadastrar;
	private Botao btnVoltar;
	private Botao btnExcluirFornecedor;
	private Botao btnAdicionarFornecedor;
	private Botao btnExcluirPacote;
	private Botao btnAdicionarPacote;
	private JTable tabelaTodosFornecedores;
	private JTable tabelaAddFornecedores;
	private JTable tabelaTodosPacotes;
	private JTable tabelaAddPacotes;
	private DefaultTableModel modeloFornecedores;
	private DefaultTableModel modeloPacotes;
	private JComboBox<Cliente> jcbClientes;
	private RadioButton rbCerimonialista;
	private RadioButton rbCliente;
	private Label lbSoma;
	private Orcamento novoOrcamento = new Orcamento();

	public TelaCadastroOrcamento () {
		super("Cadastro Orçamento");
		setSize(800, 900);
		setLocationRelativeTo(null);
		addRadioButtons();
		addLabels();
		addComboBox();
		addCamposDeTexto();
		addBotoes();
		ouvinteBotoes();
		tabelaTodosFornecedores();
		tabelaAddFornecedores();
		tabelaTodosPacotes();
		tabelaAddPacotes();
		setVisible(true);
	}

	public void addRadioButtons() {
		rbCerimonialista = new RadioButton("Cerimonialista", 585, 160, 100, 20);
		add(rbCerimonialista);

		rbCliente = new RadioButton("Cliente", 510, 160, 65, 20);
		add(rbCliente);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbCerimonialista);
		grupo.add(rbCliente);
		rbCerimonialista.setSelected(true);
	}

	public void addLabels() {
		Label lbTitulo = new Label("Cadastrar Orçamento", 280, 20, 240, 25);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);

		add(new Label("Nome do evento:", 170, 70, 100, 20));
		add(new Label("Data e hora:", 170, 115, 100, 20));
		add(new Label("Local do evento:", 170, 160, 100, 20));
		add(new Label("Quantidade de convidados:", 170, 205, 150, 20));
		add(new Label("Cliente deste orçamento:", 387, 205, 150, 20));
		add(new Label("Responsável pelo pagamento", 516, 135, 160, 20));

		add(new Label("Fornecedores deste orçamento", 120, 235, 200, 30));
		add(new Label("Todos os fornecedores", 513, 235, 150, 30));

		add(new Label("Soma dos montantes:", 280, 505, 120, 30));
		lbSoma = new Label("0.0", 405, 505, 200, 30);
		add(lbSoma);

		add(new Label("Pacotes deste orçamento", 132, 535, 200, 30));
		add(new Label("Todos os pacotes", 528, 535, 150, 30));
	}

	public void addComboBox() {
		jcbClientes = new JComboBox<>();
		jcbClientes.setBounds(529, 200, 150, 30);
		for (Cliente c : ci.getTodosOsClientes()) {
			jcbClientes.addItem(c);
		}
		add(jcbClientes);
	}

	public void addCamposDeTexto() {
		tfNomeEvento = new CampoDeTexto("", 285, 65, 200, 30);
		tfNomeEvento.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NOME));
		add(tfNomeEvento);

		try {
			MaskFormatter mascara = new MaskFormatter("##/##/#### ##:##");
			tfDataHora = new JFormattedTextField(mascara);
			tfDataHora.setBounds(285, 110, 200, 30);
			add(tfDataHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tfLocalEvento = new CampoDeTexto("", 285, 155, 200, 30);
		add(tfLocalEvento);

		tfQtdConvidados = new CampoDeTexto("", 325, 200, 50, 30);
		tfQtdConvidados.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NUMERO));
		add(tfQtdConvidados);
	}

	public void addBotoes() {	
		btnCadastrar = new Botao("Cadastrar", 250, 825, 120, 30);
		add(btnCadastrar);

		btnExcluirFornecedor = new Botao("Excluir", 145, 470, 120, 30);
		add(btnExcluirFornecedor);

		btnAdicionarFornecedor = new Botao("Adicionar", 515, 470, 120, 30);
		add(btnAdicionarFornecedor);

		btnExcluirPacote = new Botao("Excluir", 145, 770, 120, 30);
		add(btnExcluirPacote);

		btnAdicionarPacote = new Botao("Adicionar", 515, 770, 120, 30);
		add(btnAdicionarPacote);

		btnVoltar = new Botao("Voltar", 430, 825, 120, 30);
		add(btnVoltar);
	}

	public static boolean isNumeric(String s) {
		if (s == null || s.equals("")) {
			return false;
		}
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9')
				return false;
		}
		return true;
	}

	public void ouvinteBotoes() {
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaOrcamentos();
			}
		});

		btnExcluirFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaAddFornecedores.getSelectedRow() != -1) {
					String cpf_cnpj = tabelaAddFornecedores.getValueAt(tabelaAddFornecedores.getSelectedRow(), 1).toString();
					modeloFornecedores.removeRow(tabelaAddFornecedores.getSelectedRow());
					float soma = Float.parseFloat(lbSoma.getText());
					soma -= novoOrcamento.buscaFornecedor(cpf_cnpj).getValor();
					lbSoma.setText(""+soma);
					novoOrcamento.getFornecedores().remove(novoOrcamento.buscaFornecedor(cpf_cnpj));
				}
			}
		});

		btnAdicionarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodosFornecedores.getSelectedRow() != -1) {
					String cpf_cnpj = tabelaTodosFornecedores.getValueAt(tabelaTodosFornecedores.getSelectedRow(), 1).toString();
					if(ci.buscaFornecedor(cpf_cnpj).getDiponivel().equals("Sim")) {
						Fornecedor fornecedorTemp = ci.buscaFornecedor(cpf_cnpj);
						String resultado = JOptionPane.showInputDialog(null, "Digite o valor para esse fornecedor:");
						if(resultado != null) {
							if(novoOrcamento.adicionarFornecedor(fornecedorTemp)) {
								if(resultado.equals("")) {
									resultado = "0";
								}
								if(isNumeric(resultado)) {
									fornecedorTemp.setValor(Float.parseFloat(resultado));

									float soma = Float.parseFloat(lbSoma.getText());
									soma += fornecedorTemp.getValor();
									lbSoma.setText(""+soma);

									Object[] row = new Object[3];
									row[0] = fornecedorTemp.getNome();
									row[1] = fornecedorTemp.getCPF_CNPJ();
									row[2] = fornecedorTemp.getValor();
									modeloFornecedores.addRow(row);
								}
							}
						}
					}
				}
			}
		});

		btnExcluirPacote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaAddPacotes.getSelectedRow() != -1) {
					String nomePacote = tabelaAddPacotes.getValueAt(tabelaAddPacotes.getSelectedRow(), 0).toString();
					modeloPacotes.removeRow(tabelaAddPacotes.getSelectedRow());

					float soma = Float.parseFloat(lbSoma.getText());
					soma -= novoOrcamento.buscaPacote(nomePacote).getValor();
					lbSoma.setText(""+soma);

					novoOrcamento.getPacotes().remove(novoOrcamento.buscaPacote(nomePacote));
				}
			}
		});

		btnAdicionarPacote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodosPacotes.getSelectedRow() != -1) {
					String nomePacote = tabelaTodosPacotes.getValueAt(tabelaTodosPacotes.getSelectedRow(), 0).toString();
					Pacote pacoteTemp = ci.buscaPacote(nomePacote);
					if(novoOrcamento.adicionarPacote(pacoteTemp)) {
						String nomeFornecedores = "";
						for (Fornecedor f : novoOrcamento.getFornecedores())///////////////////////////////////
							nomeFornecedores += f.getNome();

						float soma = Float.parseFloat(lbSoma.getText());
						soma += pacoteTemp.getValor();
						lbSoma.setText(""+soma);

						Object[] linha = new Object[3];

						linha[0] = pacoteTemp.getNomePacote();
						linha[1] = pacoteTemp.getValor();
						linha[2] = nomeFornecedores;

						modeloPacotes.addRow(linha);
					}
				}
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeEvento = tfNomeEvento.getText();
				String localEvento = tfLocalEvento.getText();
				String qtdConvidados = tfQtdConvidados.getText();
				float somaTotal = Float.parseFloat(lbSoma.getText());
				Cliente cliente = (Cliente) jcbClientes.getSelectedItem();

				String responsavelPagamento = "";
				if(rbCerimonialista.isSelected())
					responsavelPagamento = rbCerimonialista.getText();
				else if (rbCliente.isSelected())
					responsavelPagamento = rbCliente.getText();

				try {
					if(!nomeEvento.equals("") && !localEvento.equals("") && !qtdConvidados.equals("") && tfDataHora.getText().length()==16) {
						String tempoI1 = tfDataHora.getText();
						DateTimeFormatter dataIf = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
						LocalDateTime dataHora = LocalDateTime.parse(tempoI1, dataIf);

						if(dataHora.isBefore(LocalDateTime.now())) {
							JOptionPane.showMessageDialog(null, "Essa data já ocorreu!");
							return;
						}


						if(cliente.getOrcamento() == null) {
							novoOrcamento.setNomeEvento(nomeEvento);
							novoOrcamento.setDataHora(dataHora);
							novoOrcamento.setLocalEvento(localEvento);
							novoOrcamento.setQtdConvidados(qtdConvidados);
							novoOrcamento.setValor(somaTotal);
							novoOrcamento.setResponsavelPagamento(responsavelPagamento);

							cliente.setOrcamento(novoOrcamento);
							p.salvarCentral(ci);
							dispose();
							new TelaOrcamentos();
						}
						else {
							JOptionPane.showMessageDialog(null, "Esse cliente já possui um orçamento!");
							return;
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Os campos de textos devem ser preenchidos!");
						return;
					}
				}catch (DateTimeParseException dtpe) {
					JOptionPane.showMessageDialog(null, "Data e hora inválida!");
				}
			}
		});
	}

	public void tabelaTodosFornecedores() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Física/Jurídica");
		modelo.addColumn("Diponível");

		for(Fornecedor f : ci.getTodosOsFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getDiponivel();
			modelo.addRow(linha);
		}
		tabelaTodosFornecedores = new JTable(modelo);
		for(int i=0;i<tabelaTodosFornecedores.getColumnCount();i++) {
			tabelaTodosFornecedores.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabelaTodosFornecedores.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaTodosFornecedores);
		painelScrow.setBounds(393, 262, 372, 205);
		add(painelScrow);
	}

	public void tabelaAddFornecedores() {
		modeloFornecedores = new DefaultTableModel();
		modeloFornecedores.addColumn("Nome");
		modeloFornecedores.addColumn("Física/Jurídica");
		modeloFornecedores.addColumn("Valor");

		for(Fornecedor f : novoOrcamento.getFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getValor();
			modeloFornecedores.addRow(linha);
		}
		tabelaAddFornecedores = new JTable(modeloFornecedores);
		for(int i=0;i<tabelaAddFornecedores.getColumnCount();i++) {
			tabelaAddFornecedores.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloFornecedores);
		tabelaAddFornecedores.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaAddFornecedores);
		painelScrow.setBounds(20, 262, 372, 205);
		add(painelScrow);
	}

	public void tabelaTodosPacotes() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Valor");
		modelo.addColumn("Fonecedores");

		for (Pacote pacote : ci.getTodosOsPacotes()) {
			String fornecedores = "";

			for (Fornecedor f : pacote.getFornecedores()) {
				fornecedores += f.getNome();
			}
			Object[] linha = new Object[3];
			linha[0] = pacote.getNomePacote();
			linha[1] = pacote.getValor();
			linha[2] = fornecedores;

			modelo.addRow(linha);
		}
		tabelaTodosPacotes = new JTable(modelo);
		for(int i=0;i<tabelaTodosPacotes.getColumnCount();i++) {
			tabelaTodosPacotes.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabelaTodosPacotes.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaTodosPacotes);
		painelScrow.setBounds(393, 562, 372, 205);
		add(painelScrow);
	}

	public void tabelaAddPacotes() {
		modeloPacotes = new DefaultTableModel();
		modeloPacotes.addColumn("Nome");
		modeloPacotes.addColumn("Valor");
		modeloPacotes.addColumn("Fonecedores");

		for (Pacote pacote : novoOrcamento.getPacotes()) {
			String fornecedores = "";
			for (Fornecedor f : pacote.getFornecedores()) {/////////////////////////////////////////////////////////////
				fornecedores += f.getNome();
			}
			Object[] linha = new Object[3];

			linha[0] = pacote.getNomePacote();
			linha[1] = pacote.getValor();
			linha[2] = fornecedores;

			modeloPacotes.addRow(linha);
		}
		tabelaAddPacotes = new JTable(modeloPacotes);
		for(int i=0;i<tabelaAddPacotes.getColumnCount();i++) {
			tabelaAddPacotes.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloPacotes);
		tabelaAddPacotes.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaAddPacotes);
		painelScrow.setBounds(20, 562, 372, 205);
		add(painelScrow);
	}
}