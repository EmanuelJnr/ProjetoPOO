package Strategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
import DAO.ClienteDAOImpl;
import DAO.Fornecedor;
import DAO.Pacote;
import ElementosGraficos.CheckBox;
import Model.Foto;

public class RelatorioCompleto implements RelatorioStrategy{

	public void gerarRelatorio(ClienteDAOImpl clienteTemp, ArrayList<Fornecedor> todosFornecedores,
			Map<String, CheckBox> map) throws DocumentException, IOException {

		Document documento = new Document(PageSize.A4);

		try {
			FileOutputStream os = new FileOutputStream("Relatório "+clienteTemp.getOrcamento().getNomeEvento()+".pdf");
			PdfWriter.getInstance(documento, os);
			documento.open();

			Image logoTipo = Image.getInstance("src/Controller/PartyHelperLogoTipoPDF.png");
			logoTipo.setAlignment(Paragraph.ALIGN_CENTER);
			logoTipo.scalePercent(45, 45);
			documento.add(Image.getInstance(logoTipo));

			documento.add(new Paragraph("\n"));
			
			Font fonteRelatorio = new Font(FontFamily.HELVETICA, 14.0f, Font.UNDERLINE);
			Paragraph titulo = new Paragraph("RELATÓRIO", fonteRelatorio);
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			documento.add(titulo);

			documento.add(new Paragraph("\n\n"));

			if(map.get("nomeEvento").isSelected()) {
				Paragraph nomeEvento = new Paragraph("• "+map.get("nomeEvento").getText()+": "+clienteTemp.getOrcamento().getNomeEvento());
				documento.add(nomeEvento);
				documento.add(new Paragraph("\n"));
			}

			if(map.get("nomeCliente").isSelected()) {
				Paragraph nomeCliente = new Paragraph("• "+map.get("nomeCliente").getText()+": "+clienteTemp.getNome());
				documento.add(nomeCliente);
				documento.add(new Paragraph("\n"));
			}

			if(map.get("emailCliente").isSelected()) {
				Paragraph emailCliente = new Paragraph("• "+map.get("emailCliente").getText()+": "+clienteTemp.getEmail());
				documento.add(emailCliente);
				documento.add(new Paragraph("\n"));
			}

			DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy HH:mm").toFormatter();
			if(map.get("dataHorario").isSelected()) {
				Paragraph dataHoraEvento = new Paragraph("• "+map.get("dataHorario").getText()+": "+clienteTemp.getOrcamento().getDataHora().format(parser)+" h");
				documento.add(dataHoraEvento);
				documento.add(new Paragraph("\n"));
			}

			if(map.get("local").isSelected()) {
				Paragraph localEvento = new Paragraph("• "+map.get("local").getText()+": "+clienteTemp.getOrcamento().getLocalEvento());
				documento.add(localEvento);
				documento.add(new Paragraph("\n"));
			}

			if(map.get("tamanho").isSelected()) {
				Paragraph QtdConvidados = new Paragraph("• "+"Quantidade de pessoas que participarão no Evento: "+clienteTemp.getOrcamento().getQtdConvidados());
				documento.add(QtdConvidados);
				documento.add(new Paragraph("\n"));
			}

			if(map.get("fornecedores").isSelected()) {
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
				Paragraph ValorTotal = new Paragraph("• "+map.get("valorEvento").getText()+": "+clienteTemp.getOrcamento().getValor()+" Reais", fonteValorTotal);
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

			documento.close();
		} catch (DocumentException de) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar relatório!");
		} catch (IOException io) {
			JOptionPane.showMessageDialog(null, "Erro no arquivo do relatório!");
		}
	}
}