package Telas;

import Funcionalidades.NomeTela;
import Interface.Botao;
import Ouvintes.OuvinteNovaTela;

public class TelaListarReunioes extends TelaReunioes{
	private static final long serialVersionUID = 1L;
	private Botao btnVoltar;
	
	public void addBotoes() {
		btnVoltar = new Botao("Voltar", 500, 500, 120, 30);
		add(btnVoltar);
	}
	public void ouvinteBotoes() {
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PRINCIPAL);
	}
}
