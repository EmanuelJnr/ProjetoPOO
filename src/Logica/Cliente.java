package Logica;

import java.time.LocalDateTime;

public class Cliente {
	private String CPF_CNPJ;
	private String nome;
	private String telefone;
	private String email;
	private LocalDateTime dataNascimento;
	private String endereco;

	public Cliente(String CPF_CNPJ, String nome, String telefone, String email, LocalDateTime dataNascimento, String endereco) {
		setCPF_CNPJ(CPF_CNPJ);
		setNome(nome);
		setTelefone(telefone);
		setEmail(email);
		setDataNascimento(dataNascimento);
	}

	public String toString() {
		return "Nome: " + nome + ", CPF/CNPJ: " + CPF_CNPJ;
	}
	public boolean equals(Cliente c) {
		if(c.getCPF_CNPJ().equals(CPF_CNPJ))
			return true;
		return false;
	}
	public String getCPF_CNPJ() {
		return CPF_CNPJ;
	}
	public void setCPF_CNPJ(String cPF_CNPJ) {
		CPF_CNPJ = cPF_CNPJ;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}