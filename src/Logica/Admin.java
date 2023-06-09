package Logica;

public class Admin {
	private String nome;
	private String email;
	private String senha;

	public Admin(String nome, String email, String senha) {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}