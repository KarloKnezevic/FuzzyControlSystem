package hr.fer.zemris.nenr.lab1.conclusionEngine;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.nenr.lab1.database.IDatabase;
import hr.fer.zemris.nenr.lab1.input.IInput;
import hr.fer.zemris.nenr.lab1.input.IMeasuredData;
import hr.fer.zemris.nenr.lab1.rule.IRule;

public class ConclusionEngine implements IConclusionEngine {
	
	private IDatabase DB;
	private IInput input;
	private IRule[] dbRules;
	
	private IMeasuredData[] differentConclusions;
	private int[] pureData;
	
	private IDefuzzyfier defuzzyfier;

	public ConclusionEngine(IDatabase dB, IDefuzzyfier defuzyfier, IMeasuredData[] differentConclusions) {
		this.DB = dB;
		dbRules = DB.returnAllRules();
		this.differentConclusions = differentConclusions;
		this.defuzzyfier = defuzyfier;
		
		if (differentConclusions != null) {
			pureData = new int[differentConclusions.length];
		} else pureData = null;
	}
	
	@Override
	public void readInput(IInput input) {
		this.input = input;
	}

	@Override
	public void conclude() {
		for (IRule r : dbRules) {
			r.makeConclusion(input);
		}
		
		List<IRule> conclusions = new ArrayList<>();
		for (int i = 0; i < differentConclusions.length; i++) {
			conclusions.clear();
			for (IRule r : dbRules) {
				if (differentConclusions[i].getDataName().equals(r.getConsequent().getMeasuredData().getDataName())) {
					conclusions.add(r);
				}
			}
			//zaokruživanje obzirom na 0.5
			pureData[i] = (int)Math.floor(defuzzyfier.defuzzyfy(conclusions.toArray(new IRule[conclusions.size()])) + 0.5);
		}
	}

	@Override
	public int[] getConclusion() {
		return pureData;
	}
}