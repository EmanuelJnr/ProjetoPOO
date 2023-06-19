package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
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
	private CheckBox cbDataHorario;
	private CheckBox cbLocal;
	private CheckBox cbTamanho;
	private CheckBox cbFornecedores;
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

		try {
			FileOutputStream os = new FileOutputStream("Relatório "+clienteTemp.getOrcamento().getNomeEvento()+".pdf");
			PdfWriter.getInstance(documento, os);
			documento.open();

			Image logoTipo = Image.getInstance("src/Imagens/PartyHelper.png");
			logoTipo.scalePercent(45, 45);
			documento.add(Image.getInstance(logoTipo));

			if(cbTodos.isSelected()) {
				cbNomeEvento.setSelected(true);
				cbDataHorario.setSelected(true);
				cbLocal.setSelected(true);
				cbTamanho.setSelected(true);
				cbFornecedores.setSelected(true);
			}

			if(cbNomeEvento.isSelected()) {
				Paragraph nomeEvento = new Paragraph(cbNomeEvento.getText()+": "+clienteTemp.getOrcamento().getNomeEvento());
				documento.add(nomeEvento);
				documento.add(new Paragraph("\n"));
			}

			DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
			if(cbDataHorario.isSelected()) {
				Paragraph dataHoraEvento = new Paragraph(cbDataHorario.getText()+": "+clienteTemp.getOrcamento().getDataHora().format(parser));
				documento.add(dataHoraEvento);
				documento.add(new Paragraph("\n"));
			}

			if(cbLocal.isSelected()) {
				Paragraph localEvento = new Paragraph(cbLocal.getText()+": "+clienteTemp.getOrcamento().getLocalEvento());
				documento.add(localEvento);
				documento.add(new Paragraph("\n"));
			}

			if(cbTamanho.isSelected()) {
				Paragraph QtdConvidados = new Paragraph("Quantidade de pessoas que participarão no Evento: "+clienteTemp.getOrcamento().getQtdConvidados());
				documento.add(QtdConvidados);
				documento.add(new Paragraph("\n"));
			}

			if(cbFornecedores.isSelected()) {
				documento.add(new Paragraph("A lista de fornecedores que participarão do evento:\n\n"));
				for (Fornecedor f : todosFornecedores) {
					Paragraph nomeFornecedor = new Paragraph(f.getNome());
					documento.add(nomeFornecedor);
					documento.add(new Paragraph("\n"));
				}
			}

			int indentation = 0;
			for (Foto f : clienteTemp.getOrcamento().getFotos()) {
				Image i = Image.getInstance(f.getCaminhoDaFoto());
				float scaler = ((documento.getPageSize().getWidth() - documento.leftMargin()
						- documento.rightMargin() - indentation) / i.getWidth()) * 100;
				i.scalePercent(scaler);
				documento.add(i);
			}

			documento.close();
		} catch (DocumentException de) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
		} catch (IOException io) {
			JOptionPane.showMessageDialog(null, "Erro no arquivo do relatório!");
		}
	}

	public void addLabels() {
		Label lbTitulo = new Label("GERAR RELATÓRIO", 280, 20, 240, 20);
		lbTitulo.setFont(Fontes.titulo());
		add(lbTitulo);
	}

	public void addCheckBoxs() {
		cbNomeEvento = new CheckBox("Nome do evento", 190, 70, 118, 20);
		add(cbNomeEvento);

		cbDataHorario = new CheckBox("Data e horário do evento", 190, 100, 163, 20);
		add(cbDataHorario);

		cbLocal = new CheckBox("Local onde o evento acontecerá", 190, 130, 203, 20);
		add(cbLocal);

		cbTamanho = new CheckBox("Tamanho do evento", 190, 160, 137, 20);
		add(cbTamanho);

		cbFornecedores = new CheckBox("Os fornecedores que participarão do evento", 190, 190, 267, 20);
		add(cbFornecedores);

		cbTodos = new CheckBox("Todos", 190, 220, 59, 20);
		add(cbTodos);
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
				if(cbNomeEvento.isSelected() || cbDataHorario.isSelected() || cbLocal.isSelected()
						|| cbTamanho.isSelected() || cbFornecedores.isSelected() || cbTodos.isSelected()) {
					gerarRelatorio();
					JOptionPane.showMessageDialog(null, "Relatório criado!");
					dispose();
					new TelaOrcamentos();
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione pelo menos uma caixinha para gerar o relatório");
					return;
				}
			}
		});
	}
}