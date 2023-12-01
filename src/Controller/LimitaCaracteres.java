package Controller;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitaCaracteres extends PlainDocument{
	private static final long serialVersionUID = 1L;
	public enum TipoEntrada {NOME, NUMEROPONTO, NUMERO;};
	private TipoEntrada tpEntrada;

	public LimitaCaracteres(TipoEntrada tpEntrada) {
		this.tpEntrada = tpEntrada;
	}

	public void insertString(int i, String string, AttributeSet as) throws BadLocationException {
		if(string == null)
			return;

		String regex = "";
		switch (tpEntrada) {
		case NOME: regex = "[^\\p{IsLatin} ]"; break;
		case NUMEROPONTO: regex = "[^0-9.]"; break;
		case NUMERO: regex = "[^0-9]"; break;
		}
		string = string.replaceAll(regex, "");
		super.insertString(i, string, as);
	}
}