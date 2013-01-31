package hr.fer.zemris.nenr.lab1.membershipFunctions;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.input.IMeasuredData;

public interface IConclusion {
	public double computeConclusion(IInput sensor);
	public double computeInverseConclusion(double y);
	public IMeasuredData getMeasuredData();
}
