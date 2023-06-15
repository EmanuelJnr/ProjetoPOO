package Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Orcamento {
	private String nomeEvento;
	private LocalDateTime data;
	private String localEvento;
	private String qtdConvidados;
	private ArrayList<Fornecedor> fornecedores;
	private ArrayList<ImageIcon> fotos;
	
	public Orcamento(String nomeEvento, LocalDateTime data, String localEvento, String qtdConvidados) {
		this.nomeEvento = nomeEvento;
		this.data = data;
		this.localEvento = localEvento;
		this.qtdConvidados = qtdConvidados;
	}
	
	public ArrayList<ImageIcon> getFotos() {
		return fotos;
	}
	
	public void setFotos(ArrayList<ImageIcon> fotos) {
		this.fotos = fotos;
	}
	
	public ArrayList<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getLocalEvento() {
		return localEvento;
	}
	public void setLocalEvento(String localEvento) {
		this.localEvento = localEvento;
	}
	public String getQtdConvidados() {
		return qtdConvidados;
	}
	public void setQtdConvidados(String qtdConvidados) {
		this.qtdConvidados = qtdConvidados;
	}
}
