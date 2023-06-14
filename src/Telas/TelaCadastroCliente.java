package Telas;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import Funcionalidades.Fontes;
import Funcionalidades.NomeTela;
import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaCadastroCliente extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private JRadioButton rbFisica = new JRadioButton("Pessoa Física");
	private JRadioButton rbJuridica = new JRadioButton("Pessoa Jurídica");
	private Botao btnCadastrar;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private CampoDeTexto tfTelefone;
	private CampoDeTexto tfCpfCnpj;
	
	public TelaCadastroCliente() {
		super("Tela Cadastro Cliente");
		addLabels();
		addRadioButtons();
		addBotoes();
		addCamposDeTexto();
		setVisible(true);
	}
	
	public void addCamposDeTexto() {
		tfNome = new CampoDeTexto("", 180,230,205,30);
		add(tfNome);
		
		tfEmail = new CampoDeTexto("", 455,230,180,30);
		add(tfEmail);
		
		tfTelefone = new CampoDeTexto("", 485,280,150,30);
		add(tfTelefone);
		
		tfCpfCnpj = new CampoDeTexto("", 245,280,140,30);
		add(tfCpfCnpj);
	}
	
	public void addLabels() {
		Label lbTitulo = new Label("Cadastro Cliente", 0, 20, 800, 20);
		lbTitulo.setHorizontalAlignment(Label.CENTER);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);
		
		add(new Label("Nome:",135,230,80,30));
		add(new Label("Email:", 415,230,80,30));
		add(new Label("Telefone:", 415,280,80,30));
	}
	
	public void addBotoes() {
		Botao btnVoltar = new Botao("Voltar",410,500,120,30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELAPRINCIPAL);
		add(btnVoltar);
		
		btnCadastrar = new Botao("Cadastrar",270,500,120,30);
		add(btnCadastrar);
	}
	
	public void addRadioButtons() {
		rbFisica.setBounds(130,270,110,20);
		rbFisica.setFont(Fontes.padrao());
		add(rbFisica);
		
		rbJuridica.setBounds(130,295,110,20);
		rbJuridica.setFont(Fontes.padrao());
		add(rbJuridica);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbFisica);
		grupo.add(rbJuridica);
	}
	public static void main(String[] args) {
		new TelaCadastroCliente();
	}
}