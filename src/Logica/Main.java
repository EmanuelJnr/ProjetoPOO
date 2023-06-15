package Logica;

import Telas.TelaCadastroAdmin;
import Telas.TelaLoginAdmin;

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