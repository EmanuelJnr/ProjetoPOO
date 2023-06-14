package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import Funcionalidades.NomeTela;
import Telas.TelaAddFornecedorCadOrcamento;
import Telas.TelaAddFornecedorPacote;
import Telas.TelaAddServicoFornecedor;
import Telas.TelaCadastroCliente;
import Telas.TelaCadastroFornecedor;
import Telas.TelaCadastroOrcamento;
import Telas.TelaCadastroPacote;
import Telas.TelaDetalharFornecedor;
import Telas.TelaEditarOrcamento;
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

	public static void proximaTela(JButton botao, JFrame essaTela, NomeTela nomeTela) {

		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				essaTela.dispose();

				switch (nomeTela) {
				case TELAORCAMENTOS:
					new TelaOrcamentos();
					break;
				case TELAEDITARORCAMENTO:
					new TelaEditarOrcamento();
					break;
				case TELAFILTRARORCAMENTOS:
					new TelaFiltrarOrcamentos();
					break;
				case TELACADASTROORCAMENTO:
					new TelaCadastroOrcamento();
					break;
				case TELAFORNECEDORES:
					new TelaFornecedores();
					break;
				case TELAFORNECEDORESFINANCAS:
					new TelaFornecedoresFinancas();
					break;
				case TELAADDFORNECEDORORCAMENTO:
					new TelaAddFornecedorCadOrcamento();
					break;
				case TELAADDFORNECEDORPACOTE:
					new TelaAddFornecedorPacote();
					break;
				case TELAFILTRARFORNECEDOR:
					new TelaFiltrarFornecedor();
					break;
				case TELACADASTROFORNECEDOR:
					new TelaCadastroFornecedor();
					break;
				case TELADETALHARFORNECEDOR:
					new TelaDetalharFornecedor();
					break;
				case TELAREUNIOES:
					new TelaReunioes();
					break;
				case TELASERVICOS:
					new TelaServicos();
					break;
				case TELAADDSERVICOFORNECEDOR:
					new TelaAddServicoFornecedor();
					break;
				case TELAPACOTES:
					new TelaPacotes();
					break;
				case TELACADASTROPACOTE:
					new TelaCadastroPacote();
					break;
				case TELACADASTROCLIENTE:
					new TelaCadastroCliente();
					break;
				case TELAPLANILHAFINANCAS:
					new TelaPlanilhaFinancas();
					break;
				case TELALOGINADMIN:
					new TelaLoginAdmin();
					break;
				case TELAPRINCIPAL:
					new TelaPrincipal();
					break;

				} // Fim do switch case
			}
		});
	}// Fim do método
}// Fim da Classe