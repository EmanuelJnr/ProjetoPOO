package Telas;

import Interface.Botao;
import Interface.CriarImagem;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaPrincipal extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnOrcamentos;
	private Botao btnFornecedores;
	private Botao btnReunioes;
	private Botao btnServicos;
	private Botao btnPacotes;
	private Botao btnClientes;
	private Botao btnPlanilha;
	private Botao btnLogout;

	public TelaPrincipal() {
		super("Tela Principal");
		addBotoes();
		ouvintesBotoes();
		addLabel();
		setVisible(true);
	}
	
	public void addBotoes() {
		btnOrcamentos = new Botao("Orçamentos/Contratos", 230, 281, 160, 36);
		add(btnOrcamentos);
		
		btnFornecedores = new Botao("Fornecedores", 410, 281, 160, 36);
		add(btnFornecedores);
		
		btnReunioes = new Botao("Reuniões", 230, 338, 160, 36);
		add(btnReunioes);
		
		btnServicos = new Botao("Serviços", 410, 338, 160, 36);
		add(btnServicos);
		
		btnPacotes = new Botao("Pacotes", 230, 396, 160, 36);
		add(btnPacotes);
		
		btnClientes = new Botao("Clientes", 410, 396, 160, 36);
		add(btnClientes);
		
		btnPlanilha = new Botao("Gerar Planilha", 313, 449, 160, 36);
		add(btnPlanilha);
		
		btnLogout = new Botao("Logout", 610, 510, 160, 36);
		add(btnLogout);
	}
	
	public void ouvintesBotoes() {
		OuvinteNovaTela.proximaTela(btnOrcamentos, this, "TelaOrcamentos");
		OuvinteNovaTela.proximaTela(btnFornecedores, this, "TelaFornecedores");
		OuvinteNovaTela.proximaTela(btnReunioes, this, "TelaReunioes");
		OuvinteNovaTela.proximaTela(btnServicos, this, "TelaServicos");
		OuvinteNovaTela.proximaTela(btnPacotes, this, "TelaPacotes");
		OuvinteNovaTela.proximaTela(btnClientes, this, "TelaClientes");
		OuvinteNovaTela.proximaTela(btnPlanilha, this, "TelaPlanilhaFinancas");
		OuvinteNovaTela.proximaTela(btnLogout, this, "TelaLoginAdmin");
	}
	
	public void addLabel() {
		Label logo = new Label("", 200, -60, 400, 400);
		logo.setIcon(CriarImagem.getImg("PartyHelper.png",400,400));
		add(logo);
	}
}