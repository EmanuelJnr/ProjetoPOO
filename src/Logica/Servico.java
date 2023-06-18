package Logica;

public class Servico {
	private String servico;

	public Servico(String servico) {
		this.servico = servico;
	}
	public Servico() {
	}
	
	public boolean equals(Servico s) {
		if(s.getServico().equals(servico))
			return true;
		return false;
	}
	public String toString() {
		return servico;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
}