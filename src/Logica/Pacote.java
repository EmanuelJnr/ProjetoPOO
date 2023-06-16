package Logica;

import java.util.ArrayList;

public class Pacote {
	private String nomePacote;
	private float valor;
	private ArrayList<Fornecedor> fornecedores;
	private String caracteristicas;
	
	public Pacote(String nomePacote, float valor) {
		this.nomePacote = nomePacote;
		this.valor = valor;
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
