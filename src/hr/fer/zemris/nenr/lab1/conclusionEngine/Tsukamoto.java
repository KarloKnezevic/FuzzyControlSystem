package hr.fer.zemris.nenr.lab1.conclusionEngine;

import hr.fer.zemris.nenr.lab1.membershipFunctions.IConclusion;
import hr.fer.zemris.nenr.lab1.rule.IRule;

public class Tsukamoto implements IDefuzzyfier {

	@Override
	//ovaj algoritam zanimaju samo zaključci
	public double defuzzyfy(IRule[] concludedRules) {
		IConclusion[] conclusions = new IConclusion[concludedRules.length];
		for (int i = 0; i < concludedRules.length; i++) {
			conclusions[i] = concludedRules[i].getConsequent();
		}

		double sumAlfa = 0;
		double sumAlfaW = 0;
		
		for (IConclusion c : conclusions) {
			double alfa = c.getMeasuredData().getMemFun();
			double w = c.getMeasuredData().getX();
			sumAlfa += alfa;
			sumAlfaW += alfa*w;
		}
		
		if (sumAlfa == 0.0) return 0.0;	
		return sumAlfaW/sumAlfa;
	}
}