package DAO;

import java.util.ArrayList;

public class Fornecedor {
	private String nome;
	private String email;
	private String CPF_CNPJ;
	private String telefone;
	private ArrayList<Servico> servicos;
	private String diponivel;
	private int qtdContratos;
	private String comentarios;
	private float valor;

	public Fornecedor(String nome, String email, String cPF_CNPJ, String telefone, ArrayList<Servico> servicos) {
		diponivel = "Sim";
		this.nome = nome;
		this.email = email;
		this.CPF_CNPJ = cPF_CNPJ;
		this.telefone = telefone;
		this.servicos = servicos;
	}
	public Fornecedor() {
		diponivel = "Sim";
		servicos = new ArrayList<>();
	}
	public String toString() {
		return nome;
	}
	public boolean equals(Fornecedor f) {
		if(f.getCPF_CNPJ().equals(CPF_CNPJ))
			return true;
		return false;
	}
	public boolean adicionarServico(Servico sAdd) {
		for (Servico s : servicos) {
			if(s.equals(sAdd))
				return false;
		}
		servicos.add(sAdd);
		return true;
	}
	public ArrayList<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(ArrayList<Servico> servicos) {
		this.servicos = servicos;
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
	public String getCPF_CNPJ() {
		return CPF_CNPJ;
	}
	public void setCPF_CNPJ(String cPF_CNPJ) {
		CPF_CNPJ = cPF_CNPJ;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDiponivel() {
		return diponivel;
	}
	public void setDiponivel(String diponivel) {
		this.diponivel = diponivel;
	}
	public int getQtdContratos() {
		return qtdContratos;
	}
	public void setQtdContratos(int qtdContratos) {
		this.qtdContratos = qtdContratos;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}