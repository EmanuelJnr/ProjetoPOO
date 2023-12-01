package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Persistencia {
	private XStream xstream = new XStream(new DomDriver());

	public Persistencia() {
		xstream.addPermission(AnyTypePermission.ANY);
	}
	public void salvarCentral(CentralDeInformacoes ci) {
		String xml = xstream.toXML(ci);
		File arquivo = new File("Party Helper.xml");

		try {
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.println(xml);
			gravar.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public CentralDeInformacoes recuperarCentral() {
		File arquivo = new File("Party Helper.xml");
		try {
			if (arquivo.exists()) {
				FileInputStream file = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xstream.fromXML(file);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new CentralDeInformacoes();
	}
}