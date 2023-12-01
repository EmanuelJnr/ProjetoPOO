package DAO;

public interface ClienteDAO {
	
	public boolean adicionarFornecedor(Fornecedor fAdd);
	
	public boolean adicionarPacote(Pacote pAdd);
	
	public Fornecedor buscaFornecedor(String cpf_cnpj);
	
	public Pacote buscaPacote(String nome);
	
	public String getCPF_CNPJ();
	
	public void setCPF_CNPJ(String cPF_CNPJ);
	
	public String getNome();
	
	public void setNome(String nome);
	
	public String getTelefone();
	
	public void setTelefone(String telefone);
	
	public String getEmail();
	
	public void setEmail(String email);
	
	public Orcamento getOrcamento();
	
	public void setOrcamento(Orcamento orcamento);
}