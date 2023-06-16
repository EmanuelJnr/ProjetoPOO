package Logica;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class AlinhaCelulas {
	public static DefaultTableCellRenderer alinhar() {
		DefaultTableCellRenderer tabel = new DefaultTableCellRenderer();
		tabel.setHorizontalAlignment(SwingConstants.CENTER);
		return tabel;
	}
}