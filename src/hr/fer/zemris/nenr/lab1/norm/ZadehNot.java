package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.input.IMeasuredData;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class ZadehNot extends Norm {
	
	public ZadehNot() {}

	public ZadehNot(IConclusion arg) {
		super(arg);
	}
	
	@Override
	public double computeConclusion(IInput sensor) {
		return 1.0-arg1.computeConclusion(sensor);
	}
	
	@Override
	public double computeInverseConclusion(double y) {
		return arg1.computeInverseConclusion(1-y);
	}
	
	@Override
	public IMeasuredData getMeasuredData() {
		return arg1.getMeasuredData();
	}
	
	@Override
	public String toString() {
		return "( NE(" + arg1.toString() + ") )";
	}
	
	@Override
	public IConclusion x(IConclusion arg) {
		return new ZadehNot(arg);
	}
}