package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import Telas.TelaCadastroFornecedor;
import Telas.TelaClientes;
import Telas.TelaDetalharFornecedor;
import Telas.TelaFiltrarFornecedor;
import Telas.TelaFornecedores;
import Telas.TelaLoginAdmin;
import Telas.TelaOrcamentos;
import Telas.TelaPacotes;
import Telas.TelaPlanilhaFinancas;
import Telas.TelaPrincipal;
import Telas.TelaReunioes;
import Telas.TelaServicos;

public abstract class OuvinteNovaTela implements ActionListener{

	public static void proximaTela(JButton b, JFrame essaTela, String novaTela) {
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				essaTela.dispose();
				
				switch (novaTela) {
				case "TelaOrcamentos":
					new TelaOrcamentos();
					break;
				case "TelaFornecedores":
					new TelaFornecedores();
					break;
				case "TelaReunioes":
					new TelaReunioes();
					break;
				case "TelaServicos":
					new TelaServicos();
					break;
				case "TelaPacotes":
					new TelaPacotes();
					break;
				case "TelaClientes":
					new TelaClientes();
					break;
				case "TelaPlanilhaFinancas":
					new TelaPlanilhaFinancas();
					break;
				case "TelaLoginAdmin":
					new TelaLoginAdmin();
					break;
				case "TelaFiltrarFornecedor":
					new TelaFiltrarFornecedor();
					break;
				case "TelaPrincipal":
					new TelaPrincipal();
					break;
				case "TelaCadastroFornecedor":
					new TelaCadastroFornecedor();
					break;
				case "TelaDetalharFornecedor":
					new TelaDetalharFornecedor();
					break;
				} // Fim do switch case
				
			}
		});
	}
}