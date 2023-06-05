package Telas;

import javax.swing.JLabel;

import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Label;

public class TelaLoginAdmin extends TelaPadrao {
	public TelaLoginAdmin(String nome) {
		super(nome);
		Label titulo = new Label("Login Administrador", 0, 20, 800, 30);
		
		Label lbEmail = new Label("E-mail:", 200, 200, 80, 20);
		Label lbSenha = new Label("Senha:", 200, 250, 80, 20);
		
		CampoDeTexto tfEmail = new CampoDeTexto("", 260, 200, 300, 20);
		CampoDeTexto tfSenha = new CampoDeTexto("", 260, 250, 300, 20);
		
		Botao btnConfirmar = new Botao("Confirmar", 260, 510, 120, 30);
		Botao btnVoltar = new Botao("Voltar", 400, 510, 120, 30);
		
		titulo.setHorizontalAlignment(JLabel.CENTER);
		
		titulo.setFont(titulo1);
		lbEmail.setFont(fonte);
		lbSenha.setFont(fonte);
		
		add(lbSenha);
		add(lbEmail);
		add(titulo);
		add(tfEmail);
		add(tfSenha);
		add(btnConfirmar);
		add(btnVoltar);
		setVisible(true);
	}
}
