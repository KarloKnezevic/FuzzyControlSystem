package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.input.IMeasuredData;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class YagerParamNot extends Norm {
	
	public YagerParamNot(double param) {
		this.param = param;
	}

	public YagerParamNot(IConclusion arg, double param) {
		super(arg, param);
		this.param = param!=0 ? param : 1;
	}
	
	@Override
	public double computeConclusion(IInput sensor) {
		double a = arg1.computeConclusion(sensor);
		return Math.pow(1-Math.pow(a, param), 1/param);
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
		return new YagerParamNot(arg, param);
	}
}