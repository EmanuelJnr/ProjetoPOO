package Testes;

import java.util.Scanner;
import javax.swing.JOptionPane;
import Logica.ValidaCPF;
import Logica.VerificaEmail;

public class TesteCPF {
	public static void main(String[] args) {
		String txt;
		txt = JOptionPane.showInputDialog(null, "Não foi possível enviar uma nova senha, cadastre um novo e-mail");
		System.out.println(VerificaEmail.isValid(txt));// ok sem nada escrito = "", cancel e fechar = null
		
		Scanner ler = new Scanner(System.in);
		
		String CPFCliente="";

		System.out.printf("Informe um CPF: ");
		String entrada = ler.nextLine();//a entrada precisa ser 111.111.111-11
		
		if (ValidaCPF.isCPF(entrada) == true) {
			CPFCliente = entrada;
			System.out.println(CPFCliente+" é valido.");
		}
		else
			System.out.printf("Erro, CPF invalido !!!\n");
		
		ler.close();
	}
}