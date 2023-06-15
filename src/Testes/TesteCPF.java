package Testes;

import java.util.Scanner;
import Logica.ValidaCPF;

public class TesteCPF {
	public static void main(String[] args) {
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