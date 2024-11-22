package br.com.fiap.beans;

public class Calculo {

	private double economiaKwh;
	private double economiaConta;
	private double reducaoCo2;

	public Calculo() {
		super();
	}

	public Calculo(double economiaKwh, double economiaConta, double reducaoCo2) {
		super();
		this.economiaKwh = economiaKwh;
		this.economiaConta = economiaConta;
		this.reducaoCo2 = reducaoCo2;
	}

	public double getEconomiaKwh() {
		return economiaKwh;
	}

	public void setEconomiaKwh(double economiaKwh) {
		this.economiaKwh = economiaKwh;
	}

	public double getEconomiaConta() {
		return economiaConta;
	}

	public void setEconomiaConta(double economiaConta) {
		this.economiaConta = economiaConta;
	}

	public double getReducaoCo2() {
		return reducaoCo2;
	}

	public void setReducaoCo2(double reducaoCo2) {
		this.reducaoCo2 = reducaoCo2;
	}

	@Override
	public String toString() {
		return "Calculo [economiaKwh=" + economiaKwh + ", economiaConta=" + economiaConta + ", reducaoCo2=" + reducaoCo2
				+ "]";
	}

}
