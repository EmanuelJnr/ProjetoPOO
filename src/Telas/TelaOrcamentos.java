package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Interface.Botao;
import Interface.CheckBox;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Cliente;
import Logica.Persistencia;
import Ouvintes.OuvinteNovaTela;

public class TelaOrcamentos extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Botao btnReuniao;
	private Botao btnEditar;
	private Botao btnFiltrar;
	private Botao btnRelatorio;
	private CheckBox cbContrato;
	private CheckBox cbOrcamento;
	private JTable tabela;
	private ArrayList<Cliente> clientesASeremExibidos;

	public TelaOrcamentos() {
		super("Orçamentos e Contratos");
		addBotoes();
		addCheckBox();
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
		ArrayList<Cliente> todosOsOrcamentos = ci.getTodosOsClientes();
		clientesASeremExibidos = todosOsOrcamentos;
		DefaultTableModel modelo = criarModelo(todosOsOrcamentos);
		tabela = new JTable(modelo);
		centralizarOrdenar(modelo);		
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 70, 745, 380);
		add(painelScrow);
	}

	private DefaultTableModel criarModelo(ArrayList<Cliente> clientes) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Cliente");
		modelo.addColumn("CPF/CNPJ");
		modelo.addColumn("Evento");
		modelo.addColumn("Tipo");
		modelo.addColumn("Data de Modificação");

		DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
		for(Cliente c : clientes) {
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

		btnEditar = new Botao("Editar", 100, 510, 120, 30);
		add(btnEditar);

		btnFiltrar = new Botao("Filtrar", 265, 510, 120, 30);
		add(btnFiltrar);

		btnRelatorio = new Botao("Relatório", 415, 510, 120, 30);
		add(btnRelatorio);

		Botao btnVoltar = new Botao("Voltar", 580, 510, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
		add(btnVoltar);
	}

	public void addCheckBox() {
		cbContrato = new CheckBox("Contrato", 261, 460, 73, 20);
		cbOrcamento = new CheckBox("Orçamento", 261, 480, 86, 20);
		add(cbContrato);
		add(cbOrcamento);
	}

	public void ouvinteBotoes() {
		btnReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					Cliente c = ci.buscaCliente(cpf_cnpj);
					if(c.getOrcamento().getTipo().equals("Contrato")) {
						ci.setClienteTemp(c);
						p.salvarCentral(ci);
						dispose();
						new TelaReunioes();
					}
				}
			}
		});

		btnEditar.addActionListener(new ActionListener() {
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

		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					Cliente c = ci.buscaCliente(cpf_cnpj);
					if(c.getOrcamento().getTipo().equals("Contrato")) {
						ci.setClienteTemp(c);
						p.salvarCentral(ci);
						dispose();
						new TelaGerarRelatorio();
					}
				}
			}
		});

		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Cliente> todosOsClientes = ci.getTodosOsClientes();
				clientesASeremExibidos = new ArrayList<Cliente>();

				for(Cliente c: todosOsClientes) {
					if(!c.getOrcamento().getTipo().equals("Orçamento") && cbOrcamento.isSelected())
						continue;
					if(!c.getOrcamento().getTipo().equals("Contrato") && cbContrato.isSelected())
						continue;
					clientesASeremExibidos.add(c);
				}

				DefaultTableModel modelo = criarModelo(clientesASeremExibidos);
				tabela.setModel(modelo);
				centralizarOrdenar(modelo);
				tabela.repaint();
			}
		});
	}
}