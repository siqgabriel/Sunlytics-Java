package br.com.fiap.beans;

public class ConsumoEnergetico {

	private Double consumoMensalKwh;
	private Double tarifaPorKwh;
	private Double gastoMensal;

	public ConsumoEnergetico() {
		super();
	}

	public ConsumoEnergetico(Double consumoMensalKwh, Double tarifaPorKwh, Double gastoMensal) {
		super();
		this.consumoMensalKwh = consumoMensalKwh;
		this.tarifaPorKwh = tarifaPorKwh;
		this.gastoMensal = gastoMensal;
	}

	public Double getConsumoMensalKwh() {
		return consumoMensalKwh;
	}

	public void setConsumoMensalKwh(Double consumoMensalKwh) {
		this.consumoMensalKwh = consumoMensalKwh;
	}

	public Double getTarifaPorKwh() {
		return tarifaPorKwh;
	}

	public void setTarifaPorKwh(Double tarifaPorKwh) {
		this.tarifaPorKwh = tarifaPorKwh;
	}

	public Double getGastoMensal() {
		return gastoMensal;
	}

	public void setGastoMensal(Double gastoMensal) {
		this.gastoMensal = gastoMensal;
	}

	@Override
	public String toString() {
		return "ConsumoEnergetico [consumoMensalKwh=" + consumoMensalKwh + ", tarifaPorKwh=" + tarifaPorKwh
				+ ", gastoMensal=" + gastoMensal + "]";
	}

}
