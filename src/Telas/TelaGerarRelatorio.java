package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Interface.Botao;
import Interface.CheckBox;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.CentralDeInformacoes;
import Logica.Persistencia;
import Ouvintes.OuvinteNovaTela;

public class TelaGerarRelatorio extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CheckBox cbNomeEvento;
	private CheckBox cbDataHorario;
	private CheckBox cbLocal;
	private CheckBox cbTamanho;
	private CheckBox cbFornecedores;
	private CheckBox cbTodos;
	private Botao btnAddFoto;
	private Botao btnDelFoto;
	private Botao btnConfirmar;

	public TelaGerarRelatorio() {
		super("Gerar Relatório");
		addLabels();
		addCheckBoxs();
		addTabela();
		addBotoes();
		ouvinteBotoes();
		setVisible(true);
	}
	public static void main(String[] args) {/////////////////////////////////////////////////////////////
		new TelaGerarRelatorio();
	}

	public void addLabels() {
		Label lbTitulo = new Label("GERAR RELATÓRIO", 280, 20, 240, 20);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);
	}

	public void addCheckBoxs() {
		cbNomeEvento = new CheckBox("Nome do evento", 190, 70, 118, 20);
		add(cbNomeEvento);

		cbDataHorario = new CheckBox("Data e horário do evento", 190, 100, 163, 20);
		add(cbDataHorario);

		cbLocal = new CheckBox("Local onde o evento acontecerá", 190, 130, 203, 20);
		add(cbLocal);

		cbTamanho = new CheckBox("Tamanho do evento", 190, 160, 137, 20);
		add(cbTamanho);

		cbFornecedores = new CheckBox("Os fornecedores que participarão do evento", 190, 190, 267, 20);
		add(cbFornecedores);

		cbTodos = new CheckBox("Todos", 190, 220, 59, 20);
		add(cbTodos);
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome da Foto");

		for(ImageIcon i : ci.getClienteTemp().getOrcamento().getFotos()) {
			Object[] linha = new Object[1];

			String[] nomeFoto = i.getDescription().split("/");
			linha[0] = nomeFoto[nomeFoto.length-1];

			modelo.addRow(linha);
		}

		JTable tabela = new JTable(modelo);
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
				//TODO busca e adiciona a foto do pc ao contrato selecionado e na tabela.
				dispose();
				new TelaGerarRelatorio();
			}
		});

		btnDelFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO pega o objeto selecionado na tabela e remove do contrato e da tabela.
				dispose();
				new TelaGerarRelatorio();
			}
		});

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbTodos.isSelected()) {
					cbNomeEvento.setSelected(true);
					cbDataHorario.setSelected(true);
					cbLocal.setSelected(true);
					cbTamanho.setSelected(true);
					cbFornecedores.setSelected(true);
				}
				if(cbNomeEvento.isSelected()) {
					System.out.println("adiciona o nome do evento no PDF");
				}
				if(cbDataHorario.isSelected()) {
					System.out.println("adiciona a data e horário do evento no PDF");
				}
				if(cbLocal.isSelected()) {
					System.out.println("adiciona o local do evento no PDF");
				}
				if(cbTamanho.isSelected()) {
					System.out.println("adiciona o tamanho do evento no PDF");
				}
				if(cbFornecedores.isSelected()) {
					System.out.println("adiciona os fornecedores que participarão do evento no PDF");
				}
				//TODO caso o orçamento/contrato não tenha alguma informação, ela aparece no relatório com o texto “não consta
				JOptionPane.showMessageDialog(null, "Relatório criado!");
				dispose();
				new TelaOrcamentos();
			}
		});
	}
}