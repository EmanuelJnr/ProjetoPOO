package Telas;

import Interface.Botao;
import Interface.CriarImagem;
import Interface.Label;
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
		OuvinteNovaTela.proximaTela(btnOrcamentos, this, "TelaOrcamentos");
		add(btnOrcamentos);

		Botao btnFornecedores = new Botao("Fornecedores", 410, 281, 160, 36);
		OuvinteNovaTela.proximaTela(btnFornecedores, this, "TelaFornecedores");
		add(btnFornecedores);

		Botao btnReunioes = new Botao("Reuniões", 230, 338, 160, 36);
		OuvinteNovaTela.proximaTela(btnReunioes, this, "TelaReunioes");
		add(btnReunioes);

		Botao btnServicos = new Botao("Serviços", 410, 338, 160, 36);
		OuvinteNovaTela.proximaTela(btnServicos, this, "TelaServicos");
		add(btnServicos);

		Botao btnPacotes = new Botao("Pacotes", 230, 396, 160, 36);
		OuvinteNovaTela.proximaTela(btnPacotes, this, "TelaPacotes");
		add(btnPacotes);

		Botao btnClientes = new Botao("Clientes", 410, 396, 160, 36);
		OuvinteNovaTela.proximaTela(btnClientes, this, "TelaClientes");
		add(btnClientes);

		Botao btnPlanilha = new Botao("Gerar Planilha", 313, 449, 160, 36);
		OuvinteNovaTela.proximaTela(btnPlanilha, this, "TelaPlanilhaFinancas");
		add(btnPlanilha);

		Botao btnLogout = new Botao("Logout", 610, 510, 160, 36);
		OuvinteNovaTela.proximaTela(btnLogout, this, "TelaLoginAdmin");
		add(btnLogout);
	}

	public void addLabel() {
		Label logo = new Label("", 200, -60, 400, 400);
		logo.setIcon(CriarImagem.getImg("PartyHelper.png",400,400));
		add(logo);
	}
}