package Logica;

import java.util.ArrayList;

public class CentralDeInformacoes {
	private Admin admin;
	
	private ArrayList<Cliente> todosOsClientes = new ArrayList<>();
	
	public boolean adicionarCliente(Cliente cAdd) {
		for (Cliente cliente : todosOsClientes) {
			if(cAdd.equals(cliente))
				return false;			
		}
		todosOsClientes.add(cAdd);
		return true;
	}
	public Cliente recuperarClientePeloCPF_CNPJ(String CPF_CNPJ) {
		for (Cliente c : todosOsClientes) {
			if(CPF_CNPJ.equals(c.getCPF_CNPJ()))
				return c;
		}
		return null;
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
}