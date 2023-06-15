package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Interface.RadioButton;
import Ouvintes.OuvinteNovaTela;

public class TelaCadastroFornecedor extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private CampoDeTexto tfCpfCnpj;
	private CampoDeTexto tfTelefone;
	private RadioButton rbCPF;
	private RadioButton rbCNPJ;
	private Botao btnExcluir;
	private Botao btnCadastrar;

	public TelaCadastroFornecedor() {
		super("Cadastrar Fornecedor");
		addLabels();
		addCamposDeTexto();
		addRadioButton();
		addBotoes();
		OuvinteBotoes();
		addTabela();
		setVisible(true);
	}
	public static void main(String[] args) {
		new TelaCadastroFornecedor();
	}

	public void addLabels() {
		Label titulo = new Label("CADASTRAR FORNECEDOR", 265, 15, 270, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Nome:", 125, 90, 50, 30));
		add(new Label("E-mail:",380, 90, 50, 30));
		add(new Label("Telefone:", 380, 140, 53, 30));
		add(new Label("Serviços", 375, 200, 50, 30));
	}

	public void addCamposDeTexto() {
		tfNome = new CampoDeTexto("", 173, 90, 180, 30);
		add(tfNome);

		tfEmail = new CampoDeTexto("", 440, 90, 240, 30);
		add(tfEmail);

		tfCpfCnpj = new CampoDeTexto("", 178, 142, 175, 30);
		add(tfCpfCnpj);

		tfTelefone = new CampoDeTexto("", 440, 142, 240, 30);
		add(tfTelefone);
	}

	public void addRadioButton() {
		rbCPF = new RadioButton("CPF", 120, 140, 50, 15);
		add(rbCPF);

		rbCNPJ = new RadioButton("CNPJ", 120, 160, 56, 15);
		add(rbCNPJ);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbCPF);
		grupo.add(rbCNPJ);
	}

	public void addBotoes() {
		btnExcluir = new Botao("Excluir", 200, 445, 120, 30);
		add(btnExcluir);

		Botao btnAdicionar = new Botao("Adicionar", 480, 445, 120, 30);
		OuvinteNovaTela.proximaTela(btnAdicionar, this, NomeTela.TELA_ADD_SERVICO_FORNECEDOR);
		add(btnAdicionar);

		btnCadastrar = new Botao("Cadastrar", 250, 515, 120, 30);
		add(btnCadastrar);

		Botao btnVoltar = new Botao("Voltar", 430, 515, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_FORNECEDORES);
		add(btnVoltar);
	}

	public void OuvinteBotoes() {
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO pega um objeto da tabela serviços e exclui.
				dispose();
				new TelaCadastroFornecedor();
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Fazer a confirmação e verificação dos campos de texto.
				dispose();
				new TelaFornecedores();
			}
		});
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Preço");
		modelo.addColumn("Fornecedor");

		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.

		/* TODO Adicionar na lista os devidos atributos. 
		for() {
			Object[] linha = new Object[3];

			linha[0] = //Nome
			linha[1] = //Preço
			linha[2] = //Fornecedor

			modelo.addRow(linha);
		}
		 */	
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 230, 745, 205);
		add(painelScrow);
	}
}