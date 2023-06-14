package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import Telas.TelaAddFornecedorOrcamento;
import Telas.TelaAddFornecedorPacote;
import Telas.TelaAddServico;
import Telas.TelaCadastroFornecedor;
import Telas.TelaCadastroOrcamento;
import Telas.TelaCadastroPacote;
import Telas.TelaClientes;
import Telas.TelaDetalharFornecedor;
import Telas.TelaFiltrarFornecedor;
import Telas.TelaFiltrarOrcamentos;
import Telas.TelaFornecedores;
import Telas.TelaFornecedoresFinancas;
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
				case "TelaFiltrarOrcamentos":
					new TelaFiltrarOrcamentos();
					break;
				case "TelaCadastroOrcamento":
					new TelaCadastroOrcamento();
					break;
				case "TelaFornecedores":
					new TelaFornecedores();
					break;
				case "TelaFornecedoresFinancas":
					new TelaFornecedoresFinancas();
					break;
				case "TelaAddFornecedorOrcamento":
					new TelaAddFornecedorOrcamento();
					break;
				case "TelaAddFornecedorPacote":
					new TelaAddFornecedorPacote();
					break;
				case "TelaFiltrarFornecedor":
					new TelaFiltrarFornecedor();
					break;
				case "TelaCadastroFornecedor":
					new TelaCadastroFornecedor();
					break;
				case "TelaDetalharFornecedor":
					new TelaDetalharFornecedor();
					break;
				case "TelaReunioes":
					new TelaReunioes();
					break;
				case "TelaServicos":
					new TelaServicos();
					break;
				case "TelaAddServico":
					new TelaAddServico();
					break;
				case "TelaPacotes":
					new TelaPacotes();
					break;
				case "TelaCadastroPacote":
					new TelaCadastroPacote();
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
				case "TelaPrincipal":
					new TelaPrincipal();
					break;

				} // Fim do switch case
			}
		});
	}// Fim do método
}// Fim da Classe