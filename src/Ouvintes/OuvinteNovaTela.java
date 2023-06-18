package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import Interface.NomeTela;
import Telas.TelaCadastroCliente;
import Telas.TelaCadastroFornecedor;
import Telas.TelaCadastroOrcamento;
import Telas.TelaCadastroPacote;
import Telas.TelaDetalharFornecedor;
import Telas.TelaEditarOrcamento;
import Telas.TelaFiltrarFornecedor;
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
				case TELA_ORCAMENTOS:
					new TelaOrcamentos();
					break;
				case TELA_EDITAR_ORCAMENTO:
					new TelaEditarOrcamento();
					break;
				case TELA_CADASTRO_ORCAMENTO:
					new TelaCadastroOrcamento();
					break;
				case TELA_FORNECEDORES:
					new TelaFornecedores();
					break;
				case TELA_FORNECEDORES_FINANCAS:
					new TelaFornecedoresFinancas();
					break;
				case TELA_FILTRAR_FORNECEDOR:
					new TelaFiltrarFornecedor();
					break;
				case TELA_CADASTRO_FORNECEDOR:
					new TelaCadastroFornecedor();
					break;
				case TELA_DETALHAR_FORNECEDOR:
					new TelaDetalharFornecedor();
					break;
				case TELA_REUNIOES:
					new TelaReunioes();
					break;
				case TELA_SERVICOS:
					new TelaServicos();
					break;
				case TELA_PACOTES:
					new TelaPacotes();
					break;
				case TELA_CADASTRO_PACOTE:
					new TelaCadastroPacote();
					break;
				case TELA_CADASTRO_CLIENTE:
					new TelaCadastroCliente();
					break;
				case TELA_PLANILHA_FINANCAS:
					new TelaPlanilhaFinancas();
					break;
				case TELA_LOGIN_ADMIN:
					new TelaLoginAdmin();
					break;
				case TELA_PRINCIPAL:
					new TelaPrincipal();
					break;

				} // Fim do switch case
			}
		});
	}// Fim do método
}// Fim da Classe