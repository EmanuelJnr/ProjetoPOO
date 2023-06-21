package Telas;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Interface.Botao;
import Interface.CheckBox;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Cliente;
import Logica.Fornecedor;
import Logica.Foto;
import Logica.Pacote;
import Logica.Persistencia;
import Ouvintes.OuvinteNovaTela;

public class TelaGerarRelatorio extends TelaPadrao{
	private static final long serialVersionUID = 1L;
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private CheckBox cbNomeEvento;
	private CheckBox cbNomeCliente;
	private CheckBox cbEmailCliente;
	private CheckBox cbDataHorario;
	private CheckBox cbLocal;
	private CheckBox cbTamanho;
	private CheckBox cbFornecedores;
	private CheckBox cbValorEvento;
	private CheckBox cbTodos;
	private Botao btnAddFoto;
	private Botao btnDelFoto;
	private Botao btnConfirmar;
	private Cliente clienteTemp = ci.getClienteTemp();
	private ArrayList<Fornecedor> todosFornecedores = new ArrayList<>();
	private DefaultTableModel modelo;
	private JTable tabela;

	public TelaGerarRelatorio() {
		super("Gerar Relatório");
		addLabels();
		addCheckBoxs();
		addTabela();
		addBotoes();
		ouvinteBotoes();
		ouvinteCBTodos();
		setVisible(true);
	}

	public boolean adicionarFornecedor(Fornecedor fAdd) {
		for (Fornecedor f : todosFornecedores) {
			if(f.equals(fAdd))
				return false;
		}
		todosFornecedores.add(fAdd);
		return true;
	}

	public void preencheFornecedores() {
		for (Fornecedor f : clienteTemp.getOrcamento().getFornecedores())
			adicionarFornecedor(f);

		for (Pacote p : clienteTemp.getOrcamento().getPacotes()) {
			for (Fornecedor f : p.getFornecedores())
				adicionarFornecedor(f);
		}
	}

	public void gerarRelatorio() {
		preencheFornecedores();
		Document documento = new Document(PageSize.A4);
		boolean nenhumaMarcada = true;

		try {
			FileOutputStream os = new FileOutputStream("Relatório "+clienteTemp.getOrcamento().getNomeEvento()+".pdf");
			PdfWriter.getInstance(documento, os);
			documento.open();

			Image logoTipo = Image.getInstance("src/Imagens/PartyHelperLogoTipoPDF.png");
			logoTipo.setAlignment(Paragraph.ALIGN_CENTER);
			logoTipo.scalePercent(45, 45);
			documento.add(Image.getInstance(logoTipo));

			documento.add(new Paragraph("\n"));

			List<CheckBox> todosCB = Arrays.asList(cbNomeEvento, cbNomeCliente, cbEmailCliente, cbDataHorario, cbLocal, cbTamanho,
					cbValorEvento, cbFornecedores, cbTodos);


			for (CheckBox cb : todosCB) {
				if(cb.isSelected()) {
					nenhumaMarcada = false;
				}
			}

			if(!nenhumaMarcada) {
				Font fonteRelatorio = new Font(FontFamily.HELVETICA, 14.0f, Font.UNDERLINE);
				Paragraph titulo = new Paragraph("RELATÓRIO", fonteRelatorio);
				titulo.setAlignment(Paragraph.ALIGN_CENTER);
				documento.add(titulo);

				documento.add(new Paragraph("\n\n"));

				if(cbNomeEvento.isSelected()) {
					Paragraph nomeEvento = new Paragraph("• "+cbNomeEvento.getText()+": "+clienteTemp.getOrcamento().getNomeEvento());
					documento.add(nomeEvento);
					documento.add(new Paragraph("\n"));
				}

				if(cbNomeCliente.isSelected()) {
					Paragraph nomeCliente = new Paragraph("• "+cbNomeCliente.getText()+": "+clienteTemp.getNome());
					documento.add(nomeCliente);
					documento.add(new Paragraph("\n"));
				}

				if(cbEmailCliente.isSelected()) {
					Paragraph emailCliente = new Paragraph("• "+cbEmailCliente.getText()+": "+clienteTemp.getEmail());
					documento.add(emailCliente);
					documento.add(new Paragraph("\n"));
				}

				DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
				if(cbDataHorario.isSelected()) {
					Paragraph dataHoraEvento = new Paragraph("• "+cbDataHorario.getText()+": "+clienteTemp.getOrcamento().getDataHora().format(parser)+" h");
					documento.add(dataHoraEvento);
					documento.add(new Paragraph("\n"));
				}

				if(cbLocal.isSelected()) {
					Paragraph localEvento = new Paragraph("• "+cbLocal.getText()+": "+clienteTemp.getOrcamento().getLocalEvento());
					documento.add(localEvento);
					documento.add(new Paragraph("\n"));
				}

				if(cbTamanho.isSelected()) {
					Paragraph QtdConvidados = new Paragraph("• "+"Quantidade de pessoas que participarão no Evento: "+clienteTemp.getOrcamento().getQtdConvidados());
					documento.add(QtdConvidados);
					documento.add(new Paragraph("\n"));
				}

				if(cbFornecedores.isSelected()) {
					documento.add(new Paragraph("• A lista de Pacotes:\n"));
					for (Pacote p : clienteTemp.getOrcamento().getPacotes()) {
						Paragraph nomeFornecedor = new Paragraph("- "+p.getNomePacote()+", Valor a ser pago: "+p.getValor()+" Reais\n");
						documento.add(nomeFornecedor);
					}
					documento.add(new Paragraph("\n"));

					documento.add(new Paragraph("• A lista de fornecedores que participarão do evento:\n"));
					for (Fornecedor f : todosFornecedores) {
						Paragraph nomeFornecedor = new Paragraph("- "+f.getNome()+", Valor a ser pago: "+f.getValor()+" Reais\n");
						documento.add(nomeFornecedor);
					}
					documento.add(new Paragraph("\n"));

					Font fonteValorTotal = new Font(FontFamily.HELVETICA, 12.0f, Font.UNDERLINE);
					Paragraph ValorTotal = new Paragraph("• "+cbValorEvento.getText()+": "+clienteTemp.getOrcamento().getValor()+" Reais", fonteValorTotal);
					titulo.setAlignment(Paragraph.ALIGN_CENTER);
					documento.add(ValorTotal);
				}

				int indentation = 0;
				for (Foto f : clienteTemp.getOrcamento().getFotos()) {
					Image i = Image.getInstance(f.getCaminhoDaFoto());
					float scaler = ((documento.getPageSize().getWidth() - documento.leftMargin()
							- documento.rightMargin() - indentation) / i.getWidth()) * 100;
					i.scalePercent(scaler);
					documento.add(i);
				}
			}

			else
				documento.add(new Paragraph("Não consta"));

			documento.close();
		} catch (DocumentException de) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
		} catch (IOException io) {
			JOptionPane.showMessageDialog(null, "Erro no arquivo do relatório!");
		}
	}

	public void addLabels() {
		Label lbTitulo = new Label("GERAR RELATÓRIO", 280, 10, 240, 25);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);
	}

	public void addCheckBoxs() {
		cbNomeEvento = new CheckBox("Nome do Evento", 190, 40, 118, 20);
		add(cbNomeEvento);

		cbNomeCliente = new CheckBox("Nome do Cliente", 190, 70, 118, 20);
		add(cbNomeCliente);

		cbEmailCliente = new CheckBox("E-mail do Cliente", 190, 100, 118, 20);
		add(cbEmailCliente);

		cbDataHorario = new CheckBox("Data e hora do Evento", 190, 130, 163, 20);
		add(cbDataHorario);

		cbLocal = new CheckBox("Local do Evento", 190, 160, 203, 20);
		add(cbLocal);

		cbTamanho = new CheckBox("Tamanho do Evento", 190, 190, 137, 20);
		add(cbTamanho);

		cbFornecedores = new CheckBox("Os fornecedores que participarão do evento", 190, 220, 267, 20);
		add(cbFornecedores);

		cbValorEvento = new CheckBox("Valor total do Evento", 190, 250, 170, 20);
		add(cbValorEvento);

		cbTodos = new CheckBox("Todos", 190, 280, 59, 20);
		add(cbTodos);
	}

	public void ouvinteCBTodos() {
		cbTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<CheckBox> todosCB = Arrays.asList(cbNomeEvento, cbNomeCliente, cbEmailCliente, cbDataHorario, cbLocal, cbTamanho,
						cbValorEvento, cbFornecedores);
				if(e.getSource() == cbTodos) {
					if(cbTodos.isSelected()) {
						for (CheckBox cb : todosCB)
							cb.setSelected(true);
					}
					else if(!cbTodos.isSelected()) {
						for(CheckBox cb : todosCB)
							cb.setSelected(false);
					}
				}
			}
		});
	}

	public void addTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome da Foto");

		for(Foto f : clienteTemp.getOrcamento().getFotos()) {
			Object[] linha = new Object[1];

			String[] nomeFoto = f.getCaminhoDaFoto().split("/");
			linha[0] = nomeFoto[nomeFoto.length-1];

			modelo.addRow(linha);
		}
		tabela = new JTable(modelo);
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabela.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20, 310, 745, 200);
		add(painelScrow);
	}

	public void addBotoes() {
		btnAddFoto = new Botao("Adicionar Foto", 20, 275, 150, 30);
		add(btnAddFoto);

		btnDelFoto = new Botao("Remover Foto", 615, 275, 150, 30);
		add(btnDelFoto);

		btnConfirmar = new Botao("Confirmar", 240, 520, 120, 30);
		add(btnConfirmar);

		Botao btnVoltar = new Botao("Voltar", 440, 520, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_ORCAMENTOS);
		add(btnVoltar);
	}

	public void ouvinteBotoes() {
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();

				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Todos os arquivos de imagem", "jpg", "png", "jpeg", "bmp", "gif");

				jFileChooser.setAcceptAllFileFilterUsed(false);
				jFileChooser.addChoosableFileFilter(filtro);

				int resposta = jFileChooser.showOpenDialog(null);

				if(resposta == JFileChooser.APPROVE_OPTION) {
					File arquivoSelecionado = jFileChooser.getSelectedFile();
					clienteTemp.getOrcamento().adicionarFoto(new Foto(arquivoSelecionado.getAbsolutePath()));

					Object[] linha = new Object[1];
					String[] nomeFoto = arquivoSelecionado.getAbsolutePath().split("\\\\");
					linha[0] = nomeFoto[nomeFoto.length-1];
					modelo.addRow(linha);
				}
			}
		});

		btnDelFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String nomeFoto = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
					modelo.removeRow(tabela.getSelectedRow());
					clienteTemp.getOrcamento().getFotos().remove(clienteTemp.getOrcamento().buscaFoto(nomeFoto));
				}
			}
		});

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarRelatorio();
				JOptionPane.showMessageDialog(null, "Relatório criado!");

				Desktop desktop = Desktop.getDesktop();
				File file = new File("Relatório "+clienteTemp.getOrcamento().getNomeEvento()+".pdf");
				try {
					desktop.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				dispose();
				new TelaOrcamentos();
			}
		});
	}
}