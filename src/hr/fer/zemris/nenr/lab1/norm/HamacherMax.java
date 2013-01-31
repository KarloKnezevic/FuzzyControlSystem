package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class HamacherMax extends Norm {
	
	public HamacherMax() {}

	public HamacherMax(IConclusion arg1, IConclusion arg2) {
		super(arg1, arg2);
	}

	@Override
	public double computeConclusion(IInput sensor) {
		double a = arg1.computeConclusion(sensor);
		double b = arg2.computeConclusion(sensor);
		
		return (a+b-2*a*b)/(1-a*b);
	}
	
	@Override
	public String toString() {
		return "( " + arg1.toString() + " ILI " + arg2.toString() + " )";
	}
	
	@Override
	public IConclusion x(IConclusion arg1, IConclusion arg2) {
		return new HamacherMax(arg1, arg2);
	}
}