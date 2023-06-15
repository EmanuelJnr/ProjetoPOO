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

public class TelaCadastroPacote extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private CampoDeTexto tfNomePacote;
	private CampoDeTexto tfValor;
	private Botao btnCadastrar;

	public TelaCadastroPacote() {
		super("Cadastrar Pacote");
		addLabels();
		addCamposDeTexto();
		addBotoes();
		addTabela();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("Cadastrar Pacote", 300, 20, 200, 25);
		titulo.setFont(Fontes.titulo());
		add(titulo);
		
		add(new Label("Nome do pacote: ", 70, 100, 160, 25));
		add(new Label("Valor: ", 70, 150, 120, 25));
		add(new Label("Fornecedores", 70, 250, 150, 25));
	}
	
	public void addCamposDeTexto() {
		tfNomePacote = new CampoDeTexto("", 220, 100, 350, 25);
		add(tfNomePacote);
		
		tfValor = new CampoDeTexto("", 220, 150, 350, 25);
		add(tfValor);
	}
	
	public void addBotoes() {
		Botao btnAdicionar = new Botao("Adicionar Fornecedor", 500, 250, 150, 25);
		OuvinteNovaTela.proximaTela(btnAdicionar, this, NomeTela.TELA_ADD_FORNECEDOR_PACOTE);
		add(btnAdicionar);
		
		btnCadastrar = new Botao("Cadastrar", 240, 520, 120, 30);
		add(btnCadastrar);
		
		Botao btnVoltar = new Botao("Voltar", 440, 520, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PACOTES);
		add(btnVoltar);
	}
	public void ouvinteCadastrar() {
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO cadastra um pacote no banco
				dispose();
				new TelaPacotes();
			}
		});
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
		painelScrow.setBounds(20,290,745,200);
		add(painelScrow);
	}
}