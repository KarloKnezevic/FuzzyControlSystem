package hr.fer.zemris.nenr.lab1.input;

public class LK implements IMeasuredData {

	private double LK;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public LK(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		LK = sensor.getLK();
	}

	@Override
	public double getX() {
		return LK;
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
		return "LK";
	}

	@Override
	public void setX(double x) {
		LK = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new LK(minDomain, maxDomain);
	}
}