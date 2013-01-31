package hr.fer.zemris.nenr.lab1.conclusionEngine;

import hr.fer.zemris.nenr.lab1.input.IInput;

public interface IConclusionEngine {
	
	public void readInput(IInput input);
	public void conclude();
	public int[] getConclusion();

}
