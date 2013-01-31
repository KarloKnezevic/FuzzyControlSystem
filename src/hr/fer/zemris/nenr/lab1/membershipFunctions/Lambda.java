package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.*;

public class Lambda implements IConclusion {
	
	private IMeasuredData data;
	private double alfa;
	private double beta;
	private double gamma;
	
	public Lambda(IMeasuredData data, double alfa, double beta, double gamma) {
		this.data = data.getCopy();
		this.alfa = alfa;
		this.beta = beta;
		this.gamma = gamma;
	}

	@Override
	public double computeConclusion(IInput sensor) {
		data.pickData(sensor);
		if (data.getX()<alfa || data.getX()>=gamma) {
			return 0;
		} else if (data.getX()>=alfa && data.getX()<=beta) {
			return (data.getX()-alfa)/(beta-alfa);
		} else {
			return (gamma-data.getX())/(gamma-beta);
		}
	}

	@Override
	public double computeInverseConclusion(double y) {
		return 0.0;
	}

	@Override
	public IMeasuredData getMeasuredData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "Λ[var:" + data.getDataName() + ", param:" + alfa + "," + beta + "," + gamma + "]";
	}
}
