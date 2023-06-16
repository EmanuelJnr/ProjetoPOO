package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.CheckBox;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Fornecedor;
import Logica.Persistencia;
import Logica.Servico;
import Ouvintes.OuvinteNovaTela;

public class TelaFiltrarFornecedor extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CampoDeTexto tfNome;
	private Botao btnVoltar;
	private CheckBox cbFisica;
	private CheckBox cbJuridica;
	private CheckBox cbCom;
	private CheckBox cbSem;
	private Botao btnFiltrar;
	private JTable tabela;
	private ArrayList<Fornecedor> fornecedoresASeremExibidos;

	public TelaFiltrarFornecedor() {
		super("Filtrar Fornecedores");
		addTabela();
		addCampoDeTexto();
		addCheckBox();
		addLabels();
		addBotao();
		ouvinteFiltrar();
		setVisible(true);
	}

	public void addCampoDeTexto() {
		tfNome = new CampoDeTexto("", 60, 500, 140, 30);
		add(tfNome);
	}

	public void addCheckBox() {
		cbFisica = new CheckBox("Pessoa Física", 220, 492, 120, 25);
		add(cbFisica);

		cbJuridica = new CheckBox("Pessoa Jurídica", 220, 513, 120, 25);
		add(cbJuridica);

		cbCom = new CheckBox("Com", 385, 492, 60, 25);
		add(cbCom);

		cbSem = new CheckBox("Sem", 385, 513, 60, 25);
		add(cbSem);
	}

	public void addLabels() {
		Label titulo = new Label("Fornecedores",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Filtros:",20,465,120,30));
		add(new Label("Nome",20,500,120,30));
		add(new Label("Conjunto de serviços",360,465,120,30));
	}

	public void addBotao() {
		btnVoltar = new Botao("Voltar",640,500,120,30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_FORNECEDORES);
		add(btnVoltar);

		btnFiltrar = new Botao("Filtrar",500,500,120,30);
		add(btnFiltrar);
	}

	public void centralizarOrdenar(DefaultTableModel modelo) {
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabela.setRowSorter(sorter);
	}
	private void addTabela() {
		ArrayList<Fornecedor> todosOsFornecedores = ci.getTodosOsFornecedores();
		fornecedoresASeremExibidos = todosOsFornecedores;
		DefaultTableModel modelo = criarModelo(todosOsFornecedores);
		tabela = new JTable(modelo);
		centralizarOrdenar(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,100,745,350);
		add(painelScrow);
	}

	private DefaultTableModel criarModelo(ArrayList<Fornecedor> fornecedores) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Email");
		modelo.addColumn("CPF/CNPJ");
		modelo.addColumn("Telefone");
		modelo.addColumn("Serviços");

		for(Fornecedor f: fornecedores) {
			String servicos = "";
			for (Servico s : f.getServicos()) {
				servicos += s.getServico()+" ";
			}
			Object[] linha = new Object[5];
			linha[0] = f.getNome();
			linha[1]= f.getEmail();
			linha[2]= f.getCPF_CNPJ();
			linha[3]= f.getTelefone();
			linha[4]= servicos;
			modelo.addRow(linha);
		}
		return modelo;
	}

	public void ouvinteFiltrar() {
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Fornecedor> todosOsFornecedores = ci.getTodosOsFornecedores();
				fornecedoresASeremExibidos = new ArrayList<Fornecedor>();

				for(Fornecedor f: todosOsFornecedores) {
					if (!f.getNome().equals(tfNome.getText()) && !tfNome.getText().equals(""))
						continue;

					if(f.getCPF_CNPJ().length()!=14 && cbFisica.isSelected())
						continue;

					if(f.getCPF_CNPJ().length()!=18 && cbJuridica.isSelected())
						continue;

					if(!f.getServicos().isEmpty() && cbSem.isSelected())
						continue;

					if(f.getServicos().isEmpty() && cbCom.isSelected())
						continue;

					fornecedoresASeremExibidos.add(f);
				}
				DefaultTableModel modelo = criarModelo(fornecedoresASeremExibidos);

				tabela.setModel(modelo);
				centralizarOrdenar(modelo);
				tabela.repaint();
			}
		});
	}
}
