package hr.fer.zemris.nenr.lab1.input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sensor implements IInput {
	private int L;
	private int D;
	private int LK;
	private int DK;
	private int V;
	private int S;

	private BufferedReader reader;
	private BufferedWriter writer;

	public Sensor(BufferedReader reader, BufferedWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public boolean measure() {
		String line = null;
		
		try {
			line = reader.readLine();
		} catch (IOException e) {
			System.err.println("Sensor ERROR. Cannot measure.");
			e.printStackTrace();
		}
		
		if (line != null) {
			if(line.charAt(0)=='K') return false;
			Scanner s = new Scanner(line);
			L = s.nextInt();
			D = s.nextInt();
			LK = s.nextInt();
			DK = s.nextInt();
			V = s.nextInt();
			S = s.nextInt();
		}
		return true;
	}
	
	@Override
	public void measure(int[] data) {
		PrintWriter pw = new PrintWriter(writer);
		pw.println(data[0] + " " + data[1]);
		pw.flush();
	}
	
	@Override
	public int getL() {
		return L;
	}

	@Override
	public int getD() {
		return D;
	}

	@Override
	public int getLK() {
		return LK;
	}

	@Override
	public int getDK() {
		return DK;
	}

	@Override
	public int getV() {
		return V;
	}

	@Override
	public int getS() {
		return S;
	}
}