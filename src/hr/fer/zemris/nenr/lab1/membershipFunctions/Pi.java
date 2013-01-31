package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.*;

public class Pi implements IConclusion {
	
	private IMeasuredData data;
	private double alfa;
	private double beta;
	private double gamma;
	private double delta;
	
	public Pi(IMeasuredData data, double alfa, double beta, double gamma, double delta) {
		this.alfa = alfa;
		this.beta = beta;
		this.gamma = gamma;
		this.delta = delta;
		this.data = data.getCopy();
	}

	@Override
	public double computeConclusion(IInput sensor) {
		data.pickData(sensor);
		if (data.getX()<alfa || data.getX()>=delta) {
			return 0;
		} else if (data.getX()>=alfa && data.getX()<beta) {
			return (data.getX()-alfa)/(beta-alfa);
		} else if (data.getX()>=beta && data.getX()<gamma) {
			return 1;
		} else {
			return (delta-data.getX())/(delta-gamma);
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
		return "∏[var:" + data.getDataName() + ", param:" + alfa + "," + beta + "," + gamma + "," + delta + "]";
	}
}