package Funcionalidades;

import java.awt.Font;

public abstract class Fontes {
	public static Font padrao() {
		return new Font("Tahoma", Font.PLAIN, 12);
	}
	public static Font titulo() {
		return new Font("Tahoma", Font.BOLD, 20);
	}
}