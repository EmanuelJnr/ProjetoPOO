package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import org.apache.commons.mail.EmailException;
import Interface.Botao;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Cliente;
import Logica.Mensageiro;
import Logica.Persistencia;
import Logica.Reuniao;
import Logica.VerificaEmail;
import Ouvintes.OuvinteNovaTela;

public class TelaReunioes extends TelaPadrao {
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private Cliente cliente = ci.buscaCliente(ci.getClienteTemp().getCPF_CNPJ());
	private Botao btnATA;
	private Botao btnReuniao;
	private JFormattedTextField tfDataHora;
	private Mensageiro msg;
	private DefaultTableModel modelo;
	private DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
	private JTable tabela;

	public TelaReunioes() {
		super("Reuniões");
		addLabels();
		addTabela();
		addCampoDeTexto();
		addBotoes();
		verificacao();
		ouvinteBotoes();
		setVisible(true);
	}

	public void verificacao() {
		if(cliente.getOrcamento().getTipo().equals("Concluído")) {
			btnATA.setEnabled(false);
			btnReuniao.setEnabled(false);
			tfDataHora.setEnabled(false);
		}
	}

	public void addLabels() {
		Label titulo = new Label("REUNIÕES", 346, 30, 108, 30);
		titulo.setFont(Fontes.titulo());
		add(titulo);

		add(new Label("Data e hora:", 180, 450, 68, 20));
		add(new Label("Data e hora do Evento:", 500, 450, 150, 20));

		Label lbDataEvento = new Label(cliente.getOrcamento().getDataHora().format(parser), 635, 450, 105, 20);
		add(lbDataEvento);
	}

	public void addTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Data e Hora da Reunião");
		modelo.addColumn("ATA");

		for(Reuniao r: cliente.getOrcamento().getReunioes()) {
			Object[] linha = new Object[2];

			linha[0] = r.getDataHora().format(parser);
			linha[1] = r.getAta();

			modelo.addRow(linha);
		}

		tabela = new JTable(modelo);
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(modelo);
		tabela.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);

		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,80,745,350);
		add(painelScrow);
	}

	public void addCampoDeTexto() {
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/#### ##:##");
			tfDataHora = new JFormattedTextField(mascara);
			tfDataHora.setBounds(257, 446, 203, 30);
			add(tfDataHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void addBotoes() {
		boolean ata = true;
		if(cliente.getOrcamento().getReunioes().size() == 0)
			ata = false;
		btnATA = new Botao("ATA", 180, 500, 120, 30);
		btnATA.setEnabled(ata);
		add(btnATA);

		btnReuniao = new Botao("Marcar Reunião", 340, 500, 120, 30);
		add(btnReuniao);

		Botao btnVoltar = new Botao("Voltar", 500, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_ORCAMENTOS);
		add(btnVoltar);
	}

	public String tratarNome(String nomeCompleto) {
		String[] nomeSplit = nomeCompleto.split(" ");
		String resultado = "";
		for (String nome : nomeSplit) {
			String primeiraLetra = nome.substring(0, 1).toUpperCase();
			String restanteNome = nome.substring(1);
			String nomeCapitalizado = primeiraLetra + restanteNome;
			resultado += nomeCapitalizado+" ";
		}
		return resultado;
	}

	public void ouvinteBotoes() {
		btnReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tfDataHora.getText().length()==16) {
						String tempoI1 = tfDataHora.getText();
						LocalDateTime dataHora = LocalDateTime.parse(tempoI1, parser);

						if(dataHora.isBefore(LocalDateTime.now())) {//ela tentou marcar uma reunião para antes do instante de agora
							JOptionPane.showMessageDialog(null, "Essa data já ocorreu!");
							return;
						}

						for(Reuniao r : ci.getTodasAsReunioes()) {//percorre todas as reuniões já marcadas no sistema
							if(dataHora.isAfter(r.getDataHora()) && dataHora.isBefore(r.getDataHora().plusMinutes(30))) {//verifica se a reunião que está tentando marcar está no intervalo de 30 minutos depois de uma reunião já marcada no sistema.
								JOptionPane.showMessageDialog(null, "Tem uma reunião marcada para: "+r.getDataHora().format(parser)+" horas, tente marcar para 30 minutos depois!");
								return;
							}
						}

						for(Reuniao r : ci.getTodasAsReunioes()) {//percorre todas as reuniões já marcadas no sistema
							if(r.getDataHora().equals(dataHora)) {//verifica se a reunião que está tentando marcar é igual a uma reunião já marcada no sistema.
								JOptionPane.showMessageDialog(null, "Tem uma reunião marcada para exatamente essa hora!");
								return;
							}
						}

						for(Reuniao r : ci.getTodasAsReunioes()) {//percorre todas as reuniões já marcadas no sistema
							if(dataHora.isBefore(r.getDataHora()) && dataHora.isAfter(r.getDataHora().minusMinutes(30))) {//verifica se a reunião que está tentando marcar está no intervalo de 30 minutos antes de uma reunião já marcada no sistema.
								JOptionPane.showMessageDialog(null, "Tem uma reunião marcada para: "+r.getDataHora().format(parser)+" horas, tente marcar para 30 minutos antes!");
								return;
							}
						}


						if(dataHora.isAfter(cliente.getOrcamento().getDataHora())) {//ela tentou marcar uma reunião para depois da data e hora do evento
							JOptionPane.showMessageDialog(null, "Não pode marcar uma reunião para depois do evento!");
							return;
						}

						try {
							msg = new Mensageiro();
						} catch (EmailException emailInvalido) {
							JOptionPane.showMessageDialog(null, "O e-mail do programa não está mais válido!");
						}
						try {
							String corpoEmail = "\nEu, "+tratarNome(ci.getAdmin().getNome())+", Cerimonialista e organizador(a) de eventos, venho atravéz deste lhe convidar para a reunião que "
									+ "será realizada na dada: "+tfDataHora.getText()+" horas, que falaremos sobre o evento que está marcado para o dia: "+cliente.getOrcamento().getDataHora().format(parser)+" horas.";

							msg.enviarEmail(cliente.getEmail(), "Reunião Party Helper", corpoEmail);

							Reuniao reuniao = new Reuniao(dataHora);
							ci.getTodasAsReunioes().add(reuniao);
							cliente.getOrcamento().getReunioes().add(reuniao);
							p.salvarCentral(ci);

							Object[] linha = new Object[2];
							linha[0] = reuniao.getDataHora().format(parser);
							linha[1] = reuniao.getAta();
							modelo.addRow(linha);
							btnATA.setEnabled(true);
							JOptionPane.showMessageDialog(null, "Reunião marcada, um e-mail para o cliente foi enviado!");

						} catch (EmailException msgNEnviada) {
							String novoEmail = JOptionPane.showInputDialog(null, "Não foi possível enviar a mensagem para o e-mail do cliente, deseja cadastrar um novo e-mail para o cliente?");
							if(novoEmail==null)
								return;

							boolean flag = true;
							while(flag) {
								if(VerificaEmail.isValid(novoEmail)) {
									cliente.setEmail(novoEmail);
									p.salvarCentral(ci);
									flag = false;
								}
								else {
									JOptionPane.showMessageDialog(null, "E-mail inválido!");
								}
							}
						}
					}
				}catch (DateTimeParseException dtpe) {
					JOptionPane.showMessageDialog(null, "Data e hora inválida!");
					tfDataHora.setText("");
					return;
				}
			}
		});

		btnATA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {

					String dataSelecionada = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
					LocalDateTime dataHora = LocalDateTime.parse(dataSelecionada, parser);

					for (Reuniao r : cliente.getOrcamento().getReunioes()) {
						if(r.getDataHora().equals(dataHora)) {
							ci.setReuniaoTemp(r);
							p.salvarCentral(ci);
							dispose();
							new TelaATAReuniao();
							return;
						}
					}
				}
			}
		});
	}
}