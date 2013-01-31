package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.input.IMeasuredData;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class ContrastIntensification extends Norm {
	
	public ContrastIntensification() {
		param = 2.0;
	}
	
	public ContrastIntensification(double param) {
		this.param = param;
	}

	public ContrastIntensification(IConclusion arg) {
		//pretpostavljen izraz za kontrastnu intenzifikaciju
		super(arg, 2);
	}
	
	public ContrastIntensification(IConclusion arg, double t) {
		super(arg, t);
	}
	
	@Override
	public double computeConclusion(IInput sensor) {
		double a = arg1.computeConclusion(sensor);
		if (a>=0 && a<=0.5) {
			return 2*Math.pow(a, 2);
		} else {
			return 1-2*Math.pow((1-a), 2);
		}
	}
	
	@Override
	public IMeasuredData getMeasuredData() {
		return arg1.getMeasuredData();
	}
	
	@Override
	public String toString() {
		return "( MANJE I VIŠE[" + param + "](" + arg1.toString() + ") )";
	}
	
	@Override
	public IConclusion x(IConclusion arg) {
		return new ContrastIntensification(arg, param);
	}
}