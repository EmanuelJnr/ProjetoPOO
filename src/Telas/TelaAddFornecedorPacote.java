package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Funcionalidades.NomeTela;
import Interface.Botao;
import Ouvintes.OuvinteNovaTela;

public class TelaAddFornecedorPacote extends TelaAddFornecedorCadOrcamento{
	private static final long serialVersionUID = 1L;
	private Botao btnAdicionar;
	private Botao btnVoltar;
	
	public void addBotoes() {
		btnAdicionar = new Botao("Adicionar",200,500,120,30);
		add(btnAdicionar);

		btnVoltar = new Botao("Voltar",480,500,120,30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_CADASTRO_PACOTE);
		add(btnVoltar);
	}
	public void ouvinteBtnAdicionar() {
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Adiciona fornecedor da tabela no pacote
				dispose();
				new TelaCadastroPacote();
			}
		});
	}
}