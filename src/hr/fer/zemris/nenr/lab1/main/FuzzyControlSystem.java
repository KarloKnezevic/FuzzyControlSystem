package hr.fer.zemris.nenr.lab1.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import hr.fer.zemris.nenr.lab1.conclusionEngine.ConclusionEngine;
import hr.fer.zemris.nenr.lab1.conclusionEngine.IConclusionEngine;
import hr.fer.zemris.nenr.lab1.conclusionEngine.Tsukamoto;
import hr.fer.zemris.nenr.lab1.database.*;
import hr.fer.zemris.nenr.lab1.input.*;
import hr.fer.zemris.nenr.lab1.membershipFunctions.*;
import hr.fer.zemris.nenr.lab1.norm.*;
import hr.fer.zemris.nenr.lab1.rule.*;

/**
 * 
 * @author Karlo Knežević
 * 1. laboratorijska vježba iz predmeta: Neizrazito evolucijsko i neuro računarstvo
 * Fuzzy Boat
 *
 */
public class FuzzyControlSystem {

	/**
	 * Definirati ulaz i izlaz senzora.
	 * Definirati bazu pravila i popuniti je pravilima.
	 * Definirati ulaze i domenu mjerenih podataka.
	 * Pokrenuti stroj za zaključivanje.
	 * Algoritam: dok god ima rezultata sa senzora:
	 * 					čitaj rezultate
	 * 					izvedi zaključak
	 * 					reagiraj
	 * @param args
	 */
	public static void main(String[] args) {

		//senzor, čita podatke iz okoline
		IInput sensor = new Sensor(new BufferedReader(new InputStreamReader(System.in)), new BufferedWriter(new OutputStreamWriter(System.out)));

		//baza pravila
		IDatabase DB = new FuzzyDatabase();

		//ulazi i domena ulaza
		//ovo je ujedno i UNIVERZALAN SKUP - stvarna fizička domena u kojoj elementi poprimaju numeričke vrijednosti
		IMeasuredData A = new A(-35, 35); 	//konsekvens
		IMeasuredData K = new K(-90, 90); 	//konsekvens
		IMeasuredData D = new D(0, 1300);	//antecedens
		IMeasuredData L = new L(0, 1300);	//antecedens
		IMeasuredData DK = new DK(0, 1300);	//antecedens
		IMeasuredData LK = new LK(0, 1300);	//antecedens
		IMeasuredData V = new V(0, 1300);	//antecedens
		IMeasuredData S = new S(0, 1);		//antecedens	

		//mjerna svojstva i domena (konsekvensi)
		IMeasuredData[] measuredData = {A, K};
		
		//korišteni operatori i norme -> lijepo se ponašaju Zadeh, Algebarski, DRASTIC (NAJBOLJI!) 
		Norm I = new DrasticMin();
		Norm ILI = new DrasticMax();
		Norm NE = new ZadehNot();
		Norm VRLO = new Concentration();
		Norm MANJEiliVISE = new Dilatation();
		

		//definirana pravila; antecedens -> konsekvens; konsekvens mora biti funkcija jer je dekomponiran (ne mora nužno biti funkcija, ali u tom slučaju treba definirati inverz normi
		//u pravilima su navedeni modeli neizrazitih skupova kojima su pridružene jezične varijable
		IRule[] rules = {
				//ako je udaljenost od lijeve strane mala i brzina oko 20, skreni u desno do 10 stupnjeva
				new Rule( I.x(new Lfunc(L, 10, 30), new Lambda(V, 10, 20, 30)), 	new Lfunc(K, -10, 0)),
				//ako je udaljenost od desne strane mala i brzina oko 20, skreni u lijevo do 10 stupnjeva
				new Rule( I.x(new Lfunc(D, 10, 30), new Lambda(V, 10, 20, 30)), 	new Gamma(K, 0, 10)),
				//ako je LK malen i brzina mala, skreni udesno
				new Rule( I.x(new Lfunc(LK, 50, 100), new Lfunc(V, 20, 35)), 		new Lfunc(K, -45, 0)),
				//ako je DK malen i brzina mala, skreni ulijevo
				new Rule( I.x(new Lfunc(DK, 50, 100), new Lfunc(V, 20, 35)), 		new Gamma(K, 0, 45)),
				//ako je LK malen i brzina mala ili je DK malen i brzina mala, povećaj akceleraciju
				new Rule( ILI.x( I.x(new Lfunc(LK, 50, 100), new Lfunc(V, 20, 35)), 
						I.x(new Lfunc(DK, 50, 100), new Lfunc(V, 20, 35))),		new Gamma(A, 25, 35)),
				//ako je L malen i LK jako malen, jako skreni udesno
				new Rule( I.x( new Lfunc(L, 15, 25), VRLO.x(new Lfunc(LK, 50, 100))), 	VRLO.x(new Lfunc(K, -90, -70))),
				//ako je D malen i DK jako malen, jako skreni ulijevo
				new Rule( I.x( new Lfunc(D, 15, 25), VRLO.x(new Lfunc(DK, 50, 100))), 	VRLO.x(new Gamma(K, 70, 90))),
				//ako V nije velika i LK i DK nisu mali, više ili manje povećaj akceleraciju
				new Rule( I.x( NE.x( new Gamma(V, 40, 60)), 
						NE.x( ILI.x(new Lfunc(LK, 30, 70), new Lfunc(DK, 30, 70)))),	MANJEiliVISE.x( new Gamma(A, 0, 20))),
				//ako je V relativno velika i LK ili DK jako mali, jako koči
				new Rule ( I.x( MANJEiliVISE.x(new Gamma(V, 45, 70)), 
						ILI.x( VRLO.x(new Lfunc(LK, 50, 100)), VRLO.x(new Lfunc(DK, 50, 100)))), 	VRLO.x(new Lfunc(A, -35, -10))),
				//ako je V velika i LK malen, jako skreni udesno
				new Rule ( I.x( new Gamma(V, 25, 45), new Lfunc(LK, 50, 100)), 		VRLO.x(new Lfunc(K, -90, -70))),
				//ako je V velika i DK malen, jako skreni ulijevo
				new Rule ( I.x( new Gamma(V, 25, 45), new Lfunc(DK, 50, 100)), 		VRLO.x(new Gamma(K, 70, 90))),
				//ako je D jako malen ili L jako malen, povećaj akceleraciju
				new Rule ( ILI.x(VRLO.x( new Lfunc(D, 10, 35)), VRLO.x( new Lfunc(L, 10, 35))), 	new Gamma(A, 34, 35)),
				//ako je D jako malen, jako skreni ulijevo
				new Rule ( VRLO.x(new Lfunc(D, 10, 35)),	new Gamma(K, 88, 90)), 
				//ako je L jako malen, jako skreni udesno
				new Rule ( VRLO.x(new Lfunc(L, 10, 35)),	new Lfunc(K, -90, -88)),
				//ako je smjer suprotan, jako smanji akceleraciju
				new Rule ( new Lfunc(S, 0, 1),	VRLO.x (new Lfunc(A, -35, 0)))
		};
		//dodavanje skupa pravila u bazu podataka
		DB.addRules(rules);
		//ispiši pravila u datoteku
		DB.printDB2File();

		//sustav za zaključivanje: baza podataka, defazifikator, različiti antecedensi
		IConclusionEngine cEngine = new ConclusionEngine(DB, new Tsukamoto(), measuredData);

		//dok ima ulaznih podataka sa senzora, upravljaj
		while (sensor.measure()) {
			//pročitaj informaciju sa senzora
			cEngine.readInput(sensor);
			//donesi odluku
			cEngine.conclude();
			//učitaj odluku i javi drugim djelovima broda informaciju
			sensor.measure(cEngine.getConclusion());
		}
	}
}