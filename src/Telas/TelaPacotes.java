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

public class TelaPacotes extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnDetalhar;

	public TelaPacotes() {
		super("Pacotes");
		addBotoes();
		addLabels();
		addTabela();
		ouvinteBtnDetalhar();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("Pacotes", 350, 30, 78, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome do pacote");
		modelo.addColumn("Valor");
		modelo.addColumn("Serviços");
		modelo.addColumn("Disponibilidade");

		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.

		/** TODO Adicionar na lista os devidos atributos. 
		for() {
			Object[] linha = new Object[4];

			linha[0] = //Nome
			linha[1] = //Física/Jurídica
			linha[2] = //Quantidade

			modelo.addRow(linha);
		}
		 */	
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 100, 745, 350);
		add(painelScrow);
	}

	public void addBotoes() {
		Botao btnCadastrar = new Botao("Cadastrar", 160, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnCadastrar, this, "TelaCadastroPacote");
		add(btnCadastrar);

		btnDetalhar = new Botao("Detalhar/Deletar", 335, 500, 130, 30);
		add(btnDetalhar);

		Botao btnVoltar = new Botao("Voltar", 520, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaPrincipal");
		add(btnVoltar);
	}

	public void ouvinteBtnDetalhar() {
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pega um objeto da tabela
				dispose();
				//new TelaDetalhamentoPacote();
			}
		});
	}
}