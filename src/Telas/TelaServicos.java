package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaServicos extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnEditar;
	private Botao btnExcluir;

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
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome do Serviço");
		modelo.addColumn("Preço");
		modelo.addColumn("Fornecedor");

		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.

		/** TODO Adicionar na lista os devidos atributos.
		for() {
			Object[] linha = new Object[3];

			linha[0] = //Nome do Serviço
			linha[1] = //Preço
			linha[2] = //Fornecedor

			modelo.addRow(linha);
		}
		 */
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 100, 745, 350);
		add(painelScrow);
	}

	public void addBotoes() {
		Botao btnCadastrar = new Botao("Cadastrar", 100, 490, 120, 30);
		OuvinteNovaTela.proximaTela(btnCadastrar, this, "TelaCadastrarServico");
		add(btnCadastrar);

		btnEditar = new Botao("Editar", 265, 490, 120, 30);
		add(btnEditar);

		btnExcluir = new Botao("Excluir", 415, 490, 120, 30);
		add(btnExcluir);

		Botao btnVoltar = new Botao("Voltar", 580, 490, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaPrincipal");
		add(btnVoltar);
	}
	public void ouvinteBotoes() {
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pega um objeto da tabela
				dispose();
				//new TelaEditarServico();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pega um objeto da tabela e exclui
				dispose();
				new TelaServicos();
			}
		});
	}
}