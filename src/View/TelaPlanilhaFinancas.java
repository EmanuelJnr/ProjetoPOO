package View;

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
import ElementosGraficos.Botao;
import ElementosGraficos.Fontes;
import ElementosGraficos.Label;
import ElementosGraficos.NomeTela;
import Controller.AlinhaCelulas;
import Controller.CentralDeInformacoes;
import Controller.GerarPlanilhaExcel;
import Controller.OuvinteNovaTela;
import Controller.Persistencia;
import DAO.ClienteDAOImpl;

public class TelaPlanilhaFinancas extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Botao btnVoltar;
	private Botao btnPlanilha;
	private JTable tabela;
	private ArrayList<ClienteDAOImpl> clientesASeremExibidos;

	public TelaPlanilhaFinancas() {
		super("Gerar Planilha");
		addBotoes();
		addLabels();
		addTabela();
		ouvintesBotoes();
		setVisible(true);
	}

	public void addBotoes() {		
		btnVoltar = new Botao("Voltar",520,500,120,30);
		add(btnVoltar);

		btnPlanilha = new Botao("Gerar Planilha",300,500,120,30);
		add(btnPlanilha);
	}

	public void ouvintesBotoes() {
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);

		btnPlanilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					ci.setClienteTemp(ci.buscaCliente(cpf_cnpj));
					p.salvarCentral(ci);
					GerarPlanilhaExcel gpe = new GerarPlanilhaExcel();
					gpe.gerarPlanilhaExcel();
				}
			}
		});
	}

	public void addLabels() {
		Label titulo = new Label("Orçamentos",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
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
}