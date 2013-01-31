package hr.fer.zemris.nenr.lab1.input;

public interface IInput {
	//očitanje 6 podataka sa senzora
	public int getL();
	public int getD();
	public int getLK();
	public int getDK();
	public int getV();
	public int getS();
	//mjerena informacija iz okoline
	public boolean measure();
	//senzoru dajemo informaciju; senzor ograničen na cjelobrojne vrijednosti
	public void measure(int[] data);
}
