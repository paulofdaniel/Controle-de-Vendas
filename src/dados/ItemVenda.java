package dados;

import java.io.Serializable;

import utilitarios.LtpUtil;

public class ItemVenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private double precoUnitario;
	private double quantVenda;
	private double valorVenda;
	
	
	public ItemVenda(Produto produto, double precoUnitario, double quantVenda, double valorVenda) {
		super();
		this.produto = produto;
		this.precoUnitario = precoUnitario;
		this.quantVenda = quantVenda;
		this.valorVenda = valorVenda;
	}
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public double getQuantVenda() {
		return quantVenda;
	}

	public void setQuantVenda(double quantVenda) {
		this.quantVenda = quantVenda;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	@Override
	public String toString() {
		return produto.getNome() + "\t\t\t\t" + LtpUtil.formatarValor(precoUnitario, "0,00") + "\t\t\t" + quantVenda + "\t" + LtpUtil.formatarValor(valorVenda, "0,00") + "\n";
	}
	
}
