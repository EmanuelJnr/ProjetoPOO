package Ouvintes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class OuvinteFocoFormatted implements FocusListener{
	private JTextField campo;

	public OuvinteFocoFormatted(JTextField campo) {
		this.campo = campo;
	}

	public void focusGained(FocusEvent arg0) {
		campo.setText("");
	}
	public void focusLost(FocusEvent arg0) {
	}
}
