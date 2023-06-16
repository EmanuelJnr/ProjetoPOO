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
import Logica.CentralDeInformacoes;
import Logica.Orcamento;
import Logica.Persistencia;
import Ouvintes.OuvinteNovaTela;

public class TelaCadastroOrcamento extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CampoDeTexto tfNomeEvento;
	private CampoDeTexto tfDataHora;
	private CampoDeTexto tfLocalEvento;
	private CampoDeTexto tfQtdConvidados;
	private Botao btnCadastrar;

	public TelaCadastroOrcamento () {
		super("Cadastro Orçamento");
		addCamposDeTexto();
		addLabels();
		addTabela();
		addBotoes();
		ouvinteCadastrar();
		setVisible(true);
	}
	public static void main(String[] args) {/////////////////////////////////////////////////////
		new TelaCadastroOrcamento();
	}

	public void addLabels() {
		Label lbTitulo = new Label("Cadastrar Orçamento", 280, 20, 240, 20);
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
		modelo.addColumn("Data");
		modelo.addColumn("Local ");
		modelo.addColumn("quant convidados");
		modelo.addColumn("Fornecedores");
		modelo.addColumn("Fotos");

		for(Orcamento o : ci.getTodosOsOrcamentos()) {
			Object[] linha = new Object[6];

			linha[0] = o.getNomeEvento();
			linha[1] = String.valueOf(o.getData());
			linha[2] = o.getLocalEvento();
			linha[3]= o.getQtdConvidados();
			linha[4]= o.getFornecedores();
			linha[5]= o.getFotos();

			modelo.addRow(linha);
		}
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
		btnCadastrar = new Botao("Cadastrar", 240, 520, 120, 30);
		add(btnCadastrar);

		Botao btnAddFornecedor = new Botao("Adicionar Fornecedor", 20, 275, 150, 30);
		OuvinteNovaTela.proximaTela(btnAddFornecedor, this, NomeTela.TELA_ADD_FORNECEDOR_CAD_ORCAMENTO);
		add(btnAddFornecedor);

		Botao btnCancelar = new Botao("Cancelar", 440, 520, 120, 30);
		OuvinteNovaTela.proximaTela(btnCancelar, this, NomeTela.TELA_ORCAMENTOS);
		add(btnCancelar);
	}

	public void ouvinteCadastrar() {
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Cadastre um orçamento no banco.
				dispose();
				new TelaOrcamentos();
			}
		});
	}
}