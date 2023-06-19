package Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Orcamento {
	private String nomeEvento;
	private LocalDateTime dataHora;
	private LocalDateTime dataModificacao;
	private String localEvento;
	private String qtdConvidados;
	private ArrayList<Fornecedor> fornecedores;
	private ArrayList<Pacote> pacotes;
	private ArrayList<Reuniao> reunioes;
	private ArrayList<Foto> fotos;
	private float valor;
	private String responsavelPagamento;
	private String tipo;

	public Orcamento(String nomeEvento, LocalDateTime dataHora, String localEvento, String qtdConvidados, ArrayList<Fornecedor> fornecedores, ArrayList<Pacote> pacotes, float valor, String responsavelPagamento) {
		this.nomeEvento = nomeEvento;
		this.dataHora = dataHora;
		dataModificacao = LocalDateTime.now();
		this.localEvento = localEvento;
		this.qtdConvidados = qtdConvidados;
		this.fornecedores = fornecedores;
		this.pacotes = pacotes;
		reunioes = new ArrayList<>();
		setFotos(new ArrayList<>());
		this.valor = valor;
		this.responsavelPagamento = responsavelPagamento;
		this.tipo = "Orçamento";
	}
	public Orcamento() {
		dataModificacao = LocalDateTime.now();
		setFotos(new ArrayList<>());
		reunioes = new ArrayList<>();
		fornecedores = new ArrayList<>();
		pacotes = new ArrayList<>();
		this.tipo = "Orçamento";
	}

	public Fornecedor buscaFornecedor(String cpf_cnpj) {
		for(Fornecedor f : fornecedores) {
			if(cpf_cnpj.equals(f.getCPF_CNPJ())) {
				return f;
			}
		}
		return null;
	}
	public Pacote buscaPacote(String nome) {
		for(Pacote p : pacotes) {
			if(nome.equals(p.getNomePacote()))
				return p;
		}
		return null;
	}
	public boolean adicionarFornecedor(Fornecedor fAdd) {
		for (Fornecedor f : fornecedores) {
			if(f.equals(fAdd))
				return false;
		}
		fornecedores.add(fAdd);
		return true;
	}
	public boolean adicionarPacote(Pacote pAdd) {
		for (Pacote p : pacotes) {
			if(p.equals(pAdd))
				return false;
		}
		pacotes.add(pAdd);
		return true;
	}
	public boolean adicionarReuniao(Reuniao rAdd) {
		for (Reuniao r : reunioes) {
			if(r.equals(rAdd))
				return false;
		}
		reunioes.add(rAdd);
		return true;
	}
	public boolean adicionarFoto(Foto fAdd) {
		for (Foto f : fotos) {
			if(f.equals(fAdd))
				return false;
		}
		fotos.add(fAdd);
		return true;
	}
	public Foto buscaFoto(String caminhoDaFoto) {
		for(Foto f : fotos) {
			if(caminhoDaFoto.contains(f.getCaminhoDaFoto()))
				return f;
		}
		return null;
	}
	public boolean equals(Orcamento o) {
		if(o.getNomeEvento().equals(nomeEvento))
			return true;
		return false;
	}
	public String toString() {
		return nomeEvento;
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
	public ArrayList<Foto> getFotos() {
		return fotos;
	}
	public void setFotos(ArrayList<Foto> fotos) {
		this.fotos = fotos;
	}
}