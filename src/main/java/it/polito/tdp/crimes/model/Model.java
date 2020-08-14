package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private EventsDao dao;
	private Graph<Integer, DefaultWeightedEdge> graph;
	private List<Integer> vertici;
	
	public Model() {
		dao=new EventsDao();
	}
	
	public List<Integer> getYears(){
		return dao.getYears();
	}
	
	public void creaGrafo(Integer year) {
		this.graph=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.vertici=dao.getDistrictIds();
		Graphs.addAllVertices(this.graph, vertici);
		
		for(Integer v1: vertici) {
			for(Integer v2: vertici) {
				if(!v1.equals(v2)) {
					if(this.graph.getEdge(v1, v2)==null) {
						Double latMediav1=dao.getLatMedia(year,v1);
						Double lonMediav1=dao.getLonMedia(year,v1);
						LatLng centroV1=new LatLng(latMediav1, lonMediav1);
						
						Double latMediav2=dao.getLatMedia(year,v2);
						Double lonMediav2=dao.getLonMedia(year,v2);
						LatLng centroV2=new LatLng(latMediav2, lonMediav2);
						
						Double distMedia=LatLngTool.distance(centroV1, centroV2, LengthUnit.KILOMETER);
						
						Graphs.addEdgeWithVertices(this.graph, v1, v2, distMedia);
					}
				}
			}
		}
	}
	
	public List<DistrictDistance> getAdiacenti(Integer vertex){
		List<Integer> vicini=Graphs.neighborListOf(graph, vertex);
		List<DistrictDistance> result=new ArrayList<>();
		for(Integer vicino: vicini) {
			result.add(new DistrictDistance(vicino,graph.getEdgeWeight(graph.getEdge(vertex, vicino))));
		}
		Collections.sort(result);
		return result;
	}
	
	public List<Integer> getDistricts(){
		return this.vertici;
	}
	
	public int numVertici() {
		return this.graph.vertexSet().size();
	}
	public int numArchi() {
		return this.graph.edgeSet().size();
	}
	
	public List<Integer> getMesi(Integer year){
		List<Integer> result= dao.getMonth(year);
		Collections.sort(result);
		return result;
	}
	public List<Integer> getGiorni(Integer year){
		List<Integer> result= dao.getDay(year);
		Collections.sort(result);
		return result;
	}
}
