package DAO;

import java.util.ArrayList;

public class Pacote {
	private String nomePacote;
	private float valor;
	private ArrayList<Fornecedor> fornecedores;
	private String caracteristicas;

	public Pacote(String nomePacote, float valor, ArrayList<Fornecedor> fornecedores, String caracteristicas) {
		this.nomePacote = nomePacote;
		this.valor = valor;
		this.fornecedores = fornecedores;
		this.caracteristicas = caracteristicas;
	}
	public Pacote() {
		fornecedores = new ArrayList<>();
	}
	public boolean equals(Pacote p) {
		if(p.getNomePacote().equals(this.nomePacote)) {
			return true;
		}
		return false;
	}
	public boolean adicionarFornecedor(Fornecedor fAdd) {
		for (Fornecedor f : fornecedores) {
			if(f.equals(fAdd))
				return false;
		}
		fornecedores.add(fAdd);
		return true;
	}
	public String toString() {
		return nomePacote;
	}
	public ArrayList<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public String getNomePacote() {
		return nomePacote;
	}
	public void setNomePacote(String nomePacote) {
		this.nomePacote = nomePacote;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}