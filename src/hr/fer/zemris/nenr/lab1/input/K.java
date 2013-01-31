package hr.fer.zemris.nenr.lab1.input;

public class K implements IMeasuredData {

	private double K;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public K(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		//senzor ne mjeri kut
	}

	@Override
	public double getX() {
		return K;
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
		return "K";
	}

	@Override
	public void setX(double x) {
		K = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new K(minDomain, maxDomain);
	}
}
