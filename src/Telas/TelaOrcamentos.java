package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interface.Botao;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.CentralDeInformacoes;
import Logica.Orcamento;
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

	public TelaOrcamentos() {
		super("Orçamentos");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBotoes();
		setVisible(true);
	}
	public static void main(String[] args) {///////////////////////////////////////////////////
		new TelaOrcamentos();
	}

	public void addLabels() {
		Label titulo = new Label("ORÇAMENTOS", 329, 30, 142, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Data");
		modelo.addColumn("Local ");
		modelo.addColumn("quant convidados");
		modelo.addColumn("Fornecedores");
		modelo.addColumn("Fotos");

		for(Orcamento m: ci.getTodosOsOrcamentos()) {
			Object[] linha = new Object[6];

			linha[0] = m.getNomeEvento();
			linha[1] = String.valueOf(m.getData());
			linha[2] = m.getLocalEvento();
			linha[3] = m.getQtdConvidados();
			linha[4] = m.getFornecedores();
			linha[5] = m.getFotos();

			modelo.addRow(linha);
		}
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 70, 745, 380);
		add(painelScrow);
	}

	public void addBotoes() {
		btnReuniao = new Botao("Reunião", 160, 460, 120, 30);
		add(btnReuniao);

		Botao btnCadastrar = new Botao("Cadastrar", 520, 460, 120, 30);
		OuvinteNovaTela.proximaTela(btnCadastrar, this, NomeTela.TELA_CADASTRO_ORCAMENTO);
		add(btnCadastrar);

		btnEditar = new Botao("Editar", 100, 510, 120, 30);
		add(btnEditar);

		btnFiltrar = new Botao("Filtrar", 265, 510, 120, 30);
		OuvinteNovaTela.proximaTela(btnFiltrar, this, NomeTela.TELA_FILTRAR_ORCAMENTOS);
		add(btnFiltrar);

		btnRelatorio = new Botao("Relatório", 415, 510, 120, 30);
		add(btnRelatorio);

		Botao btnVoltar = new Botao("Voltar", 580, 510, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
		add(btnVoltar);
	}

	public void ouvinteBotoes() {
		btnReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO pega o objeto selecionado na tabela
				dispose();
				new TelaReunioes();
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO pega o objeto selecionado na tabela
				dispose();
				new TelaEditarOrcamento();
			}
		});

		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO pega o objeto selecionado na tabela
				dispose();
				new TelaGerarRelatorio();
			}
		});
	}
}