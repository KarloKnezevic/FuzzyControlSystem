package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class HamacherParamMin extends Norm {
	
	public HamacherParamMin(double param) {
		this.param = param;
	}

	public HamacherParamMin(IConclusion arg1, IConclusion arg2, double param) {
		super(arg1, arg2, param);
		this.param = param < 0 ? -1*param : param;
	}

	@Override
	public double computeConclusion(IInput sensor) {
		double a = arg1.computeConclusion(sensor);
		double b = arg2.computeConclusion(sensor);
		return (a*b)/(param + (1-param)*(a+b-a*b));
	}
	
	@Override
	public String toString() {
		return "( " + arg1.toString() + " I " + arg2.toString() + " )";
	}
	
	@Override
	public IConclusion x(IConclusion arg1, IConclusion arg2) {
		return new HamacherParamMin(arg1, arg2, param);
	}
}