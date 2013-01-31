package hr.fer.zemris.nenr.lab1.input;

public class D implements IMeasuredData {
	
	private double D;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public D(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		D = sensor.getD();
	}

	@Override
	public double getX() {
		return D;
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
		return "D";
	}

	@Override
	public void setX(double x) {
		D = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new D(minDomain, maxDomain);
	}
}