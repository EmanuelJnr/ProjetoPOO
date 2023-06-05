package Telas;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Label;

public class TelaCadastroAdmin extends TelaPadrao{
	private static final long serialVersionUID = 1L;

	public TelaCadastroAdmin () {
		 super("Cadastro Administrador");
		 addTextFields();
	     addLabels();
	     addBotao();
	     setVisible(true);
	 }
	 public void addLabels() {
		 Label lbTitulo = new Label("CADASTRO ADMINISTRADOR",0, 20, 800, 20);
		 lbTitulo.setHorizontalAlignment(Label.CENTER);
		 lbTitulo.setFont(titulo1);
		 
		 Label lbNome = new Label("Nome:", 250, 150, 80, 20);
		 lbNome.setFont(fonte);
		 
		 Label lbEmail = new Label("E-mail:", 250, 200, 80, 20);
		 lbEmail.setFont(fonte);
		 
		 Label lbSenha = new Label("Senha:", 250, 250, 80, 20);
		 lbSenha.setFont(fonte);
		 
		 Label lbConfirmarSenha = new Label("Confirmar Senha:", 250, 300, 100, 20);
		 lbConfirmarSenha.setFont(fonte);
		 
		 add(lbNome);
		 add(lbTitulo);
		 add(lbEmail);
		 add(lbSenha);
		 add(lbConfirmarSenha);
	 }
	 public void addTextFields() {
		 CampoDeTexto textFieldNome = new CampoDeTexto("", 350, 145, 200, 30);

	     JTextField textFieldEmail = new JTextField();
	     textFieldEmail.setBounds(350, 195, 200, 30);
	     
	     JPasswordField textFieldSenha = new JPasswordField();
	     textFieldSenha.setBounds(350, 245, 200, 30);

	     JPasswordField textFieldConfirmarSenha = new JPasswordField();
	     textFieldConfirmarSenha.setBounds(350, 295, 200, 30);
	     
	     add(textFieldNome);
	     add(textFieldEmail);
	     add(textFieldSenha);
	     add(textFieldConfirmarSenha);
	 }
	 public void addBotao() {
		 add(new Botao("Confirmar", 12, 340, 500, 120, 30));
	 }
	 
	 public static void main(String[] args) {
		new TelaCadastroAdmin();
	}
}
