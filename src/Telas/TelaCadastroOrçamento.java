package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Label;
import Ouvintes.OuvinteNovaTela;

public class TelaCadastroOrçamento extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private CampoDeTexto tfNomeEvento;
	private CampoDeTexto tfDataHora;
	private CampoDeTexto tfLocalEvento;
	private CampoDeTexto tfQtdConvidados;
	private Botao btnCadastrar;

	public TelaCadastroOrçamento () {
		 super("Cadastro Orçamento");
		 addCamposDeTexto();
	     addLabels();
	     addBotoes();
	     ouvinteCadastrar();
	     setVisible(true);
	}
	
	public void addLabels() {
		 Label lbTitulo = new Label("Cadastrar Orçamento", 280, 20, 240, 20);
		 lbTitulo.setFont(Fontes.titulo());
		 add(lbTitulo);
		 
		 add(new Label("Nome do evento:", 190, 70, 100, 20));
		 
		 add(new Label("Data e hora:", 190, 120, 100, 20));
		 
		 add(new Label("Local do evento:", 190, 170, 100, 20));
		 
		 add(new Label("Quantidade de convidados:", 190, 220, 150, 20));
	 }
	 
	 public void addCamposDeTexto() {
		 tfNomeEvento = new CampoDeTexto("", 380, 65, 200, 30);
		 add(tfNomeEvento);

	     tfDataHora = new CampoDeTexto("", 380, 115, 200, 30);
	     add(tfDataHora);
	     
	     tfLocalEvento = new CampoDeTexto("", 380, 165, 200, 30);
	     add(tfLocalEvento);

	     tfQtdConvidados = new CampoDeTexto("", 380, 215, 200, 30);
	     add(tfQtdConvidados);
	 }
	 
	 public void addBotoes() {
		 btnCadastrar = new Botao("Cadastrar", 240, 500, 120, 30);
		 add(btnCadastrar);
		 
		 Botao btnCancelar = new Botao("Cancelar", 440, 500, 120, 30);
		 OuvinteNovaTela.proximaTela(btnCancelar, this, "TelaOrcamentos");
		 add(btnCancelar);
	 }
	 
	 public void ouvinteCadastrar() {
		 btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cria um admin e guarda os valores nele, faz as validações dos campos de texto.
				dispose();
				new TelaLoginAdmin();
			}
		});
	}
	public static void main(String[] args) {
		new TelaCadastroOrçamento();
	}
}