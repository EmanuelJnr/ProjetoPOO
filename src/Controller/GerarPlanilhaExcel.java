package Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import DAO.ClienteDAOImpl;
import DAO.Fornecedor;
import DAO.Pacote;

public class GerarPlanilhaExcel {
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	ClienteDAOImpl clienteTemp = ci.getClienteTemp();
	ArrayList<Fornecedor> todosFornecedores = new ArrayList<>();
	ArrayList<Pacote> pacotes = new ArrayList<>();

	public GerarPlanilhaExcel() {
		for (Pacote p : clienteTemp.getOrcamento().getPacotes()) {
			pacotes.add(p);
		}
		preencheFornecedores();
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

	public void gerarPlanilhaExcel() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet orcamento = workbook.createSheet("Orçamento");
		int rownum = 0;
		int valor = 0;

		Row row = orcamento.createRow(rownum++);
		Cell titulo = row.createCell(0);
		titulo.setCellValue("FORNECEDOR/PACOTE");
		Cell cellValor = row.createCell(1);
		cellValor.setCellValue("VALOR");

		if(todosFornecedores.size() != 0) {
			for (Fornecedor f : todosFornecedores) {               
				int cellnum = 0;
				Row row2 = orcamento.createRow(rownum++);
				Cell cellNome = row2.createCell(cellnum++);
				cellNome.setCellValue(f.getNome());
				Cell cellValorFornecedor = row2.createCell(cellnum++);
				cellValorFornecedor.setCellValue(f.getValor());
				valor += f.getValor();
			}  	
		}
		if(pacotes.size()!=0) {
			row = orcamento.createRow(rownum++);
			for (Pacote p : pacotes) {
				int cellnum = 0;
				row = orcamento.createRow(rownum++);
				Cell cellNome = row.createCell(cellnum++);
				cellNome.setCellValue(p.getNomePacote());
				Cell cellValorFornecedor = row.createCell(cellnum);
				cellValorFornecedor.setCellValue(p.getValor());
				valor += p.getValor();
			}
		}
		row = orcamento.createRow(rownum++);

		row = orcamento.createRow(rownum++);
		Cell cellTextoValor = row.createCell(0);
		cellTextoValor.setCellValue("Valor total:");
		Cell cellValorFornecedor = row.createCell(1);
		cellValorFornecedor.setCellValue(valor);

		try {     
			FileOutputStream out = new FileOutputStream(new File("Finanças "+clienteTemp.getOrcamento().getNomeEvento()+".xls"));
			workbook.write(out);
			out.close();
			workbook.close();
			JOptionPane.showMessageDialog(null,"Arquivo Excel criado com sucesso!");

			Desktop desktop = Desktop.getDesktop();
			File file = new File("Finanças "+clienteTemp.getOrcamento().getNomeEvento()+".xls");
			try {
				desktop.open(file);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,"Não conseguiu abrir o programa do Excel, mas a planilha foi salva no Eclipse!");
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Arquivo não encontrado!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro na edição!!");
		}
	}

}
