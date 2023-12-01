package Controller;

import View.TelaCadastroAdmin;
import View.TelaLoginAdmin;

public class Main {
	public static void main(String[] args) {
		Persistencia p = new Persistencia();
		CentralDeInformacoes ci = p.recuperarCentral();
		if(ci.getAdmin()==null) {
			new TelaCadastroAdmin();
		}
		else {
			new TelaLoginAdmin();
		}
	}
}