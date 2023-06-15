package Telas;

import Interface.Botao;
import Interface.CriarImagem;
import Interface.Label;
import Interface.NomeTela;
import Ouvintes.OuvinteNovaTela;

public class TelaPrincipal extends TelaPadrao{
	private static final long serialVersionUID = 1L;

	public TelaPrincipal() {
		super("Tela Principal");
		addBotoes();
		addLabel();
		setVisible(true);
	}

	public void addBotoes() {
		Botao btnOrcamentos = new Botao("Orçamentos/Contratos", 230, 281, 160, 36);
		OuvinteNovaTela.proximaTela(btnOrcamentos, this, NomeTela.TELA_ORCAMENTOS);
		add(btnOrcamentos);

		Botao btnFornecedores = new Botao("Fornecedores", 410, 281, 160, 36);
		OuvinteNovaTela.proximaTela(btnFornecedores, this, NomeTela.TELA_FORNECEDORES);
		add(btnFornecedores);

		Botao btnReunioes = new Botao("Reuniões", 230, 338, 160, 36);
		OuvinteNovaTela.proximaTela(btnReunioes, this, NomeTela.TELA_LISTAR_REUNIOES);
		add(btnReunioes);

		Botao btnServicos = new Botao("Serviços", 410, 338, 160, 36);
		OuvinteNovaTela.proximaTela(btnServicos, this, NomeTela.TELA_SERVICOS);
		add(btnServicos);

		Botao btnPacotes = new Botao("Pacotes", 230, 396, 160, 36);
		OuvinteNovaTela.proximaTela(btnPacotes, this, NomeTela.TELA_PACOTES);
		add(btnPacotes);

		Botao btnCadCliente = new Botao("Cadastrar Cliente", 410, 396, 160, 36);
		OuvinteNovaTela.proximaTela(btnCadCliente, this, NomeTela.TELA_CADASTRO_CLIENTE);
		add(btnCadCliente);

		Botao btnPlanilha = new Botao("Gerar Planilha", 313, 449, 160, 36);
		OuvinteNovaTela.proximaTela(btnPlanilha, this, NomeTela.TELA_PLANILHA_FINANCAS);
		add(btnPlanilha);

		Botao btnLogout = new Botao("Logout", 610, 510, 160, 36);
		OuvinteNovaTela.proximaTela(btnLogout, this, NomeTela.TELA_LOGIN_ADMIN);
		add(btnLogout);
	}

	public void addLabel() {
		Label logo = new Label("", 200, -60, 400, 400);
		logo.setIcon(CriarImagem.getImg("PartyHelper.png",400,400));
		add(logo);
	}
}