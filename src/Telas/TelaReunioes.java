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

	public void ouvinteBotoes() {
		btnReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tfDataHora.getText().length()==16) {
						String tempoI1 = tfDataHora.getText();
						LocalDateTime dataHora = LocalDateTime.parse(tempoI1, parser);

						if(dataHora.isBefore(LocalDateTime.now())) {
							JOptionPane.showMessageDialog(null, "Essa data já ocorreu!");
							tfDataHora.setText("");
							return;
						}
						if(dataHora.isAfter(cliente.getOrcamento().getDataHora())) {
							JOptionPane.showMessageDialog(null, "Não pode marcar uma reunião para depois do evento!");
							tfDataHora.setText("");
							return;
						}
						for(Reuniao r : ci.getTodasAsReunioes()) {
							if(r.getDataHora().equals(dataHora)) {
								JOptionPane.showMessageDialog(null, "Já existe uma reunião marcada com essa data e hora!");
								return;
							}
						}

						try {
							msg = new Mensageiro();
						} catch (EmailException emailInvalido) {
							JOptionPane.showMessageDialog(null, "O e-mail do programa não está mais válido!");
						}
						try {
							msg.enviarEmail(cliente.getEmail(), "Reunião Party Helper", "Uma reunião com a Cerimonialista foi marcada para: "+tfDataHora.getText());

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
							if(r.getAta() == null) {
								ci.setReuniaoTemp(r);
								p.salvarCentral(ci);
								dispose();
								new TelaATAReuniao();
								return;
							}
						}
					}
				}
			}
		});
	}
}