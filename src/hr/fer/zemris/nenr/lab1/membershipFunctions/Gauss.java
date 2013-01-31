package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.*;

public class Gauss implements IConclusion{

	private IMeasuredData data;
	private double mi;
	private double sigma;

	public Gauss(IMeasuredData data, double mi, double sigma) {
		this.mi = mi;
		this.sigma = sigma;
		this.data = data.getCopy();
	}

	@Override
	public double computeConclusion(IInput sensor) {
		data.pickData(sensor);
		return Math.pow(Math.E, -1*Math.pow((data.getX()-mi)/sigma, 2));
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
		return "Gauss[var:" + data.getDataName() + ", param:" + mi + "," + sigma + "]";
	}
}
