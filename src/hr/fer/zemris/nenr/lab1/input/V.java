package hr.fer.zemris.nenr.lab1.input;

public class V implements IMeasuredData {

	private double V;
	private double minDomain;
	private double maxDomain;
	private double membershipFunctionValue;
	
	public V(double minDomain, double maxDomain) {
		this.minDomain = minDomain;
		this.maxDomain = maxDomain;
	}

	@Override
	public void pickData(IInput sensor) {
		V = sensor.getV();
	}

	@Override
	public double getX() {
		return V;
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
		return "V";
	}

	@Override
	public void setX(double x) {
		V = x;
	}

	@Override
	public IMeasuredData getCopy() {
		return new V(minDomain, maxDomain);
	}
}
