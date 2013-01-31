package hr.fer.zemris.nenr.lab1.input;

public class L implements IMeasuredData {
	
	private double L;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public L(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		L = sensor.getL();
	}

	@Override
	public double getX() {
		return L;
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
		return "L";
	}

	@Override
	public void setX(double x) {
		L = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new L(minDomain, maxDomain);
	}
}