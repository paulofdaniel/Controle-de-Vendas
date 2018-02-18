package dados;

import java.io.Serializable;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private double precoUnitario;
	private GregorianCalendar dataInclusao;
	private GregorianCalendar dataUltAlteracao;
	
	private static int cod = 1;	


	public Produto(String nome, double precoUnitario,GregorianCalendar dataInclusao,
			GregorianCalendar dataUltAlteracao) {
		super();
		this.codigo = cod++;
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.dataInclusao = dataInclusao;
		this.dataUltAlteracao = dataUltAlteracao;
	}

	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public GregorianCalendar getDataInclusao() {
		return dataInclusao;
	}
	public GregorianCalendar getDataUltAlteracao() {
		return dataUltAlteracao;
	}
	public void setDataUltAlteracao(GregorianCalendar dataUltAlteracao) {
		this.dataUltAlteracao = dataUltAlteracao;
	}
	public static void setCod(int cod) {
		Produto.cod = cod;
	}
	
	@Override
	public String toString() {
		return      "Codigo:              " + codigo 
				+ "\nNome:                " + nome 
				+ "\nPrecoUnitario R$:    " + LtpUtil.formatarValor(precoUnitario , "0,00")
				+ "\nInclusao:            " + LtpUtil.formatarData(dataInclusao, "dd/MM/yyyy") 
				+ "\nÚltima alteracao:    " + LtpUtil.formatarData(dataUltAlteracao, "dd/MM/yyyy") + "\n\n";
	}	
	
}
