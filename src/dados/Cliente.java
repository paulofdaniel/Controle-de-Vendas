package dados;

import java.io.Serializable;
import java.util.GregorianCalendar;
import utilitarios.LtpUtil;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private GregorianCalendar dataInclusao;
	private GregorianCalendar dataUltAlteracao;	
	
	public Cliente(String cpf, String nome, String telefone, String email,
			GregorianCalendar dataInclusao, GregorianCalendar dataUltAlteracao ) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataInclusao = dataInclusao;
		this.dataUltAlteracao = dataUltAlteracao;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	
	@Override
	public String toString() {
		return "CPF:              " + LtpUtil.formatarCPF(cpf) + "\n"
		   + "Nome:             " + nome + "\n"
		   + "Telefone:         " + telefone + "\n"
		   + "E-mail:           " + email + "\n"
		   + "Inclusao:         " + LtpUtil.formatarData(dataInclusao, "dd/MM/yyyy") + "\n"
		   + "Última alteração: " + LtpUtil.formatarData(dataUltAlteracao, "dd/MM/yyyy") +"\n\n";
	}
	
}
