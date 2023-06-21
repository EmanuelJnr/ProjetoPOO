package Telas;

public class teste {
	public static void main(String[] args) {
		String nomeCompleto = "emanuel lopes ventura junior";
		
		String[] nomeSplit = nomeCompleto.split(" ");
		String resultado = "";
        for (String nome : nomeSplit) {
            String primeiraLetra = nome.substring(0, 1).toUpperCase();
            String restanteNome = nome.substring(1);
            String nomeCapitalizado = primeiraLetra + restanteNome;
            resultado += nomeCapitalizado+" ";
        }
        System.out.println(resultado);
	}

}