package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ElementosGraficos.Botao;
import ElementosGraficos.Fontes;
import ElementosGraficos.Label;
import ElementosGraficos.NomeTela;
import ElementosGraficos.RadioButton;
import Controller.AlinhaCelulas;
import Controller.CentralDeInformacoes;
import Controller.OuvinteNovaTela;
import Controller.Persistencia;
import DAO.ClienteDAOImpl;

public class TelaOrcamentos extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Botao btnReuniao;
	private Botao btnEditarOcamento;
	private Botao btnRelatorio;
	private Botao btnEditarContrato;
	private RadioButton rbContratos;
	private RadioButton rbOrcamentos;
	private RadioButton rbTodos;
	private ButtonGroup grupo;
	private JTable tabela;
	private ArrayList<ClienteDAOImpl> clientesASeremExibidos;

	public TelaOrcamentos() {
		super("Orçamentos e Contratos");
		addBotoes();
		addRadioButtons();
		addLabels();
		addTabela();
		ouvinteBotoes();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("ORÇAMENTOS/CONTRATOS", 250, 30, 280, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void centralizarOrdenar(DefaultTableModel modelo) {
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(modelo);
		tabela.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = 4;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));

		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}

	private void addTabela() {
		ArrayList<ClienteDAOImpl> todosOsOrcamentos = ci.getTodosOsClientes();
		clientesASeremExibidos = todosOsOrcamentos;
		DefaultTableModel modelo = criarModelo(todosOsOrcamentos);
		tabela = new JTable(modelo);
		centralizarOrdenar(modelo);		
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 70, 745, 380);
		add(painelScrow);
	}

	private DefaultTableModel criarModelo(ArrayList<ClienteDAOImpl> clientes) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Cliente");
		modelo.addColumn("CPF/CNPJ");
		modelo.addColumn("Evento");
		modelo.addColumn("Tipo");
		modelo.addColumn("Data de Modificação");

		DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
		for(ClienteDAOImpl c : clientes) {
			if(c.getOrcamento() != null) {
				Object[] linha = new Object[5];

				linha[0] = c.getNome();
				linha[1] = c.getCPF_CNPJ();
				linha[2] = c.getOrcamento().getNomeEvento();
				linha[3] = c.getOrcamento().getTipo();
				linha[4] = c.getOrcamento().getDataModificacao().format(parser);

				modelo.addRow(linha);
			}
		}
		return modelo;
	}

	public void addBotoes() {
		btnReuniao = new Botao("Reunião", 100, 460, 120, 30);
		add(btnReuniao);

		Botao btnCadastrar = new Botao("Cadastrar", 580, 460, 120, 30);
		OuvinteNovaTela.proximaTela(btnCadastrar, this, NomeTela.TELA_CADASTRO_ORCAMENTO);
		add(btnCadastrar);

		btnEditarOcamento = new Botao("Editar Oçamento", 415, 460, 120, 30);
		btnEditarOcamento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(btnEditarOcamento);

		btnEditarContrato = new Botao("Editar Contrato", 415, 510, 120, 30);
		add(btnEditarContrato);

		btnRelatorio = new Botao("Relatório", 100, 510, 120, 30);
		add(btnRelatorio);

		Botao btnVoltar = new Botao("Voltar", 580, 510, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
		add(btnVoltar);
	}

	public void addRadioButtons() {
		rbContratos = new RadioButton("Contratos", 261, 460, 78, 20);
		add(rbContratos);

		rbOrcamentos = new RadioButton("Orçamentos", 261, 490, 93, 20);
		add(rbOrcamentos);

		rbTodos = new RadioButton("Todos", 261, 520, 60, 20);
		add(rbTodos);

		grupo = new ButtonGroup();
		grupo.add(rbContratos);
		grupo.add(rbOrcamentos);
		grupo.add(rbTodos);
		rbTodos.setSelected(true);
	}

	public void ouvinteBotoes() {
		btnReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					ClienteDAOImpl c = ci.buscaCliente(cpf_cnpj);
					if(c.getOrcamento().getTipo().equals("Contrato") || c.getOrcamento().getTipo().equals("Concluído")) {
						ci.setClienteTemp(c);
						p.salvarCentral(ci);
						dispose();
						new TelaReunioes();
					}
				}
			}
		});

		btnEditarOcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					ci.setClienteTemp(ci.buscaCliente(cpf_cnpj));
					p.salvarCentral(ci);
					dispose();
					new TelaEditarOrcamento();
				}
			}
		});

		btnEditarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					ClienteDAOImpl c = ci.buscaCliente(cpf_cnpj);
					if(c.getOrcamento().getTipo().equals("Contrato") || c.getOrcamento().getTipo().equals("Concluído")) {
						ci.setClienteTemp(c);
						p.salvarCentral(ci);
						dispose();
						new TelaEditarContrato();
					}
				}
			}
		});

		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					ClienteDAOImpl c = ci.buscaCliente(cpf_cnpj);
					if(c.getOrcamento().getTipo().equals("Contrato") || c.getOrcamento().getTipo().equals("Concluído")) {
						ci.setClienteTemp(c);
						p.salvarCentral(ci);
						dispose();
						new TelaGerarRelatorio();
					}
				}
			}
		});

		rbContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ClienteDAOImpl> todosOsClientes = ci.getTodosOsClientes();
				clientesASeremExibidos = new ArrayList<ClienteDAOImpl>();

				for(ClienteDAOImpl c: todosOsClientes) {
					if(c.getOrcamento() != null) {
						if (!c.getOrcamento().getTipo().equals("Contrato"))
							continue;
						clientesASeremExibidos.add(c);
					}
				}

				DefaultTableModel modelo = criarModelo(clientesASeremExibidos);
				tabela.setModel(modelo);
				centralizarOrdenar(modelo);
				tabela.repaint();
			}
		});

		rbOrcamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ClienteDAOImpl> todosOsClientes = ci.getTodosOsClientes();
				clientesASeremExibidos = new ArrayList<ClienteDAOImpl>();

				for(ClienteDAOImpl c: todosOsClientes) {
					if(c.getOrcamento() != null) {
						if (!c.getOrcamento().getTipo().equals("Orçamento"))
							continue;
						clientesASeremExibidos.add(c);
					}
				}

				DefaultTableModel modelo = criarModelo(clientesASeremExibidos);
				tabela.setModel(modelo);
				centralizarOrdenar(modelo);
				tabela.repaint();
			}
		});

		rbTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo = criarModelo(ci.getTodosOsClientes());
				tabela.setModel(modelo);
				centralizarOrdenar(modelo);
				tabela.repaint();
			}
		});
	}
}