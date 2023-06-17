package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Fornecedor;
import Logica.LimitaCaracteres;
import Logica.Orcamento;
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
	private Botao btnExcluir;
	private Botao btnAdicionar;
	private JTable tabelaTodos;
	private JTable tabelaAdd;
	private DefaultTableModel modelo;

	public TelaCadastroOrcamento () {
		super("Cadastro Orçamento");
		addLabels();
		addCamposDeTexto();
		addBotoes();
		ouvinteBotoes();
		ouvinteJanela();
		tabelaTodosFornecedores();
		tabelaAddFornecedores();
		setVisible(true);
	}
	public static void main(String[] args) {/////////////////////////////////////////////////////
		new TelaCadastroOrcamento();
	}

	public void addLabels() {
		Label lbTitulo = new Label("Cadastrar Orçamento", 280, 20, 240, 20);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);

		add(new Label("Nome do evento:", 190, 70, 100, 20));
		add(new Label("Data e hora:", 190, 115, 100, 20));
		add(new Label("Local do evento:", 190, 160, 100, 20));
		add(new Label("Quantidade de convidados:", 190, 205, 150, 20));
		add(new Label("Fornecedores deste orçamento", 126, 235, 200, 30));
		add(new Label("Todos os fornecedores", 513, 235, 150, 30));
	}

	public void addCamposDeTexto() {
		tfNomeEvento = new CampoDeTexto("", 380, 65, 200, 30);
		tfNomeEvento.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NOME));
		add(tfNomeEvento);

		try {
			MaskFormatter mascara = new MaskFormatter("##/##/#### ##:##");
			tfDataHora = new JFormattedTextField(mascara);
			tfDataHora.setBounds(380, 110, 200, 30);
			add(tfDataHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tfLocalEvento = new CampoDeTexto("", 380, 155, 200, 30);
		add(tfLocalEvento);

		tfQtdConvidados = new CampoDeTexto("", 380, 200, 200, 30);
		tfQtdConvidados.setDocument(new LimitaCaracteres(LimitaCaracteres.TipoEntrada.NUMERO));
		add(tfQtdConvidados);
	}

	public void addBotoes() {	
		btnExcluir = new Botao("Excluir", 155, 470, 120, 30);
		add(btnExcluir);

		btnAdicionar = new Botao("Adicionar", 520, 470, 120, 30);
		add(btnAdicionar);

		btnCadastrar = new Botao("Cadastrar", 250, 525, 120, 30);
		add(btnCadastrar);

		btnVoltar = new Botao("Voltar", 430, 525, 120, 30);
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

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ci.setFornecedoresTemp(new ArrayList<>());
				p.salvarCentral(ci);
				dispose();
				new TelaOrcamentos();
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeEvento = tfNomeEvento.getText();
				String localEvento = tfLocalEvento.getText();
				String qtdConvidados = tfQtdConvidados.getText();

				try {
					if(!nomeEvento.equals("") && !localEvento.equals("") && !qtdConvidados.equals("") && tfDataHora.getText().length()==16) {
						String tempoI1 = tfDataHora.getText();
						DateTimeFormatter dataIf = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
						LocalDateTime dataHora = LocalDateTime.parse(tempoI1, dataIf);
						if(ci.adicionarOrcamento(new Orcamento(nomeEvento, dataHora, localEvento, qtdConvidados, ci.getFornecedoresTemp(), new ArrayList<>()))) {
							ci.setFornecedoresTemp(new ArrayList<>());
							p.salvarCentral(ci);
							dispose();
							new TelaOrcamentos();
						}
						else {
							JOptionPane.showMessageDialog(null, "Essa data já ocorreu ou já existe um orçamento com esse nome!");
							tfNomeEvento.setText("");
							tfDataHora.setText("");
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
		painelScrow.setBounds(393, 262, 372, 205);
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
		painelScrow.setBounds(20, 262, 372, 205);
		add(painelScrow);
	}
}