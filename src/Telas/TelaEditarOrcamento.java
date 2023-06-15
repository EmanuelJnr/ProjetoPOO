package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Ouvintes.OuvinteNovaTela;

public class TelaEditarOrcamento extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private CampoDeTexto tfNomeEvento;
	private CampoDeTexto tfDataHora;
	private CampoDeTexto tfLocalEvento;
	private CampoDeTexto tfQtdConvidados;
	private Botao btnConfirmar;

	public TelaEditarOrcamento () {
		super("Edição de Orçamento");
		addCamposDeTexto();
		addLabels();
		addTabela();
		addBotoes();
		ouvinteBtnConfirmar();
		setVisible(true);
	}

	public void addLabels() {
		Label lbTitulo = new Label("Editar Orçamento", 280, 20, 240, 20);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);

		add(new Label("Nome do evento:", 190, 70, 100, 20));

		add(new Label("Data e hora:", 190, 120, 100, 20));

		add(new Label("Local do evento:", 190, 170, 100, 20));

		add(new Label("Quantidade de convidados:", 190, 220, 150, 20));

		Label lbTituloTB = new Label("Fornecedores", 331, 275, 138, 20);
		lbTituloTB.setFont(Fontes.titulo());
		add(lbTituloTB);
	}
	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Custo");
		modelo.addColumn("Responsável pagamento");
		modelo.addColumn("Contrato");

		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.

		/** TODO Adicionar na lista os devidos atributos. 
		for() {
			Object[] linha = new Object[3];

			linha[0] = //Nome
			linha[1] = //Custo
			linha[2] = //Responsável pagamento
			linha[3] = //Contrato

			modelo.addRow(linha);
		}
		 */	
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,310,745,200);
		add(painelScrow);
	}
	public void addCamposDeTexto() {
		tfNomeEvento = new CampoDeTexto("", 380, 65, 200, 30);
		add(tfNomeEvento);

		tfDataHora = new CampoDeTexto("", 380, 115, 200, 30);
		add(tfDataHora);

		tfLocalEvento = new CampoDeTexto("", 380, 165, 200, 30);
		add(tfLocalEvento);

		tfQtdConvidados = new CampoDeTexto("", 380, 215, 200, 30);
		add(tfQtdConvidados);
	}

	public void addBotoes() {
		btnConfirmar = new Botao("Confirmar", 240, 520, 120, 30);
		add(btnConfirmar);

		Botao btnAddFornecedor = new Botao("Adicionar Fornecedor", 20, 275, 150, 30);
		OuvinteNovaTela.proximaTela(btnAddFornecedor, this, NomeTela.TELA_ADD_FORNECEDOR_EDT_ORCAMENTO);
		add(btnAddFornecedor);

		Botao btnCancelar = new Botao("Cancelar", 440, 520, 120, 30);
		OuvinteNovaTela.proximaTela(btnCancelar, this, NomeTela.TELA_ORCAMENTOS);
		add(btnCancelar);
	}

	public void ouvinteBtnConfirmar() {
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO compara os dados do orçamento selecionado na tabela,
				//TODO se algum mudou ele registra.
				dispose();
				new TelaOrcamentos();
			}
		});
	}
}