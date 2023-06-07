package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.CampoDeSenha;
import Interface.CampoDeTexto;
import Interface.Label;
import Ouvintes.OuvinteLabel;

public class TelaLoginAdmin extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnEntrar;
	//private Botao btnVoltar;
	private CampoDeTexto tfEmail;
	private CampoDeSenha tfSenha;
	private Label lbEsqueceuSenha;

	public TelaLoginAdmin() {
		super("Login ADMIN");
		addBotoes();
		ouvinteEntrar();
		addLabels();
		addCamposTexto();
		addCTSenha();
		setVisible(true);
	}
<<<<<<< Updated upstream
	public static void main(String[] args) {
		new TelaLoginAdmin("Tela Login Admin");
=======

	public void addBotoes() {
		btnEntrar = new Botao("Entrar", 260, 510, 120, 30);
		add(btnEntrar);

		//btnVoltar = new Botao("Voltar", 400, 510, 120, 30);
		//add(btnVoltar);
	}

	public void ouvinteEntrar() {
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verificar se o e-mail e senha do admin estão corretos.
				dispose();
				new TelaPrincipal();
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
	}
	private void addCTSenha() {
		tfSenha = new CampoDeSenha(260, 250, 300, 20);
		add(tfSenha);
>>>>>>> Stashed changes
	}
}