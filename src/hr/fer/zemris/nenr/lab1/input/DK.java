package hr.fer.zemris.nenr.lab1.input;

public class DK implements IMeasuredData {

	private double DK;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public DK(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		DK = sensor.getDK();
	}

	@Override
	public double getX() {
		return DK;
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
		return "DK";
	}

	@Override
	public void setX(double x) {
		DK = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new DK(minDomain, maxDomain);
	}
}