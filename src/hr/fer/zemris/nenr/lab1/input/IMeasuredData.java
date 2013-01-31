package hr.fer.zemris.nenr.lab1.input;

public interface IMeasuredData {
	//odaberi podatak sa senzora
	public void pickData(IInput sensor);
	//postavi podatak iz domene
	public void setX(double x);
	//vrati podatak iz domene
	public double getX();
	//vrati iznos donje granice domene
	public double getMinDomain();
	//vrati iznos gornje granice domene
	public double getMaxDomain();
	//postavi iznos funkcije pripadnosti
	public void setMemFun(double w);
	//vrati iznos funkcije pripadnosti
	public double getMemFun();
	//vrati ime mjerene komponente
	public String getDataName();
	//vrati kopiju objekta
	public IMeasuredData getCopy();
}