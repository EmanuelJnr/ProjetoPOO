package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ElementosGraficos.*;
import Controller.*;
import DAO.Fornecedor;
import DAO.Pacote;

public class TelaCadastroPacote extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CampoDeTexto tfNomePacote;
	private CampoDeTexto tfCaracteristicas;
	private Botao btnCadastrar;
	private Botao btnVoltar;
	private Botao btnExcluir;
	private Botao btnAdicionar;
	private JTable tabelaTodos;
	private JTable tabelaAdd;
	private DefaultTableModel modelo;
	private Label lbSoma;
	private Pacote novoPacote = new Pacote();

	public TelaCadastroPacote() {
		super("Cadastrar Pacote");
		addLabels();
		addCamposDeTexto();
		addBotoes();
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

		add(new Label("Soma dos montantes:", 70, 150, 120, 30));
		lbSoma = new Label("0.0", 220, 150, 200, 30);
		add(lbSoma);

		add(new Label("Fornecedores deste pacote", 126, 205, 200, 30));
		add(new Label("Todos os fornecedores", 513, 205, 150, 30));
	}

	public void addCamposDeTexto() {
		tfNomePacote = new CampoDeTexto("", 220, 100, 350, 25);
		tfNomePacote.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NOME));
		add(tfNomePacote);

		tfCaracteristicas = new CampoDeTexto("", 580, 67, 192, 25);
		add(tfCaracteristicas);
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
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaAdd.getSelectedRow() != -1) {
					String cpf_cnpj = tabelaAdd.getValueAt(tabelaAdd.getSelectedRow(), 1).toString();
					modelo.removeRow(tabelaAdd.getSelectedRow());
					float soma = Float.parseFloat(lbSoma.getText());
					soma -= ci.buscaFornecedor(cpf_cnpj).getValor();
					lbSoma.setText(""+soma);
					novoPacote.getFornecedores().remove(ci.buscaFornecedor(cpf_cnpj));
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaTodos.getSelectedRow() != -1) {
					String cpf_cnpj = tabelaTodos.getValueAt(tabelaTodos.getSelectedRow(), 1).toString();
					Fornecedor f = ci.buscaFornecedor(cpf_cnpj);
					if(f.getDiponivel().equals("Sim")) {
						String resultado = JOptionPane.showInputDialog(null, "Digite o valor para esse fornecedor:");
						if(isNumeric(resultado)) {
							if(novoPacote.adicionarFornecedor(f)) {
								f.setValor(Float.parseFloat(resultado));

								float soma = Float.parseFloat(lbSoma.getText());
								soma += f.getValor();
								lbSoma.setText(""+soma);

								Object[] row = new Object[3];
								row[0] = f.getNome();
								row[1] = f.getCPF_CNPJ();
								row[2] = f.getValor();
								modelo.addRow(row);
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Esse fornecedor não está disponível!");
					}
				}
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nomePacote = tfNomePacote.getText();
					float valor = Float.parseFloat(lbSoma.getText());
					String caracteristicas = tfCaracteristicas.getText();

					if(novoPacote.getFornecedores().size()==0) {
						JOptionPane.showMessageDialog(null, "Adicione pelo menos um Fornecedor!");
						return;
					}

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
		modelo.addColumn("Diponível");

		for(Fornecedor f : ci.getTodosOsFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getDiponivel();
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
		modelo.addColumn("Valor");

		for(Fornecedor f : novoPacote.getFornecedores()) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getValor();
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