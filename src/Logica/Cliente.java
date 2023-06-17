package Logica;

public class Cliente {
	private String nome;
	private String email;
	private String CPF_CNPJ;
	private String telefone;
	private Orcamento orcamento;

	public Cliente(String CPF_CNPJ, String nome, String telefone, String email) {
		setCPF_CNPJ(CPF_CNPJ);
		setNome(nome);
		setTelefone(telefone);
		setEmail(email);
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
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
}