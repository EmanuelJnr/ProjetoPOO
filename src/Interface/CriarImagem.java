package Interface;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class CriarImagem {
	public static ImageIcon getImg(String nome, int x, int y) {
		ImageIcon icone = new ImageIcon("src/Imagens/" + nome);
		Image image = icone.getImage();
		Image newimg = image.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH);
		return icone = new ImageIcon(newimg);
	}
}