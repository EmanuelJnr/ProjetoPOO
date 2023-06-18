package Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Orcamento {
	private String nomeEvento;
	private LocalDateTime dataHora;
	private LocalDateTime dataModificacao;
	private String localEvento;
	private String qtdConvidados;
	private ArrayList<Fornecedor> fornecedores;
	private ArrayList<Pacote> pacotes;
	private ArrayList<Reuniao> reunioes;
	private ArrayList<ImageIcon> fotos;
	private float valor;
	private String responsavelPagamento;
	private String tipo;

	public Orcamento(String nomeEvento, LocalDateTime dataHora, String localEvento,
			String qtdConvidados, ArrayList<Fornecedor> fornecedores, ArrayList<Pacote> pacotes,
			float valor, String responsavelPagamento) {
		dataModificacao = LocalDateTime.now();
		fotos = new ArrayList<>();
		reunioes = new ArrayList<>();
		this.nomeEvento = nomeEvento;
		this.dataHora = dataHora;
		this.localEvento = localEvento;
		this.qtdConvidados = qtdConvidados;
		this.fornecedores = fornecedores;
		this.pacotes = pacotes;
		this.valor = valor;
		this.responsavelPagamento = responsavelPagamento;
		this.tipo = "Orçamento";
	}
	
	public boolean equals(Orcamento o) {
		if(o.getNomeEvento().equals(nomeEvento))
			return true;
		return false;
	}
	public String toString() {
		return nomeEvento;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDateTime getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(LocalDateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
}