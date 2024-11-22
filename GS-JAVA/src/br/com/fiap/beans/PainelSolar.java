package br.com.fiap.beans;

public class PainelSolar {

	private String tipo;
	private Double potencia;
	private Double eficiencia;
	private Double custo;

	public PainelSolar() {
		super();
	}

	public PainelSolar(String tipo, Double potencia, Double eficiencia, Double custo) {
		super();
		this.tipo = tipo;
		this.potencia = potencia;
		this.eficiencia = eficiencia;
		this.custo = custo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPotencia() {
		return potencia;
	}

	public void setPotencia(Double potencia) {
		this.potencia = potencia;
	}

	public Double getEficiencia() {
		return eficiencia;
	}

	public void setEficiencia(Double eficiencia) {
		this.eficiencia = eficiencia;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	@Override
	public String toString() {
		return "PainelSolar [tipo=" + tipo + ", potencia=" + potencia + ", eficiencia=" + eficiencia + ", custo="
				+ custo + "]";
	}

}
