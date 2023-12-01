package DTO;

public class ClienteDTO {
	private String nome;
	private String CPF_CNPJ;
	
	public ClienteDTO(String CPF_CNPJ, String nome) {
		setCPF_CNPJ(CPF_CNPJ);
		setNome(nome);
	}
	
	public boolean equals(ClienteDTO c) {
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
}