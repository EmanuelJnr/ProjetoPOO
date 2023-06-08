package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaDetalharFornecedor extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private CampoDeTexto tfCpfCnpj;
	private JRadioButton rbFisica = new JRadioButton("Pessoa Física");
	private JRadioButton rbJuridica = new JRadioButton("Pessoa Jurídica");
	private Botao btnEditar;
	private Botao btnVoltar;
	
	private String[] opcoes = {"Teste", "Cachorro"};
	private JComboBox<String> boxEscolha = new JComboBox<>(opcoes); 
	
	public TelaDetalharFornecedor() {
		super("Detalhes Fornecedor");
		addLabels();
		addCamposDeTexto();
		addRadioButton();
		addBox();
		addBotoes();
		ouvinteEditar();
		setVisible(true);
	}
	
	public void addBox() {
		boxEscolha.setBounds(435,260,100,30);
		add(boxEscolha);
	}
	
	public void ouvinteEditar() { 
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Fazer comparação do que ele editou
				dispose();
				new TelaFornecedores();
			}
		});
	}
	
	public void addBotoes() {
		btnEditar = new Botao("Editar",270,500,120,30);
		
		btnVoltar = new Botao("Voltar",410,500,120,30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaFornecedores");
		
		add(btnEditar);
		add(btnVoltar);
	}
	
	public void addRadioButton() {
		rbFisica.setBounds(120,250,120,30);
		add(rbFisica);
		
		rbJuridica.setBounds(120,270,120,30);
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
		
		tfCpfCnpj = new CampoDeTexto("",250,260,105,30);
		add(tfCpfCnpj);
	}
	
	public void addLabels() {
		Label titulo = new Label("Detalhar/Editar Fornecedor",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
		titulo.setFont(Fontes.titulo());
		add(titulo);
		
		add(new Label("Nome:",125,180,50,30));
		add(new Label("E-mail:",385,180, 50,30));
		add(new Label("Serviço(s):",365,260,100,30));
	}
}
