package it.polito.tdp.nobel.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.nobel.db.EsameDAO;

public class Model {
	
	private List<Esame> esami;
	private double mediabest = 0.0; 
	private Set<Esame> bestSoluzione = null; 
	
	
	
	public Model() {
		EsameDAO dao =new EsameDAO();
		this.esami = dao.getTuttiEsami(); 
	}

	
	public Set<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		
		Set<Esame> parziale = new HashSet<>(); 
		
		cerca(parziale, 0, numeroCrediti); 
		//Cerca2(parziale,0,numerocrediti); 
		
		//System.out.println("TODO!");
		
		return bestSoluzione;
	}
	
	
	private void cerca(Set<Esame> parziale, int livello, int m) {
		
		int crediti = sommaCrediti(parziale); 
		if(crediti>m) {
			return; 
		}
		
		if(crediti == m) {
			double media = calcoloMedia(parziale); 
			if(media > mediabest) {
				bestSoluzione = new HashSet<>(parziale); 
				mediabest = media; 
				
			}
		}
		
		if(livello == esami.size()) {
			return; 
		}
		//nel caso lo aggiungessi
		parziale.add(esami.get(livello)); 
		cerca(parziale, livello+1, m);
		parziale.remove(esami.get(livello)); 
		
		
		//non lo aggiungo
		cerca(parziale,livello+1,m); 
		
	}


	public double calcoloMedia(Set<Esame> parziale) {
		int crediti = 0; 
		int somma = 0; 
		
		for(Esame e: parziale) {
			crediti+=e.getCrediti(); 
			somma +=(e.getVoto()*e.getCrediti()); 
		}
		return somma/crediti;
	}


	private int sommaCrediti(Set<Esame> parziale) {
		int somma = 0; 
		
		for(Esame e : parziale) {
			somma+=e.getCrediti(); 
		}
		return somma;
	}
	
	
	//questo approccio risulta troppo lento 
	private void Cerca2(Set<Esame> parziale, int livello, int m) {
		int crediti = sommaCrediti(parziale); 
		if(crediti>m) {
			return; 
		}
		
		if(crediti == m) {
			double media = calcoloMedia(parziale); 
			if(media > mediabest) {
				bestSoluzione = new HashSet<>(parziale); 
				mediabest = media; 
				
			}
		}
		
		if(livello == esami.size()) {
			return; 
		}
		
		for (Esame e: esami) {
			if(!parziale.contains(e)) {
			parziale.add(e); 
			Cerca2(parziale,livello+1, m);
			parziale.remove(e); 
			}
		}
		
	}

}
