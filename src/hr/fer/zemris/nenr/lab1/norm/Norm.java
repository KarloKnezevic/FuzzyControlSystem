package hr.fer.zemris.nenr.lab1.norm;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.input.IMeasuredData;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public abstract class Norm implements IConclusion {
	
	//1. argument norme
	protected IConclusion arg1 = null;
	//2. argument norme
	protected IConclusion arg2 = null;
	//parametar ukoliko je norma parametrizirana
	protected Double param = null;
	
	public Norm() {}
	
	public Norm(double param) {
		this.param = param;
	}
	
	public Norm(IConclusion arg) {
		this.arg1 = arg;
	}
	
	public Norm(IConclusion arg, double param) {
		this.arg1 = arg;
		this.param = param;
	}
	
	public Norm(IConclusion arg1, IConclusion arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public Norm(IConclusion arg1, IConclusion arg2, double param) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.param = param;
	}
	
	public IConclusion x(IConclusion arg) {
		return null;
	}
	
	public IConclusion x(IConclusion arg1, IConclusion arg2) {
		return null;
	}
	
	@Override
	//treba nadjačati
	public double computeConclusion(IInput sensor) {
		return 0;
	}

	@Override
	//norma ne vraća inverz, ali dozvoljena je bilokakva interpretacija
	public double computeInverseConclusion(double y) {
		return 0.0;
	}

	@Override
	//treba nadjačati
	public IMeasuredData getMeasuredData() {
		return null;
	}

}
