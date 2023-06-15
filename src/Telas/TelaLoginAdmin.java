package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Interface.Botao;
import Interface.CampoDeSenha;
import Interface.CampoDeTexto;
import Interface.Fontes;
import Interface.Label;
import Logica.CentralDeInformacoes;
import Logica.Persistencia;
import Ouvintes.OuvinteLabel;

public class TelaLoginAdmin extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Botao btnEntrar;
	private CampoDeTexto tfEmail;
	private CampoDeSenha tfSenha;
	private Label lbEsqueceuSenha;

	public TelaLoginAdmin() {
		super("Login ADMIN");
		addBotoes();
		addLabels();
		addCamposTexto();
		ouvinteEntrar();
		setVisible(true);
	}

	public void addBotoes() {
		btnEntrar = new Botao("Entrar", 340, 510, 120, 30);
		add(btnEntrar);
	}

	public void ouvinteEntrar() {
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = tfEmail.getText();
				String senha = String.valueOf(tfSenha.getPassword());

				if(ci.getAdmin().getEmail().equals(email)) {
					if(ci.getAdmin().getSenha().equals(senha)) {
						dispose();
						new TelaPrincipal();
					}
					else {
						JOptionPane.showMessageDialog(null, "A senha está errada!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "O email está errado!");
				}
			}
		});
	}

	public void addLabels() {
		Label titulo = new Label("Login Administrador", 297, 20, 206, 25);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("E-mail:", 200, 200, 80, 20));

		add(new Label("Senha:", 200, 250, 80, 20));

		lbEsqueceuSenha = new Label("Esqueci a Senha", 355, 350, 90, 20);
		OuvinteLabel.ouvinteLabel(lbEsqueceuSenha, this);
		add(lbEsqueceuSenha);
	}

	public void addCamposTexto() {
		tfEmail = new CampoDeTexto("", 260, 200, 300, 20);
		add(tfEmail);

		tfSenha = new CampoDeSenha(260, 250, 300, 20);
		add(tfSenha);
	}
}