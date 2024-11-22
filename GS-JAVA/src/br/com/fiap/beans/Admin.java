package br.com.fiap.beans;

public class Admin {

	private String nome;
	private String email;
	private String senha;

	public Admin() {
		super();
	}

	public Admin(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		return "Admin [nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}

}
