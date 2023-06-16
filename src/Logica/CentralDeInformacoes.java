package Logica;

import java.util.ArrayList;

public class CentralDeInformacoes {
	private Admin admin;
	private ArrayList<Servico> servicosTemp = new ArrayList<>();
	private Fornecedor fornecedorTemp = new Fornecedor();
	private ArrayList<Fornecedor> fornecedoresTemp = new ArrayList<>();
	
	private ArrayList<Cliente> todosOsClientes = new ArrayList<>();
	private ArrayList<Pacote> todosOsPacotes = new ArrayList<>();
	private ArrayList<Fornecedor> todosOsFornecedores = new ArrayList<>();
	private ArrayList<Servico> todosOsServicos = new ArrayList<>();
	private ArrayList<Reuniao> todasAsReunioes = new ArrayList<>();
	private ArrayList<Orcamento> todosOsOrcamentos = new ArrayList<>();
	
	public boolean adicionarFornecedorTemp(Fornecedor fAdd) {
		for (Fornecedor f : fornecedoresTemp) {
			if(f.equals(fAdd)) {
				return false;
			}
		}
		fornecedoresTemp.add(fAdd);
		return true;
	}
	public boolean adicionarServicoTemp(Servico sAdd) {
		for (Servico s : servicosTemp) {
			if(s.equals(sAdd)) {
				return false;
			}
		}
		servicosTemp.add(sAdd);
		return true;
	}
	public boolean adicionarCliente(Cliente cAdd) {
		for (Cliente cliente : todosOsClientes) {
			if(cAdd.equals(cliente))
				return false;			
		}
		todosOsClientes.add(cAdd);
		return true;
	}
	public boolean adicionarFornecedor(Fornecedor fAdd) {
		for (Fornecedor f : todosOsFornecedores) {
			if(f.equals(fAdd)) {
				return false;
			}
		}
		todosOsFornecedores.add(fAdd);
		return true;
	}
	public void adicionarPacote(Pacote p) {
		todosOsPacotes.add(p);
	}
	public void adicionarServico(Servico s) {
		todosOsServicos.add(s);
	}
	
	public Cliente recuperarClientePeloCPF_CNPJ(String CPF_CNPJ) {
		for (Cliente c : todosOsClientes) {
			if(CPF_CNPJ.equals(c.getCPF_CNPJ()))
				return c;
		}
		return null;
	}
	
	public Fornecedor buscaFornecedor(String cpf_cnpj) {
		for(Fornecedor f : todosOsFornecedores) {
			if(cpf_cnpj.equals(f.getCPF_CNPJ())) {
				return f;
			}
		}
		return null;
	}
	public Servico buscaServicoFornecedores(String nome) {
		for (Fornecedor f : todosOsFornecedores) {
			for (Servico s : f.getServicos()) {
				if(s.getServico().equals(nome)) {
					return s;
				}
			}
		}
		return null;
	}
	public Servico buscaServico(String nome) {
		for(Servico s : todosOsServicos) {
			if(nome.equals(s.getServico())) {
				return s;
			}
		}
		return null;
	}
	public Pacote buscaPacote(String nome) {
		for(Pacote p : todosOsPacotes) {
			if(nome.equals(p.getNomePacote())) {
				return p;
			}
		}
		return null;
	}
	public Fornecedor buscaFornecedorPacote(String cpf_cnpj) {
		for (Pacote p : todosOsPacotes) {
			for (Fornecedor f : p.getFornecedores()) {
				if(cpf_cnpj.equals(f.getCPF_CNPJ())) {
					return f;
				}
			}
		}
		return null;
	}
	
	
	public ArrayList<Reuniao> getTodasAsReunioes() {
		return todasAsReunioes;
	}
	public void setTodasAsReunioes(ArrayList<Reuniao> todasAsReunioes) {
		this.todasAsReunioes = todasAsReunioes;
	}
	public ArrayList<Orcamento> getTodosOsOrcamentos() {
		return todosOsOrcamentos;
	}
	public void setTodosOsOrcamentos(ArrayList<Orcamento> todosOsOrcamentos) {
		this.todosOsOrcamentos = todosOsOrcamentos;
	}
	public ArrayList<Pacote> getTodosOsPacotes() {
		return todosOsPacotes;
	}
	public void setTodosOsPacotes(ArrayList<Pacote> todosOsPacotes) {
		this.todosOsPacotes = todosOsPacotes;
	}
	public ArrayList<Fornecedor> getTodosOsFornecedores() {
		return todosOsFornecedores;
	}
	public void setTodosOsFornecedores(ArrayList<Fornecedor> todosOsFornecedores) {
		this.todosOsFornecedores = todosOsFornecedores;
	}
	public ArrayList<Servico> getTodosOsServicos() {
		return todosOsServicos;
	}
	public void setTodosOsServicos(ArrayList<Servico> todosOsServicos) {
		this.todosOsServicos = todosOsServicos;
	}
	public ArrayList<Cliente> getTodosOsClientes() {
		return todosOsClientes;
	}
	public void setTodosOsClientes(ArrayList<Cliente> todosOsClientes) {
		this.todosOsClientes = todosOsClientes;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public ArrayList<Servico> getServicosTemp() {
		return servicosTemp;
	}
	public void setServicosTemp(ArrayList<Servico> servicosTemp) {
		this.servicosTemp = servicosTemp;
	}
	public Fornecedor getFornecedorTemp() {
		return fornecedorTemp;
	}
	public void setFornecedorTemp(Fornecedor fornecedorTemp) {
		this.fornecedorTemp = fornecedorTemp;
	}
	public ArrayList<Fornecedor> getFornecedoresTemp() {
		return fornecedoresTemp;
	}
	public void setFornecedoresTemp(ArrayList<Fornecedor> fornecedoresTemp) {
		this.fornecedoresTemp = fornecedoresTemp;
	}
}