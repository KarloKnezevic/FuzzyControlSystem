package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.*;

public class Gamma implements IConclusion {

	private IMeasuredData data;
	private double alfa;
	private double beta;

	public Gamma(IMeasuredData data, double alfa, double beta) {
		this.alfa = alfa;
		this.beta = beta;
		this.data = data.getCopy();
	}

	@Override
	public double computeConclusion(IInput sensor) {
		data.pickData(sensor);
		if (data.getX()<alfa) {
			return 0;
		} else if (data.getX()>=alfa && data.getX()<beta) {
			return (data.getX()-alfa)/(beta-alfa);
		} else {
			return 1;
		}
	}

	@Override
	//napravljena restrikcija: ukoliko je y=1, inv(L)=beta, ukoliko je y=0; inv(L)=alfa, inače normalan presijek
	public double computeInverseConclusion(double y) {
		data.setMemFun(y);
		double inv;
		if (y <= 0) {
			inv = alfa;
		} else if (y >= 1) {
			inv = beta;
		} else {
			inv = alfa + y*(beta-alfa);
		}

		//provjera, je li dobiveno rješenje u domeni
		if (inv >= data.getMinDomain() && inv <= data.getMaxDomain()) {
			data.setX(inv);
			return inv;
		} else {
			return 0.0;
		}
	}

	@Override
	public IMeasuredData getMeasuredData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "Γ[var:" + data.getDataName() + ", param:" + alfa + "," + beta + "]";
	}
}
