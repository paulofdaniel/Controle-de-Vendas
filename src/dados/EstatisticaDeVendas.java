package dados;

import utilitarios.LtpUtil;

public class EstatisticaDeVendas {

	private String cpf;
	private String nome;
	private int numVendas;
	private double valorVendas;
	
	public EstatisticaDeVendas(String cpf, String nome, int numVendas, double valorVendas) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.numVendas = numVendas;
		this.valorVendas = valorVendas;
	}	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumVendas() {
		return numVendas;
	}
	public void setNumVendas(int numVendas) {
		this.numVendas = numVendas;
	}
	public double getValorVendas() {
		return valorVendas;
	}
	public void setValorVendas(double valorVendas) {
		this.valorVendas = valorVendas;
	}

	@Override
	public String toString() {
		return "\nNome          : " + nome + "\n"
			 + "Num de Vendas : " + numVendas + "\n"
			 + "Valor total R$: " + LtpUtil.formatarValor(valorVendas, "0,00")
			 + "\n         -------";
	}
	
}
