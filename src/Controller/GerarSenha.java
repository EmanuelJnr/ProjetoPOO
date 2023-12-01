package Controller;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GerarSenha {
	// Método para gerar uma senha alfanumérica aleatória de um comprimento específico
	public static String senhaAletoria(int len)
	{
		// intervalo ASCII – alfanumérico (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();

		// cada iteração do loop escolhe aleatoriamente um caractere do dado
		// intervalo ASCII e o anexa à instância `StringBuilder`
		return IntStream.range(0, len)
				.map(i -> random.nextInt(chars.length()))
				.mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
				.collect(Collectors.joining());
	}
}