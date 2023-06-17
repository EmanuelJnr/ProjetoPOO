package Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Orcamento {
	private String nomeEvento;
	private LocalDateTime dataHora;
	private String localEvento;
	private String qtdConvidados;
	private ArrayList<Fornecedor> fornecedores;
	private ArrayList<Pacote> pacotes;
	private ArrayList<Reuniao> reunioes;
	private ArrayList<ImageIcon> fotos;
	private float valor;
	private String responsavelPagamento;

	public Orcamento(String nomeEvento, LocalDateTime dataHora, String localEvento,
			String qtdConvidados, ArrayList<Fornecedor> fornecedores, ArrayList<Pacote> pacotes) {
		this.nomeEvento = nomeEvento;
		this.dataHora = dataHora;
		this.localEvento = localEvento;
		this.qtdConvidados = qtdConvidados;
		this.fornecedores = fornecedores;
		this.pacotes = pacotes;
	}
	public Orcamento() {

	}
	public boolean equals(Orcamento o) {
		if(o.getNomeEvento().equals(nomeEvento))
			return true;
		return false;
	}
	public String toString() {
		return nomeEvento;
	}
	public boolean jaOcorreu() {
		if(dataHora.isBefore(LocalDateTime.now()))
			return true;
		return false;
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
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
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
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public ArrayList<Pacote> getPacotes() {
		return pacotes;
	}
	public void setPacotes(ArrayList<Pacote> pacotes) {
		this.pacotes = pacotes;
	}
	public String getResponsavelPagamento() {
		return responsavelPagamento;
	}
	public void setResponsavelPagamento(String responsavelPagamento) {
		this.responsavelPagamento = responsavelPagamento;
	}
	public ArrayList<Reuniao> getReunioes() {
		return reunioes;
	}
	public void setReunioes(ArrayList<Reuniao> reunioes) {
		this.reunioes = reunioes;
	}
}
