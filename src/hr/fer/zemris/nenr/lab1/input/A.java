package hr.fer.zemris.nenr.lab1.input;

public class A implements IMeasuredData {

	private double Acc;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public A(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		//senzor ne mjeri akceleraciju
	}

	@Override
	public double getX() {
		return Acc;
	}

	@Override
	public double getMinDomain() {
		return minDomain;
	}

	@Override
	public double getMaxDomain() {
		return maxDomain;
	}

	@Override
	public void setMemFun(double w) {
		membershipFunctionValue = w;
		
	}

	@Override
	public double getMemFun() {
		return membershipFunctionValue;
	}

	@Override
	public String getDataName() {
		return "A";
	}

	@Override
	public void setX(double x) {
		Acc = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new A(minDomain, maxDomain);
	}
}
