package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.*;

public class Sfunc implements IConclusion {

	private IMeasuredData data;
	private double alfa;
	private double beta;
	private double gamma;

	public Sfunc(IMeasuredData data, double alfa, double beta, double gamma) {
		this.alfa = alfa;
		this.beta = beta;
		this.gamma = gamma;
		this.data = data.getCopy();
	}

	@Override
	public double computeConclusion(IInput sensor) {
		data.pickData(sensor);
		if (data.getX()<alfa || data.getX()>=gamma) {
			return 0;
		} else if (data.getX()>=alfa && data.getX()<beta) {
			return 2*Math.pow((data.getX()-alfa)/(gamma-alfa), 2);
		} else {
			return 1-2*Math.pow((data.getX()-gamma)/(gamma-alfa), 2);
		}
	}

	@Override
	//napravljena restrikcija: ukoliko je y=1, inv(L)=gamma, ukoliko je y=0; inv(L)=alfa, inače normalan presijek
	public double computeInverseConclusion(double y) {
		data.setMemFun(y);
		double inv;
		if (y <= 0) {
			inv = alfa;
		} else if (y >= 1) {
			inv = gamma;
		} else if (y>0 && y<0.5) {
			inv = alfa + (gamma-alfa)*Math.sqrt(y/2);
		} else {
			inv = gamma + (gamma-alfa)*Math.sqrt((1-y)/2);
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
		return "S[var:" + data.getDataName() + ", param:" + alfa + "," + beta + "," + gamma + "]";
	}
}
