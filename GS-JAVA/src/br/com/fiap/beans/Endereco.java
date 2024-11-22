package br.com.fiap.beans;

public class Endereco {

	private String logradouro;
	private int numero;
	private String estado;
	private String cep;

	public Endereco() {
		super();
	}

	public Endereco(String cep, String logradouro, int numero, String cidade, String estado) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", estado=" + estado + ", cep=" + cep
				+ "]";
	}

}
