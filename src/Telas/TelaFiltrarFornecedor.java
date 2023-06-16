package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.CentralDeInformacoes;
import Logica.Fornecedor;
import Logica.Persistencia;
import Ouvintes.OuvinteNovaTela;

public class TelaFiltrarFornecedor extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Botao btnVoltar;
	private Botao btnFiltrar;
	private JRadioButton rbFisica = new JRadioButton("Pessoa Física");
	private JRadioButton rbJuridica = new JRadioButton("Pessoa Jurídica");
	private JRadioButton rbCom = new JRadioButton("Com");
	private JRadioButton rbSem = new JRadioButton("Sem");

	public TelaFiltrarFornecedor() {
		super("Filtrar Fornecedores");
		addLabels();
		addTabela();
		addBotao();
		addCampoDeTexto();
		addRadioButton();
		ouvinteBtnFiltrar();
		setVisible(true);
	}
	public static void main(String[] args) {///////////////////////////////////////////////////////
		new TelaFiltrarFornecedor();
	}

	public void addCampoDeTexto() {
		CampoDeTexto tfNome = new CampoDeTexto("", 60,500,140,30);
		add(tfNome);
	}

	public void addRadioButton() {		
		rbFisica.setBounds(220,490,120,30);
		rbJuridica.setBounds(220,510,120,30);
		add(rbFisica);
		add(rbJuridica);

		rbCom.setBounds(385,490,60,30);
		rbSem.setBounds(385,510,60,30);
		add(rbCom);
		add(rbSem);

		ButtonGroup grupoDoc = new ButtonGroup();
		grupoDoc.add(rbFisica);
		grupoDoc.add(rbJuridica);		

		ButtonGroup grupoSv = new ButtonGroup();
		grupoSv.add(rbCom);
		grupoSv.add(rbSem);
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

	public void ouvinteBtnFiltrar() {
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Permanece na mesma janela mas filtra os dados de acordo com as entradas.
			}
		});
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Email");
		modelo.addColumn("CPF/CNPJ");
		modelo.addColumn("Telefone");
		modelo.addColumn("Serviços");

		for(Fornecedor f: ci.getTodosOsFornecedores()) {
			Object[] linha = new Object[5];

			linha[0] = f.getNome();
			linha[1]= f.getEmail();
			linha[2]= f.getCPF_CNPJ();
			linha[3]= f.getTelefone();
			linha[4]= f.getServicos();

			modelo.addRow(linha);
		}	
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,100,745,350);
		add(painelScrow);
	}
}
