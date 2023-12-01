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
import ElementosGraficos.Botao;
import ElementosGraficos.Fontes;
import ElementosGraficos.Label;
import ElementosGraficos.NomeTela;
import Controller.AlinhaCelulas;
import Controller.CentralDeInformacoes;
import Controller.OuvinteNovaTela;
import Controller.Persistencia;
import DAO.Servico;

public class TelaServicos extends TelaPadrao{
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private static final long serialVersionUID = 1L;
	private Botao btnEditar;
	private Botao btnExcluir;
	private Botao btnCadastrar;
	private JTable tabela;
	private DefaultTableModel modelo;

	public TelaServicos() {
		super("Serviços");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBotoes();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("SERVIÇOS", 347, 30, 106, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome do Serviço");

		for(Servico s: ci.getTodosOsServicos()) {
			Object[] linha = new Object[3];
			linha[0] = s.getServico();
			modelo.addRow(linha);
		}

		tabela = new JTable(modelo);
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabela.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 100, 745, 350);
		add(painelScrow);
	}

	public void addBotoes() {
		btnCadastrar = new Botao("Cadastrar", 100, 490, 120, 30);
		add(btnCadastrar);

		btnEditar = new Botao("Editar", 265, 490, 120, 30);
		add(btnEditar);

		btnExcluir = new Botao("Excluir", 415, 490, 120, 30);
		add(btnExcluir);

		Botao btnVoltar = new Botao("Voltar", 580, 490, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
		add(btnVoltar);
	}

	public void ouvinteBotoes() {
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resultadoJOption = JOptionPane.showInputDialog(null, "Digite um serviço:");
				if(resultadoJOption != null) {
					if(!resultadoJOption.equals("")) {
						for (Servico s: ci.getTodosOsServicos()) {
							if(s.getServico().equals(resultadoJOption)) {
								JOptionPane.showMessageDialog(null, "Já existe um serviço com esse nome!");
								return;
							}
						}
						Object[] row = new Object[1];
						row[0] = resultadoJOption;
						modelo.addRow(row);
						ci.adicionarServico(new Servico(resultadoJOption));
						p.salvarCentral(ci);
					}
				}
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tabela.getSelectedRow();
				if(tabela.getSelectedRow() != -1) {
					String resultadoJOption = JOptionPane.showInputDialog(null, "Edite o serviço:",ci.getTodosOsServicos().get(selectedRow));
					if(resultadoJOption != null) {
						if(!resultadoJOption.equals("")) {
							ci.getTodosOsServicos().remove(selectedRow);
							p.salvarCentral(ci);
							Servico serv = new Servico(resultadoJOption);
							ci.adicionarServico(serv);
							p.salvarCentral(ci);
							dispose();
							new TelaServicos();
						}
					}
				}
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String servico = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
					modelo.removeRow(tabela.getSelectedRow());
					ci.getTodosOsServicos().remove(ci.buscaServico(servico));
					p.salvarCentral(ci);
				}
			}
		});
	}
}