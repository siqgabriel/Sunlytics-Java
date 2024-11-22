package br.com.fiap.beans;

public class Estado {

	private String nome;
	private Double tarifaMedia;
	private Double irradiacaoSolarMedia;

	public Estado() {
		super();
	}

	public Estado(String nome, Double tarifaMedia, Double irradiacaoSolarMedia) {
		super();
		this.nome = nome;
		this.tarifaMedia = tarifaMedia;
		this.irradiacaoSolarMedia = irradiacaoSolarMedia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getTarifaMedia() {
		return tarifaMedia;
	}

	public void setTarifaMedia(Double tarifaMedia) {
		this.tarifaMedia = tarifaMedia;
	}

	public Double getIrradiacaoSolarMedia() {
		return irradiacaoSolarMedia;
	}

	public void setIrradiacaoSolarMedia(Double irradiacaoSolarMedia) {
		this.irradiacaoSolarMedia = irradiacaoSolarMedia;
	}

	@Override
	public String toString() {
		return "Estado [nome=" + nome + ", tarifaMedia=" + tarifaMedia + ", irradiacaoSolarMedia="
				+ irradiacaoSolarMedia + "]";
	}

}
