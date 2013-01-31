package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.*;

public class ConPi implements IConclusion {
	
	private IMeasuredData data;
	private double beta;
	private double gamma;

	public ConPi(IMeasuredData data, double beta, double gamma) {
		this.beta = beta;
		this.gamma = gamma;
		this.data = data.getCopy();
	}

	@Override
	public double computeConclusion(IInput sensor) {
		data.pickData(sensor);
		if (data.getX()<gamma) {
			return this.S(data.getX(), gamma-beta, gamma-beta/2, gamma);
		} else {
			return 1 - this.S(data.getX(), gamma, gamma+beta/2, gamma+beta);
		}
	}
	
	private double S(double x, double a, double b, double c) {
		if (x<a || x>=c) {
			return 0;
		} else if (x>=a && x<b) {
			return 2*Math.pow((x-a)/(c-a), 2);
		} else {
			return 1-2*Math.pow((x-c)/(c-a), 2);
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
		return "π[var:" + data.getDataName() + ", param:" + beta + "," + gamma + "]";
	}
}