package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Funcionalidades.Fontes;
import Interface.Botao;
import Interface.CampoDeTexto;
import Interface.Label;
import Interface.RadioButton;
import Ouvintes.OuvinteNovaTela;

public class TelaFiltrarOrcamentos extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	private Botao btnFiltrar;
	private CampoDeTexto tfNome;
	private CampoDeTexto tfData;
	private RadioButton rbContrato;
	private RadioButton rbOrcamento;

	public TelaFiltrarOrcamentos() {
		super("Filtrar Orçamentos");
		addLabels();
		addTabela();
		addBotao();
		addCampoDeTexto();
		addRadioButton();
		setVisible(true);
	}

	public void addCampoDeTexto() {
		tfNome = new CampoDeTexto("", 60, 500, 140, 30);
		add(tfNome);

		tfData = new CampoDeTexto("", 330, 500, 140, 30);
		add(tfData);
	}

	public void addRadioButton() {
		rbContrato = new RadioButton("Contrato", 220, 494, 73, 20);
		rbOrcamento = new RadioButton("Orçamento", 220, 514, 86, 20);
		add(rbContrato);
		add(rbOrcamento);

		ButtonGroup grupoDoc = new ButtonGroup();
		grupoDoc.add(rbContrato);
		grupoDoc.add(rbOrcamento);
	}

	public void addLabels() {
		Label titulo = new Label("ORÇAMENTOS", 335, 30, 130, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Filtros:", 20, 465, 120, 30));
		add(new Label("Nome", 20, 500, 120, 30));
		add(new Label("Data", 385, 470, 120, 30));
	}

	public void addBotao() {
		Botao btnVoltar = new Botao("Voltar", 640, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, "TelaOrcamentos");
		add(btnVoltar);

		btnFiltrar = new Botao("Filtrar", 500, 500, 120, 30);
		add(btnFiltrar);
	}

	public void ouvinteBtnFiltrar() {
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Permanece na mesma janela mas filtra os dados,
				//de acordo com as entradas.
			}
		});
	}

	public void addTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Data e hora");
		modelo.addColumn("Contrato/Orçamento");

		// TODO falta fazer a adicão do banco de dados com as informações dos atributos.

		/** TODO Adicionar na lista os devidos atributos. 
		for() {
			Object[] linha = new Object[3];

			linha[0] = //Nome
			linha[1] = //Data e hora
			linha[2] = //Contrato/Orçamento

			modelo.addRow(linha);
		}
		 */	
		JTable tabela = new JTable(modelo);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,100,745,350);
		add(painelScrow);
	}
}