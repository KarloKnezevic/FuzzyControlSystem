package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.input.IMeasuredData;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public class Dilatation extends Norm {
	
	public Dilatation() {
		param = 0.5;
	}
	
	public Dilatation(double param) {
		this.param = param;
	}

	public Dilatation(IConclusion arg) {
		//ukoliko drugačije definirano, koncentracija se računa kao korijen funkcije pripadnosti
		super(arg, 0.5);
	}
	
	public Dilatation(IConclusion arg, double t) {
		//con(x) = mi(x)^t; cilj je da je t<1, inače se dobiva koncentracija
		super(arg, t);
	}
	
	@Override
	public double computeConclusion(IInput sensor) {
		return Math.pow(arg1.computeConclusion(sensor), param);
	}
	
	@Override
	public double computeInverseConclusion(double y) {
		return arg1.computeInverseConclusion(Math.pow(y, 1/param));
	}
	
	@Override
	public IMeasuredData getMeasuredData() {
		return arg1.getMeasuredData();
	}
	
	@Override
	public String toString() {
		return "( MANJE ILI VIŠE[" + param + "](" + arg1.toString() + ") )";
	}
	
	@Override
	public IConclusion x(IConclusion arg) {
		return new Dilatation(arg, param);
	}
}