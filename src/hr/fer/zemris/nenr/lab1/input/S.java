package hr.fer.zemris.nenr.lab1.input;

public class S implements IMeasuredData {

	private double S;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public S(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		S = sensor.getS();
	}

	@Override
	public double getX() {
		return S;
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
		return "S";
	}

	@Override
	public void setX(double x) {
		S = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new S(minDomain, maxDomain);
	}
}
