package it.polito.tdp.crimes.model;

import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	
	//input utente
	private Integer N;
	private Integer anno;
	private Integer mese;
	private Integer giorno;
	
	//WORLS STATUS
	private Graph<Integer, DefaultWeightedEdge> graph;
	private Map<Integer,Integer> mappaDegliAgenti; //per ogni distretto mi dice quanti agenti sono liberi
	
	//coda degli eventi
	private PriorityQueue<Evento> queue;
	
	//misure di output
	private int numEventiMalGestiti;
	
	public void init(Integer N, Integer anno, Integer mese, Integer giorno, Graph<Integer,DefaultWeightedEdge> grafo) {
		this.graph=grafo;
		this.queue=new PriorityQueue<>();
		this.numEventiMalGestiti=0;
		this.N=N;
		this.anno=anno;
		this.mese=mese;
		this.giorno=giorno;
		
		//INIZIALIZZO LA MAPPA: PER OGNI DISTRETTO CI SONO ZERO AGENTI
		
	}
	
	
	public Simulatore() {
		
	}
}
