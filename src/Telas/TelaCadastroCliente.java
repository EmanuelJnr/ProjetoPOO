package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;
import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.CentralDeInformacoes;
import Logica.Cliente;
import Logica.Persistencia;
import Logica.ValidaCNPJ;
import Logica.ValidaCPF;
import Logica.VerificaEmail;
import Ouvintes.OuvinteDeFoco;
import Ouvintes.OuvinteFocoFormatted;
import Ouvintes.OuvinteNovaTela;

public class TelaCadastroCliente extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private JRadioButton rbCPF;
	private JRadioButton rbCNPJ;
	private Botao btnCadastrar;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private JFormattedTextField tfTelefone;
	private JFormattedTextField tfCPF;
	private JFormattedTextField tfCNPJ;

	public TelaCadastroCliente() {
		super("Tela Cadastro Cliente");
		addRadioButtons();
		addLabels();
		addBotoes();
		addCamposDeTexto();
		ouvinteCadastrar();
		setVisible(true);
	}

	public void addCamposDeTexto() {
		tfNome = new CampoDeTexto("", 180,230,205,30);
		tfNome.addFocusListener(new OuvinteDeFoco(tfNome));
		add(tfNome);

		tfEmail = new CampoDeTexto("", 455,230,180,30);
		tfEmail.addFocusListener(new OuvinteDeFoco(tfEmail));
		add(tfEmail);

		try {
			MaskFormatter maskTel = new MaskFormatter("(##)#.####-####");
			tfTelefone = new JFormattedTextField(maskTel);
			tfTelefone.addFocusListener(new OuvinteFocoFormatted(tfTelefone));
			tfTelefone.setBounds(485,280,150,30);
			add(tfTelefone);
			
			MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
			tfCPF = new JFormattedTextField(maskCPF);
			tfCPF.addFocusListener(new OuvinteFocoFormatted(tfCPF));
			tfCPF.setBounds(242,280,143,30);
			add(tfCPF);
			
			MaskFormatter maskCNPJ = new MaskFormatter("##.###.###/####-##");
			tfCNPJ = new JFormattedTextField(maskCNPJ);
			tfCNPJ.addFocusListener(new OuvinteFocoFormatted(tfCNPJ));
			tfCNPJ.setBounds(242,315,143,30);
			add(tfCNPJ);
			tfCNPJ.setEditable(false);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
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
	
	public void addRadioButtons() {
		rbCPF = new JRadioButton("Pessoa Física");
		rbCPF.setBounds(130,285,110,20);
		rbCPF.setFont(Fontes.padrao());
		add(rbCPF);

		rbCNPJ = new JRadioButton("Pessoa Jurídica");
		rbCNPJ.setBounds(130,320,110,20);
		rbCNPJ.setFont(Fontes.padrao());
		add(rbCNPJ);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbCPF);
		grupo.add(rbCNPJ);
		rbCPF.setSelected(true);
		
		rbCPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCPF.setEditable(true);
				tfCPF.setText("");
				tfCNPJ.setEditable(false);
			}
		});
		rbCNPJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCNPJ.setEditable(true);
				tfCNPJ.setText("");
				tfCPF.setEditable(false);
			}
		});
	}

	public void addBotoes() {
		Botao btnVoltar = new Botao("Voltar",410,500,120,30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
		add(btnVoltar);

		btnCadastrar = new Botao("Cadastrar",270,500,120,30);
		add(btnCadastrar);
	}

	public void ouvinteCadastrar() {
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = tfNome.getText();
				String email = tfEmail.getText();
				String telefone = tfTelefone.getText();
				String cpf_cnpj = "";
				
				if(rbCPF.isSelected() && ValidaCPF.isCPF(tfCPF.getText())) {
					cpf_cnpj = tfCPF.getText();
				}
				else if (rbCPF.isSelected() && ValidaCPF.isCPF(tfCPF.getText()) == false){
					JOptionPane.showMessageDialog(null, "CPF vazio ou inválido!");
					return;
				}
				else if(rbCNPJ.isSelected() && ValidaCNPJ.isCNPJ(tfCNPJ.getText())) {
					cpf_cnpj = tfCNPJ.getText();
				}
				else{
					JOptionPane.showMessageDialog(null, "CNPJ vazio ou inválido!");
					return;
				}

				if(!nome.equals("") && !email.equals("") && !telefone.equals("")) {
					if(email != null) {
						if(VerificaEmail.isValid(email)) {
							if(ci.adicionarCliente(new Cliente(cpf_cnpj, nome, telefone, email))) {
								p.salvarCentral(ci);
								dispose();
								new TelaPrincipal();
							}
							else {
								JOptionPane.showMessageDialog(null, "Esse CPF ou CNPJ já existe no banco de dados!");
								tfCPF.setText("");
								tfCNPJ.setText("");
								return;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "E-mail inválido!");
							return;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Os campos de textos devem ser preenchidos!");
					return;
				}
			}
		});
	}
}