package Telas;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Label;

public class TelaCadastroFornecedor extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private CampoDeTexto tfCpfCnpj;
	private JRadioButton rbFisica = new JRadioButton("Pessoa Física");
	private JRadioButton rbJuridica = new JRadioButton("Pessoa Jurídica");
	private Botao btnConfirmar;
	private Botao btnVoltar;
	private Botao btnAdicionar;
	
	private String[] opcoes = {"Teste", "Cachorro"};
	private JComboBox<String> boxEscolha = new JComboBox<>(opcoes); 
	
	public TelaCadastroFornecedor() {
		super("Cadastrar Fornecedor");
		addLabels();
		addCamposDeTexto();
		addRadioButton();
		addBox();
		addBotoes();
		setVisible(true);
	}
	
	public void addBox() {
		boxEscolha.setBounds(435,220,100,30);
		add(boxEscolha);
	}
	
	public void addBotoes() {
		btnConfirmar = new Botao("Confirmar",270,500,120,30);
		btnVoltar = new Botao("Voltar",410,500,120,30);
		btnAdicionar = new Botao("Adicionar",553,220,120,30);
		
		add(btnAdicionar);
		add(btnConfirmar);
		add(btnVoltar);
	}
	
	public void addRadioButton() {
		rbFisica.setBounds(120,210,120,30);
		add(rbFisica);
		
		rbJuridica.setBounds(120,230,120,30);
		add(rbJuridica);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbFisica);
		grupo.add(rbJuridica);
	}
	
	public void addCamposDeTexto() {
		tfNome = new CampoDeTexto("", 175, 180, 180,30);
		add(tfNome);
		
		tfEmail = new CampoDeTexto("",435,180,240,30);
		add(tfEmail);
		
		tfCpfCnpj = new CampoDeTexto("",250,220,105,30);
		add(tfCpfCnpj);
	}
	
	public void addLabels() {
		Label titulo = new Label("Cadastro de Fornecedor",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
		titulo.setFont(Fontes.titulo());
		add(titulo);
		
		add(new Label("Nome:",125,180,50,30));
		add(new Label("E-mail:",385,180, 50,30));
		add(new Label("Serviço(s):",365,220,100,30));
	}
	
	public static void main(String[] args) {
		new TelaCadastroFornecedor();
	}
}
