package Telas;

import Interface.Botao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Interface.CampoDeSenha;
import Interface.CampoDeTexto;
import Interface.Fontes;
import Interface.Label;
import Logica.Admin;
import Logica.CentralDeInformacoes;
import Logica.Persistencia;
import Logica.VerificaEmail;

public class TelaCadastroAdmin extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private CampoDeSenha tfSenha;
	private CampoDeSenha tfConfirmarSenha;
	private Botao btnCadastrar;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();

	public TelaCadastroAdmin () {
		super("Cadastro ADMIN");
		addCamposDeTexto();
		addLabels();
		addBotoes();
		ouvinteCadastrar();
		setVisible(true);
	}

	public void addLabels() {
		Label lbTitulo = new Label("CADASTRO ADMINISTRADOR", 251, 20, 298, 20);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);

		add(new Label("Nome:", 250, 150, 80, 20));

		add(new Label("E-mail:", 250, 200, 80, 20));

		add(new Label("Senha:", 250, 250, 80, 20));

		add(new Label("Confirmar Senha:", 250, 300, 100, 20));
	}

	public void addCamposDeTexto() {
		tfNome = new CampoDeTexto("", 350, 145, 200, 30);
		add(tfNome);

		tfEmail = new CampoDeTexto("",350, 195, 200, 30);
		add(tfEmail);

		tfSenha = new CampoDeSenha(350, 245, 200, 30);
		add(tfSenha);

		tfConfirmarSenha = new CampoDeSenha(350, 295, 200, 30);
		add(tfConfirmarSenha);
	}

	public void addBotoes() {
		btnCadastrar = new Botao("Cadastrar", 340, 500, 120, 30);
		add(btnCadastrar);
	}

	public void ouvinteCadastrar() {
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = tfNome.getText();
				String email = tfEmail.getText();
				String senha = String.valueOf(tfSenha.getPassword());
				String confSenha = String.valueOf(tfConfirmarSenha.getPassword());
				if(!nome.equals("") && !email.equals("") && !senha.equals("") && !confSenha.equals("")) {
					if(senha.equals(confSenha)) {
						if(email != null) {
							if(VerificaEmail.isValid(email)) {
								ci.setAdmin(new Admin(nome, email, senha));
								p.salvarCentral(ci);
								dispose();
								new TelaLoginAdmin();
							}
							else {
								JOptionPane.showMessageDialog(null, "E-mail inválido!");
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Os campos de textos devem ser preenchidos!");
				}
			}
		});
	}
}