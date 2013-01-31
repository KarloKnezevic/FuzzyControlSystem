package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.*;

public class Lfunc implements IConclusion {
	
	private IMeasuredData data;
	private double alfa;
	private double beta;
	
	public Lfunc(IMeasuredData data, double alfa, double beta) {
		this.alfa = alfa;
		this.beta = beta;
		this.data = data.getCopy();
	}

	@Override
	public double computeConclusion(IInput sensor) {
		data.pickData(sensor);
		if (data.getX()<alfa) {
			return 1;
		} else if (data.getX()>=alfa && data.getX()<beta) {
			return (beta-data.getX())/(beta-alfa);
		} else {
			return 0;
		}
	}

	@Override
	//napravljena restrikcija: ukoliko je y=1, inv(L)=alfa, ukoliko je y=0; inv(L)=beta, inače normalan presijek
	public double computeInverseConclusion(double y) {
		data.setMemFun(y);
		double inv;
		if (y >= 1) {
			inv = alfa;
		} else if (y <= 0) {
			inv = beta;
		} else {
			inv = beta - y*(beta-alfa);
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
		return "L[var:" + data.getDataName() + ", param:" + alfa + "," + beta + "]";
	}
}