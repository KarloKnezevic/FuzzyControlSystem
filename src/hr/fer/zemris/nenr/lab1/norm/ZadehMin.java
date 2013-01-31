package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class ZadehMin extends Norm  {
	
	public ZadehMin() {};

	public ZadehMin(IConclusion arg1, IConclusion arg2) {
		super(arg1, arg2);
	}
	
	@Override
	public double computeConclusion(IInput sensor) {
		return Math.min(arg1.computeConclusion(sensor), arg2.computeConclusion(sensor));
	}
	
	@Override
	public String toString() {
		return "( " + arg1.toString() + " I " + arg2.toString() + " )";
	}
	
	@Override
	public IConclusion x(IConclusion arg1, IConclusion arg2) {
		return new ZadehMin(arg1, arg2);
	}
}