package br.com.fiap.beans;

public class Comparacao {

	private double economiaRelativa;
	private double reducaoCo2Relativa;

	public Comparacao() {
		super();
	}

	public Comparacao(double economiaRelativa, double reducaoCo2Relativa) {
		super();
		this.economiaRelativa = economiaRelativa;
		this.reducaoCo2Relativa = reducaoCo2Relativa;
	}

	public double getEconomiaRelativa() {
		return economiaRelativa;
	}

	public void setEconomiaRelativa(double economiaRelativa) {
		this.economiaRelativa = economiaRelativa;
	}

	public double getReducaoCo2Relativa() {
		return reducaoCo2Relativa;
	}

	public void setReducaoCo2Relativa(double reducaoCo2Relativa) {
		this.reducaoCo2Relativa = reducaoCo2Relativa;
	}

	@Override
	public String toString() {
		return "Comparacao [economiaRelativa=" + economiaRelativa + ", reducaoCo2Relativa=" + reducaoCo2Relativa + "]";
	}

}
