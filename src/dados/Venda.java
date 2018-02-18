package dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

public class Venda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numVenda;
	private Cliente cliente;
	private GregorianCalendar dataVenda;
	private ArrayList<ItemVenda> vendaItens;
	private double totalVenda;
	
	private static int cod = 1;

	public Venda(Cliente cliente, GregorianCalendar dataVenda, ArrayList<ItemVenda> vendaItens, double totalVenda) {
		super();
		this.numVenda = cod++;
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.vendaItens = vendaItens;
		this.totalVenda = totalVenda;
	}

	
	public int getNumVenda() {
		return numVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public GregorianCalendar getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(GregorianCalendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public ArrayList<ItemVenda> getVendaItens() {
		return vendaItens;
	}

	public void setVendaItens(ArrayList<ItemVenda> vendaItens) {
		this.vendaItens = vendaItens;
	}
	public static void setCod(int cod) {
		Venda.cod = cod;
	}
	public double getTotalVenda() {
		return totalVenda;
	}
	
	@Override
	public String toString() {
		return "===================================================================================="
			+"\nNúmero da venda: " + numVenda
			+"\nRealizada em   : " + LtpUtil.formatarData(dataVenda, "dd/MM/yyyy")
			+"\n\n-DADOS DO CLIENTE-\n\n" + cliente			
			+"\n-ITENS DA VENDA-\n\n"
			+ "Produto \t\t\t\tVal Un R$ \t\tQtd \tVal Total R$\n" + vendaItens.toString();
	}	
	
}
