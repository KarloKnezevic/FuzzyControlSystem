package hr.fer.zemris.nenr.lab1.rule;

import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;

public interface IRule {
	public void makeConclusion(IInput premise);
	public IConclusion getConsequent();
	public IConclusion getAntecedent();
	public String getStringConsequent();
	public String getStringAntecedent();
}
