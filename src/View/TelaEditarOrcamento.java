package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
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

import Controller.AlinhaCelulas;
import Controller.CentralDeInformacoes;
import Controller.LimitaCaracteres;
import Controller.Persistencia;
import DAO.ClienteDAOImpl;
import DAO.Fornecedor;
import DAO.Orcamento;
import DAO.Pacote;
import ElementosGraficos.Botao;
import ElementosGraficos.CampoDeTexto;
import ElementosGraficos.CheckBox;
import ElementosGraficos.Fontes;
import ElementosGraficos.Label;
import ElementosGraficos.RadioButton;

public class TelaEditarOrcamento extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private JFormattedTextField tfDataHora;
	private CampoDeTexto tfNomeEvento;
	private CampoDeTexto tfLocalEvento;
	private CampoDeTexto tfQtdConvidados;
	private Botao btnEditar;
	private Botao btnVoltar;
	private Botao btnExcluirFornecedor;
	private Botao btnAdicionarFornecedor;
	private Botao btnExcluirPacote;
	private Botao btnAdicionarPacote;
	private RadioButton rbCerimonialista;
	private RadioButton rbCliente;
	private Label lbSoma;
	private CheckBox cbTipo;
	private JTable tabelaTodosFornecedores;
	private JTable tabelaAddFornecedores;
	private JTable tabelaTodosPacotes;
	private JTable tabelaAddPacotes;
	private DefaultTableModel modeloFornecedores;
	private DefaultTableModel modeloPacotes;
	private ClienteDAOImpl clienteTemp = ci.getClienteTemp();

	public TelaEditarOrcamento () {
		super("Editar Orçamentos");
		setSize(800, 720);
		setLocationRelativeTo(null);
		addCheckBox();
		addRadioButtons();
		addLabels();
		addCamposDeTexto();
		addBotoes();
		ouvinteBotoes();
		tabelaTodosFornecedores();
		tabelaAddFornecedores();
		tabelaTodosPacotes();
		tabelaAddPacotes();
		preencheComponentes();
		verificacao();
		setVisible(true);
	}
	public void verificacao() {
		if(clienteTemp.getOrcamento().getTipo().equals("Concluído")) {
			tfDataHora.setEnabled(false);
			tfNomeEvento.setEnabled(false);
			tfLocalEvento.setEnabled(false);
			tfQtdConvidados.setEnabled(false);
			btnEditar.setEnabled(false);
			btnExcluirFornecedor.setEnabled(false);
			btnAdicionarFornecedor.setEnabled(false);
			btnExcluirPacote.setEnabled(false);
			btnAdicionarPacote.setEnabled(false);
			rbCerimonialista.setEnabled(false);
			rbCliente.setEnabled(false);
			lbSoma.setEnabled(false);
			cbTipo.setEnabled(false);
			cbTipo.setSelected(true);
		}
	}

	public void preencheComponentes() {
		if(clienteTemp.getOrcamento().getTipo().equals("Contrato")) {
			cbTipo.setSelected(true);
			cbTipo.setEnabled(false);
		}
		tfNomeEvento.setText(clienteTemp.getOrcamento().getNomeEvento());
		DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
		tfDataHora.setText(clienteTemp.getOrcamento().getDataHora().format(parser));
		tfLocalEvento.setText(clienteTemp.getOrcamento().getLocalEvento());
		tfQtdConvidados.setText(clienteTemp.getOrcamento().getQtdConvidados());

		if(clienteTemp.getOrcamento().getResponsavelPagamento().equals("Cliente"))
			rbCliente.setSelected(true);
		else
			rbCerimonialista.setSelected(true);
		lbSoma.setText(clienteTemp.getOrcamento().getValor()+"");
	}

	public void addCheckBox() {
		cbTipo = new CheckBox("Contrato Efetivo", 510, 70, 150, 20);
		add(cbTipo);
	}

	public void addRadioButtons() {
		rbCerimonialista = new RadioButton("Cerimonialista", 585, 160, 100, 20);
		add(rbCerimonialista);

		rbCliente = new RadioButton("Cliente", 510, 160, 65, 20);
		add(rbCliente);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbCerimonialista);
		grupo.add(rbCliente);
	}

	public void addLabels() {
		Label lbTitulo = new Label("EDITAR ORÇAMENTOS", 270, 20, 240, 25);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);

		add(new Label("Nome do evento:", 170, 70, 100, 20));
		add(new Label("Data e hora:", 170, 115, 100, 20));
		add(new Label("Local do evento:", 170, 160, 100, 20));
		add(new Label("Quantidade de convidados:", 170, 205, 150, 20));
		add(new Label("Responsável pelo pagamento", 516, 135, 160, 20));

		add(new Label("Fornecedores deste orçamento", 120, 235, 200, 30));
		add(new Label("Todos os fornecedores", 513, 235, 150, 30));

		add(new Label("Soma dos montantes:", 280, 405, 120, 30));
		lbSoma = new Label("0.0", 405, 405, 200, 30);
		add(lbSoma);

		add(new Label("Pacotes deste orçamento", 132, 435, 200, 30));
		add(new Label("Todos os pacotes", 528, 435, 150, 30));
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
		btnEditar = new Botao("Editar", 250, 625, 120, 30);
		add(btnEditar);

		btnExcluirFornecedor = new Botao("Excluir", 145, 370, 120, 30);
		add(btnExcluirFornecedor);

		btnAdicionarFornecedor = new Botao("Adicionar", 515, 370, 120, 30);
		add(btnAdicionarFornecedor);

		btnExcluirPacote = new Botao("Excluir", 145, 570, 120, 30);
		add(btnExcluirPacote);

		btnAdicionarPacote = new Botao("Adicionar", 515, 570, 120, 30);
		add(btnAdicionarPacote);

		btnVoltar = new Botao("Voltar", 430, 625, 120, 30);
		add(btnVoltar);
	}

	public static boolean isNumeric(String s) {
		if (s == null || s.equals(""))
			return false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
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
					soma -= clienteTemp.buscaFornecedor(cpf_cnpj).getValor();
					clienteTemp.getOrcamento().getFornecedores().remove(clienteTemp.buscaFornecedor(cpf_cnpj));
					lbSoma.setText(""+soma);
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
						if(isNumeric(resultado)) {
							if(clienteTemp.adicionarFornecedor(fornecedorTemp)) {
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
					else {
						JOptionPane.showMessageDialog(null, "Esse fornecedor não está disponível!");
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
					soma -= clienteTemp.buscaPacote(nomePacote).getValor();
					clienteTemp.getOrcamento().getPacotes().remove(clienteTemp.buscaPacote(nomePacote));
					lbSoma.setText(""+soma);
				}
			}
		});

		btnAdicionarPacote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodosPacotes.getSelectedRow() != -1) {

					String nomePacote = tabelaTodosPacotes.getValueAt(tabelaTodosPacotes.getSelectedRow(), 0).toString();
					if(clienteTemp.adicionarPacote(ci.buscaPacote(nomePacote))) {

						Pacote pacote = clienteTemp.buscaPacote(nomePacote);

						String fornecedores = "";
						for (Fornecedor f : pacote.getFornecedores())
							fornecedores += f.getNome();

						float soma = Float.parseFloat(lbSoma.getText());
						soma += pacote.getValor();
						lbSoma.setText(""+soma);

						Object[] linha = new Object[3];

						linha[0] = pacote.getNomePacote();
						linha[1] = fornecedores;
						linha[2] = pacote.getValor();

						modeloPacotes.addRow(linha);
					}
				}
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeEvento = tfNomeEvento.getText();
				String localEvento = tfLocalEvento.getText();
				String qtdConvidados = tfQtdConvidados.getText();
				float somaTotal = Float.parseFloat(lbSoma.getText());

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

						Orcamento novoOrcamento = new Orcamento(nomeEvento, dataHora, localEvento, qtdConvidados,
								clienteTemp.getOrcamento().getFornecedores(),clienteTemp.getOrcamento().getPacotes(), somaTotal, responsavelPagamento);

						novoOrcamento.setDataModificacao(LocalDateTime.now());
						if(cbTipo.isSelected())
							novoOrcamento.setTipo("Contrato");

						ClienteDAOImpl cliente = ci.buscaCliente(clienteTemp.getCPF_CNPJ());
						cliente.setOrcamento(novoOrcamento);

						ci.setClienteTemp(new ClienteDAOImpl());
						p.salvarCentral(ci);
						dispose();
						new TelaOrcamentos();

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
		painelScrow.setBounds(393, 262, 372, 105);
		add(painelScrow);
	}

	public void tabelaAddFornecedores() {
		modeloFornecedores = new DefaultTableModel();
		modeloFornecedores.addColumn("Nome");
		modeloFornecedores.addColumn("Física/Jurídica");
		modeloFornecedores.addColumn("Valor");

		for(Fornecedor f : clienteTemp.getOrcamento().getFornecedores()) {
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
		painelScrow.setBounds(20, 262, 372, 105);
		add(painelScrow);
	}

	public void tabelaTodosPacotes() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Fonecedores");
		modelo.addColumn("Valor");

		for (Pacote pacote : ci.getTodosOsPacotes()) {
			String fornecedores = "";

			for (Fornecedor f : pacote.getFornecedores()) {
				fornecedores += f.getNome();
			}
			Object[] linha = new Object[3];
			linha[0] = pacote.getNomePacote();
			linha[1] = fornecedores;
			linha[2] = pacote.getValor();

			modelo.addRow(linha);
		}
		tabelaTodosPacotes = new JTable(modelo);
		for(int i=0;i<tabelaTodosPacotes.getColumnCount();i++) {
			tabelaTodosPacotes.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabelaTodosPacotes.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaTodosPacotes);
		painelScrow.setBounds(393, 462, 372, 105);
		add(painelScrow);
	}

	public void tabelaAddPacotes() {
		modeloPacotes = new DefaultTableModel();
		modeloPacotes.addColumn("Nome");
		modeloPacotes.addColumn("Fonecedores");
		modeloPacotes.addColumn("Valor");

		for (Pacote pacote : clienteTemp.getOrcamento().getPacotes()) {
			String fornecedores = "";
			for (Fornecedor f : pacote.getFornecedores()) {
				fornecedores += f.getNome();
			}
			Object[] linha = new Object[3];

			linha[0] = pacote.getNomePacote();
			linha[1] = fornecedores;
			linha[2] = pacote.getValor();

			modeloPacotes.addRow(linha);
		}
		tabelaAddPacotes = new JTable(modeloPacotes);
		for(int i=0;i<tabelaAddPacotes.getColumnCount();i++) {
			tabelaAddPacotes.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloPacotes);
		tabelaAddPacotes.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaAddPacotes);
		painelScrow.setBounds(20, 462, 372, 105);
		add(painelScrow);
	}
}