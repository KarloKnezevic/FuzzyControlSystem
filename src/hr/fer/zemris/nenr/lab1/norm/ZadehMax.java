package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class ZadehMax extends Norm {
	
	public ZadehMax() {}

	public ZadehMax(IConclusion arg1, IConclusion arg2) {
		super(arg1, arg2);
	}
	
	@Override
	public double computeConclusion(IInput sensor) {
		return Math.max(arg1.computeConclusion(sensor), arg2.computeConclusion(sensor));
	}
	
	@Override
	public String toString() {
		return "( " + arg1.toString() + " ILI " + arg2.toString() + " )";
	}
	
	@Override
	public IConclusion x(IConclusion arg1, IConclusion arg2) {
		return new ZadehMax(arg1, arg2);
	}
}