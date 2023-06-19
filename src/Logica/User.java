package Logica;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String adress;
	private int telefone;
	
	public User(int id, String name, String email, String password, String adress, int telefone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.adress = adress;
		this.telefone = telefone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	
}
