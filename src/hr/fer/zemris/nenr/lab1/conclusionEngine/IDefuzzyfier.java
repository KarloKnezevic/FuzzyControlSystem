package hr.fer.zemris.nenr.lab1.conclusionEngine;

import hr.fer.zemris.nenr.lab1.rule.IRule;

public interface IDefuzzyfier {
	public double defuzzyfy(IRule[] concludedRules);
}
