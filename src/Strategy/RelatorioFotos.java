package Strategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.ClienteDAOImpl;
import DAO.Fornecedor;
import ElementosGraficos.CheckBox;
import Model.Foto;

public class RelatorioFotos implements RelatorioStrategy{
	
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