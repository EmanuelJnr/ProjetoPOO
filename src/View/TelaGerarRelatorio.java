package View;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.DocumentException;
import ElementosGraficos.Botao;
import ElementosGraficos.CheckBox;
import ElementosGraficos.Fontes;
import ElementosGraficos.Label;
import ElementosGraficos.NomeTela;
import Model.Foto;
import Strategy.RelatorioCompleto;
import Strategy.RelatorioFotos;
import Strategy.RelatorioStrategy;
import Strategy.RelatorioTexto;
import Strategy.RelatorioVazio;
import Controller.AlinhaCelulas;
import Controller.CentralDeInformacoes;
import Controller.OuvinteNovaTela;
import Controller.Persistencia;
import DAO.ClienteDAOImpl;
import DAO.Fornecedor;
import DAO.Pacote;

public class TelaGerarRelatorio extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CheckBox cbNomeEvento;
	private CheckBox cbNomeCliente;
	private CheckBox cbEmailCliente;
	private CheckBox cbDataHorario;
	private CheckBox cbLocal;
	private CheckBox cbTamanho;
	private CheckBox cbFornecedores;
	private CheckBox cbValorEvento;
	private CheckBox cbTodos;
	private Botao btnAddFoto;
	private Botao btnDelFoto;
	private Botao btnConfirmar;
	private ClienteDAOImpl clienteTemp = ci.getClienteTemp();
	private ArrayList<Fornecedor> todosFornecedores = new ArrayList<>();
	private DefaultTableModel modelo;
	private JTable tabela;
	private Map<String, CheckBox> map = new HashMap<>();

	public TelaGerarRelatorio() {
		super("Gerar Relatório");
		addLabels();
		addCheckBoxs();
		addTabela();
		addBotoes();
		ouvinteBotoes();
		ouvinteCBTodos();
		setVisible(true);
	}

	public boolean adicionarFornecedor(Fornecedor fAdd) {
		for (Fornecedor f : todosFornecedores) {
			if(f.equals(fAdd))
				return false;
		}
		todosFornecedores.add(fAdd);
		return true;
	}

	public void preencheFornecedores() {
		for (Fornecedor f : clienteTemp.getOrcamento().getFornecedores())
			adicionarFornecedor(f);

		for (Pacote p : clienteTemp.getOrcamento().getPacotes()) {
			for (Fornecedor f : p.getFornecedores())
				adicionarFornecedor(f);
		}
	}

	public void gerarRelatorio() throws DocumentException, IOException {
		preencheFornecedores();
		RelatorioStrategy relatorioStrategy;
		boolean nenhumaMarcada = true;
		
		map.put("nomeEvento", cbNomeEvento);
		map.put("nomeCliente", cbNomeCliente);
		map.put("emailCliente", cbEmailCliente);
		map.put("dataHorario", cbDataHorario);
		map.put("local", cbLocal);
		map.put("tamanho", cbTamanho);
		map.put("valorEvento", cbValorEvento);
		map.put("fornecedores", cbFornecedores);
		map.put("todos", cbTodos);
		for (CheckBox cb : map.values()) {
			if(cb.isSelected()) {
				nenhumaMarcada = false;
			}
		}

		if(!nenhumaMarcada && tabela.getModel().getRowCount()!=0) {
			relatorioStrategy = new RelatorioCompleto();
		}
		else if(!nenhumaMarcada && tabela.getModel().getRowCount()==0) {
			relatorioStrategy = new RelatorioTexto();
		}
		else if(nenhumaMarcada && tabela.getModel().getRowCount()!=0) {
			relatorioStrategy = new RelatorioFotos();
		}
		else {
			relatorioStrategy = new RelatorioVazio();
		}
		relatorioStrategy.gerarRelatorio(clienteTemp, todosFornecedores, map);
	}

	public void addLabels() {
		Label lbTitulo = new Label("GERAR RELATÓRIO", 280, 10, 240, 25);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);
	}

	public void addCheckBoxs() {
		cbNomeEvento = new CheckBox("Nome do Evento", 190, 40, 118, 20);
		add(cbNomeEvento);

		cbNomeCliente = new CheckBox("Nome do Cliente", 190, 70, 118, 20);
		add(cbNomeCliente);

		cbEmailCliente = new CheckBox("E-mail do Cliente", 190, 100, 118, 20);
		add(cbEmailCliente);

		cbDataHorario = new CheckBox("Data e hora do Evento", 190, 130, 163, 20);
		add(cbDataHorario);

		cbLocal = new CheckBox("Local do Evento", 190, 160, 203, 20);
		add(cbLocal);

		cbTamanho = new CheckBox("Tamanho do Evento", 190, 190, 137, 20);
		add(cbTamanho);

		cbFornecedores = new CheckBox("Os fornecedores que participarão do evento", 190, 220, 267, 20);
		add(cbFornecedores);

		cbValorEvento = new CheckBox("Valor total do Evento", 190, 250, 170, 20);
		add(cbValorEvento);

		cbTodos = new CheckBox("Todos", 190, 280, 59, 20);
		add(cbTodos);
	}

	public void ouvinteCBTodos() {
		cbTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<CheckBox> todosCB = Arrays.asList(cbNomeEvento, cbNomeCliente, cbEmailCliente, cbDataHorario, cbLocal, cbTamanho,
						cbValorEvento, cbFornecedores);
				if(e.getSource() == cbTodos) {
					if(cbTodos.isSelected()) {
						for (CheckBox cb : todosCB)
							cb.setSelected(true);
					}
					else if(!cbTodos.isSelected()) {
						for(CheckBox cb : todosCB)
							cb.setSelected(false);
					}
				}
			}
		});
	}

	public void addTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome da Foto");

		for(Foto f : clienteTemp.getOrcamento().getFotos()) {
			Object[] linha = new Object[1];

			String[] nomeFoto = f.getCaminhoDaFoto().split("/");
			linha[0] = nomeFoto[nomeFoto.length-1];

			modelo.addRow(linha);
		}
		tabela = new JTable(modelo);
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabela.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 310, 745, 200);
		add(painelScrow);
	}

	public void addBotoes() {
		btnAddFoto = new Botao("Adicionar Foto", 20, 275, 150, 30);
		add(btnAddFoto);

		btnDelFoto = new Botao("Remover Foto", 615, 275, 150, 30);
		add(btnDelFoto);

		btnConfirmar = new Botao("Confirmar", 240, 520, 120, 30);
		add(btnConfirmar);

		Botao btnVoltar = new Botao("Voltar", 440, 520, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_ORCAMENTOS);
		add(btnVoltar);
	}

	public void ouvinteBotoes() {
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();

				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Todos os arquivos de imagem", "jpg", "png", "jpeg", "bmp", "gif");

				jFileChooser.setAcceptAllFileFilterUsed(false);
				jFileChooser.addChoosableFileFilter(filtro);

				int resposta = jFileChooser.showOpenDialog(null);

				if(resposta == JFileChooser.APPROVE_OPTION) {
					File arquivoSelecionado = jFileChooser.getSelectedFile();

					System.out.println(arquivoSelecionado.getAbsolutePath());

					if(clienteTemp.getOrcamento().adicionarFoto(new Foto(arquivoSelecionado.getAbsolutePath()))) {
						Object[] linha = new Object[1];
						String[] nomeFoto = arquivoSelecionado.getAbsolutePath().split("\\\\");
						linha[0] = nomeFoto[nomeFoto.length-1];
						modelo.addRow(linha);
					}
				}
			}
		});

		btnDelFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String nomeFoto = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
					modelo.removeRow(tabela.getSelectedRow());
					clienteTemp.getOrcamento().getFotos().remove(clienteTemp.getOrcamento().buscaFoto(nomeFoto));
				}
			}
		});

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gerarRelatorio();
				} catch (DocumentException | IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Relatório criado!");

				Desktop desktop = Desktop.getDesktop();
				File file = new File("Relatório "+clienteTemp.getOrcamento().getNomeEvento()+".pdf");
				try {
					desktop.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				dispose();
				new TelaOrcamentos();
			}
		});
	}
}