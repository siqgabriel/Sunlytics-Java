package br.com.fiap.beans;

public class Cadastro {

	private String nome;
	private Endereco endereco;
	private String email;
	private String senha;

	public Cadastro() {
		super();
	}

	public Cadastro(String nome, Endereco endereco, String email, String senha) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Cadastro [nome=" + nome + ", endereco=" + endereco + ", email=" + email + ", senha=" + senha + "]";
	}

}
