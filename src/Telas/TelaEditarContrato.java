package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Interface.Botao;
import Interface.CheckBox;
import Interface.Fontes;
import Interface.Label;
import Interface.NomeTela;
import Logica.AlinhaCelulas;
import Logica.CentralDeInformacoes;
import Logica.Cliente;
import Logica.Fornecedor;
import Logica.Pacote;
import Logica.Persistencia;
import Ouvintes.OuvinteNovaTela;

public class TelaEditarContrato extends TelaPadrao{
	Persistencia p = new Persistencia();
	CentralDeInformacoes ci = p.recuperarCentral();
	private static final long serialVersionUID = 1L;
	private Botao btnAddComentario;
	private Botao btnConcluir;
	private CheckBox cbConcluido;
	private JTable tabela;
	private DefaultTableModel modelo;
	private Cliente clienteTemp = ci.getClienteTemp();
	private ArrayList<Fornecedor> todosFornecedoresClinte = new ArrayList<>();

	public TelaEditarContrato() {
		super("Editar Contrato");
		addBotoes();
		addLabels();
		addTabela();
		addCheckBox();
		verificacao();
		ouvintesBotoes();
		ouvinteCheckBox();
		setVisible(true);
	}

	public boolean adicionarFornecedor(Fornecedor fAdd) {
		for (Fornecedor f : todosFornecedoresClinte) {
			if(f.equals(fAdd))
				return false;
		}
		todosFornecedoresClinte.add(fAdd);
		return true;
	}
	public Fornecedor buscaFornecedor(String cpf_cnpj) {
		for(Fornecedor f : todosFornecedoresClinte) {
			if(cpf_cnpj.equals(f.getCPF_CNPJ()))
				return f;
		}
		return null;
	}
	public void addCheckBox() {
		cbConcluido = new CheckBox("Contrato Concluído", 75, 60, 150, 30);
		add(cbConcluido);
	}
	public void ouvinteCheckBox() {
		cbConcluido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Fornecedor f : todosFornecedoresClinte) {
					if(f.getComentarios() == null) {
						cbConcluido.setSelected(false);
						JOptionPane.showMessageDialog(null, "Você precisa adicionar comentários para todos os fornecedores!");
						return;
					}
				}
				cbConcluido.setSelected(true);
				cbConcluido.setEnabled(false);;
			}
		});
	}
	
	public void verificacao() {
		if(clienteTemp.getOrcamento().getTipo().equals("Concluído")) {
			btnAddComentario.setEnabled(false);
			btnConcluir.setEnabled(false);
			cbConcluido.setEnabled(false);
			cbConcluido.setSelected(true);
		}
	}

	public void addLabels() {
		Label titulo = new Label("Fornecedores",0,30,800,30);
		titulo.setHorizontalAlignment(Label.CENTER);
		titulo.setFont(Fontes.titulo());
		add(titulo);
	}

	public void addTabela() {
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Física/Jurídica");
		modelo.addColumn("Comentários");


		for (Fornecedor f : clienteTemp.getOrcamento().getFornecedores())
			adicionarFornecedor(f);

		for (Pacote p : clienteTemp.getOrcamento().getPacotes()) {
			for (Fornecedor f : p.getFornecedores()) {
				adicionarFornecedor(f);
			}
		}

		for(Fornecedor f : todosFornecedoresClinte) {
			Object[] linha = new Object[3];
			linha[0] = f.getNome();
			linha[1] = f.getCPF_CNPJ();
			linha[2] = f.getComentarios();
			modelo.addRow(linha);
		}
		tabela = new JTable(modelo);
		for(int i=0;i<tabela.getColumnCount();i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(AlinhaCelulas.alinhar());
		}
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
		tabela.setRowSorter(sorter);
		JScrollPane painelScrow = new JScrollPane(tabela);
		painelScrow.setBounds(20,100,745,350);
		add(painelScrow);
	}

	public void addBotoes() {
		btnAddComentario = new Botao("Adicionar Comentário", 560, 60, 150, 30);
		add(btnAddComentario);

		btnConcluir = new Botao("Concluir Edição", 140, 500, 120, 30);
		add(btnConcluir);

		Botao btnVoltar = new Botao("Voltar", 560, 500, 120, 30);
		OuvinteNovaTela.proximaTela(btnVoltar, this, NomeTela.TELA_ORCAMENTOS);
		add(btnVoltar);
	}

	public void ouvintesBotoes() {
		btnAddComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabela.getSelectedRow() != -1) {
					String cpf_cnpj = tabela.getValueAt(tabela.getSelectedRow(), 1).toString();
					if(todosFornecedoresClinte.get(tabela.getSelectedRow()).getComentarios() == null) {
						String comentario = JOptionPane.showInputDialog(null, "Digite um comentário para esse fornecedor:");
						if(comentario != null && !comentario.equals("")) {
							buscaFornecedor(cpf_cnpj).setComentarios(comentario);
						}
					}
				}
			}
		});

		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Fornecedor f : todosFornecedoresClinte) {
					if(f.getComentarios() == null) {
						JOptionPane.showMessageDialog(null, "Para concluir o contrato, todos os fornecedores devem ter comentários!");
						return;
					}
				}
				
				if(cbConcluido.isSelected()) {
					clienteTemp.getOrcamento().setTipo("Concluído");
					p.salvarCentral(ci);
					dispose();
					new TelaOrcamentos();
				}
				else {
					JOptionPane.showMessageDialog(null, "Para concluir o contrato, marque a caixinha!");
				}
			}
		});
	}
}