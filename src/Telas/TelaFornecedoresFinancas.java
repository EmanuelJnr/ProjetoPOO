package Telas;

import Funcionalidades.Fontes;
import Funcionalidades.NomeTela;
import Interface.Botao;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaFornecedoresFinancas extends TelaFornecedores{
	private static final long serialVersionUID = 1L;
	private Botao btnVoltar;
	private Label lbValor;
	private Botao btnPlanilha;
	
	public void addBotoes() {		
		btnVoltar = new Botao("Voltar",520,500,120,30);
		add(btnVoltar);
		
		btnPlanilha = new Botao("Planilha",370,500,120,30);
		add(btnPlanilha);
	}
	public void ouvintesBotoes() {
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PLANILHA_FINANCAS);
	}
	
	public void addLabels() {
		Label titulo = new Label("Fornecedores",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
		titulo.setFont(Fontes.titulo());
		add(titulo);
		
		add(new Label("Valor Total: ", 140, 500, 67, 30));
		
		lbValor = new Label("", 210, 500, 120, 30);
		lbValor.setText("soma dos valores");// fazer a soma dos valores da tabela e colcar o calculo aqui
		add(lbValor);
	}
	public static void main(String[] args) {
		new TelaFornecedoresFinancas();
	}
}