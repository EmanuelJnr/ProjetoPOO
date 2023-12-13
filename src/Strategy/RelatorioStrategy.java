package Strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.itextpdf.text.DocumentException;
import DAO.ClienteDAOImpl;
import DAO.Fornecedor;
import ElementosGraficos.CheckBox;

public interface RelatorioStrategy {
	void gerarRelatorio(ClienteDAOImpl clienteTemp, ArrayList<Fornecedor> todosFornecedores,
			Map<String, CheckBox> map) throws DocumentException, IOException;
}