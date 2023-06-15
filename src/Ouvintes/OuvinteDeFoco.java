package Ouvintes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class OuvinteDeFoco implements FocusListener{
	private JTextField campo;

	public OuvinteDeFoco(JTextField campo) {
		this.campo = campo;
	}

	public void focusGained(FocusEvent arg0) {
		campo.setForeground(Color.BLACK);
		campo.setText("");
	}
	public void focusLost(FocusEvent arg0) {
		if(campo.getText().equals("")) {
			campo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			campo.setForeground(Color.RED);
			campo.setText("Preenchimento obrigatório!");
		}
	}
}