package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Pacote novoPacote = new Pacote();

	public TelaCadastroPacote() {
		super("Cadastrar Pacote");
		addLabels();
		addCamposDeTexto();
		addBotoes();
		addTextArea();
		ouvinteBotoes();
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
					novoPacote.getFornecedores().remove(ci.buscaFornecedor(cpf_cnpj));
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodos.getSelectedRow() != -1) {
					String cpf_cnpj = tabelaTodos.getValueAt(tabelaTodos.getSelectedRow(), 1).toString();
					if(novoPacote.adicionarFornecedor(ci.buscaFornecedor(cpf_cnpj))) {
						Fornecedor f = ci.buscaFornecedor(cpf_cnpj);
						Object[] row = new Object[3];
						row[0] = f.getNome();
						row[1] = f.getCPF_CNPJ();
						row[2] = f.getQtdContratos();
						modelo.addRow(row);
					}
				}
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nomePacote = tfNomePacote.getText();
					float valor = Float.parseFloat(tfValor.getText());
					String caracteristicas = taCaracteristicas.getText();

					if(!nomePacote.equals("") && !caracteristicas.equals("")) {
						novoPacote.setNomePacote(nomePacote);
						novoPacote.setCaracteristicas(caracteristicas);
						novoPacote.setValor(valor);

						if(ci.adicionarPacote(novoPacote)) {
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
				dispose();
				new TelaPacotes();
			}
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

		for(Fornecedor f : novoPacote.getFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getQtdContratos();
			modelo.addRow(linha);
		}
		tabelaAdd = new JTable(modelo);
		for(int i=0;i<tabelaAdd.getColumnCount();i++) {
			tabelaAdd.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabelaAdd.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabelaAdd);
		painelScrow.setBounds(20, 230, 372, 205);
		add(painelScrow);
	}
}