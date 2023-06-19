package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import Interface.Botao;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.CentralDeInformacoes;
import Logica.Fornecedor;
import Logica.Pacote;
import Logica.Persistencia;
import Logica.Servico;
import Ouvintes.OuvinteNovaTela;

public class TelaDetalhamentoPacote extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Pacote pacoteRef = ci.getPacoteRef();
	private Label lbNome;
	private Label lbValor;
	private Botao btnExcluir;
	private Botao btnVoltar;

	public TelaDetalhamentoPacote() {
		super("Detalhamento do pacote");
		addLabels();
		addTextArea();
		addBotoes();
		ouvinteBotoes();
		setVisible(true);
	}

	public void addLabels() {
		Label titulo = new Label("Pacotes", 345, 30, 120, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Nome: ", 120, 120, 120, 25));
		add(new Label("Preço: ", 120, 170, 120, 25));
		add(new Label("Serviços prestados: ", 120, 220, 200, 25));

		lbNome = new Label(pacoteRef.getNomePacote(), 300, 115, 120, 25);
		add(lbNome);
		lbValor = new Label(pacoteRef.getValor()+" R$", 300, 165, 120, 25);
		add(lbValor);
	}

	public void addTextArea() {
		String servicos = "";
		for (Fornecedor f : pacoteRef.getFornecedores()) {
			for (Servico s : f.getServicos()) {
				servicos += s.getServico()+"\n";
			}
		}
		JTextArea taServicos = new JTextArea(5,10);
		taServicos.setText(servicos);
		taServicos.setBounds(300, 220, 300, 200);
		add(taServicos);
	}

	public void addBotoes() {
		btnVoltar = new Botao("Voltar", 450, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_PACOTES);
		add(btnVoltar);

		btnExcluir = new Botao("Excluir", 220, 500, 120 ,30);
		add(btnExcluir);
	}

	public void ouvinteBotoes() {
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomePacote = pacoteRef.getNomePacote();
				ci.getTodosOsPacotes().remove(ci.buscaPacote(nomePacote));
				ci.setPacoteRef(new Pacote());
				p.salvarCentral(ci);
				dispose();
				new TelaPacotes();
			}
		});
	}
}