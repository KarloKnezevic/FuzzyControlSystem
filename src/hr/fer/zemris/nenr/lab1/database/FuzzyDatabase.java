package hr.fer.zemris.nenr.lab1.database;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hr.fer.zemris.nenr.lab1.rule.IRule;

public class FuzzyDatabase implements IDatabase {
	
	private List<IRule> rules;
	private String DB2File;
	
	public FuzzyDatabase() {
		rules = new ArrayList<>();
		DB2File = "DATABASERULES.txt";
	}
	
	public FuzzyDatabase(String DBFileName) {
		rules = new ArrayList<>();
		DB2File = DBFileName;
	}

	@Override
	public void addRule(IRule rule) {
		rules.add(rule);
	}

	@Override
	public void addRules(IRule[] rules) {
		for (IRule r : rules) {
			this.rules.add(r);
		}
	}

	@Override
	public void dropDB() {
		rules.clear();
	}

	@Override
	public void deleteRule(int index) {
		if (index > 0 && index < rules.size()-1)
			rules.remove(index);
	}

	@Override
	public int sizeDB() {
		return rules.size();
	}

	@Override
	public IRule[] returnAllRules() {
		return rules.toArray(new IRule[rules.size()]);
	}

	@Override
	public IRule returnRule(int index) {
		if (index > 0 && index < rules.size()-1)
			return rules.get(index);
		return null;
	}

	@Override
	public void printDB2File() {
		Writer bw = null;
		try {
			bw = new BufferedWriter(
					new OutputStreamWriter(
							new BufferedOutputStream(
									new FileOutputStream(DB2File)), "UTF-8"));
		} catch (Exception e) {
			System.err.println("Database ERROR. Cannot write to file.");
			e.printStackTrace();
		}
		
		PrintWriter pw = new PrintWriter(bw);
		String equalsLine = "====================================================";
		
		pw.println("FUZZY DATABASE");
		pw.println(equalsLine);
		pw.println("#Rules    : " + rules.size());
		pw.println("Time info : " + Calendar.getInstance().getTime());
		pw.println(equalsLine);
		for (int i = 0; i < rules.size(); i++) {
			pw.println((i+1) + "] " + rules.get(i).getStringAntecedent() + " → " + rules.get(i).getStringConsequent());
			if (i != rules.size()-1) pw.println();
		}
		pw.println(equalsLine);
		pw.close();
	}
}