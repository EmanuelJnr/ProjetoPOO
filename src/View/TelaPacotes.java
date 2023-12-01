package View;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import DAO.Fornecedor;
import DAO.Pacote;
import DAO.Servico;

public class TelaPacotes extends TelaPadrao{
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private static final long serialVersionUID = 1L;
	private Botao btnDetalhar;
	private Botao btnVoltar;
	private JTable tabela;

	public TelaPacotes() {
		super("Pacotes");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBotoes();
		ouvinteJanela();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("Pacotes", 350, 30, 78, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Valor");
		modelo.addColumn("Serviços");
		modelo.addColumn("Disponíveis");
		modelo.addColumn("Indisponíveis");

		for (Pacote p : ci.getTodosOsPacotes()) {
			String disponiveis = "";
			String indisponiveis = "";
			String servicos = "";
			for (Fornecedor f : p.getFornecedores()) {
				if (f.getDiponivel().equals("Sim"))
					disponiveis += f.getNome()+" ";
				else
					indisponiveis += f.getNome()+" ";
				for (Servico s : f.getServicos()) {
					servicos += s.getServico()+" ";
				}
			}
			Object[] linha = new Object[5];

			linha[0] = p.getNomePacote();
			linha[1] = p.getValor();
			linha[2] = servicos;
			linha[3] = disponiveis;
			linha[4] = indisponiveis;

			modelo.addRow(linha);
		}

		tabela = new JTable(modelo){
			private static final long serialVersionUID = 1L;
			public String getToolTipText(MouseEvent e) {
				Point p = e.getPoint();
				String nomePacote = getValueAt(rowAtPoint(p), 0).toString();
				return ci.buscaPacote(nomePacote).getCaracteristicas();
			}
		};

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
		Botao btnCadastrar = new Botao("Cadastrar", 160, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnCadastrar, this, NomeTela.TELA_CADASTRO_PACOTE);
		add(btnCadastrar);

		btnDetalhar = new Botao("Detalhar/Deletar", 335, 500, 130, 30);
		add(btnDetalhar);

		btnVoltar = new Botao("Voltar", 520, 500, 120, 30);
		add(btnVoltar);
	}

	public void ouvinteBotoes() {
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					ci.setPacoteRef(ci.getTodosOsPacotes().get(tabela.getSelectedRow()));
					p.salvarCentral(ci);
					dispose();
					new TelaDetalhamentoPacote();
				}
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ci.setPacoteRef(new Pacote());
				p.salvarCentral(ci);
				dispose();
				new TelaPrincipal();
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
				ci.setPacoteRef(new Pacote());
				p.salvarCentral(ci);
			}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		});
	}
}