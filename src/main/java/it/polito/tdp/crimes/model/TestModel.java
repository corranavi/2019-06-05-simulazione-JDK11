package it.polito.tdp.crimes.model;

public class TestModel {

	public static void main(String[] args) {
		Model model=new Model();
		model.creaGrafo(2014);
		System.out.println(String.format("Grafo creato con %d vertici e %d archi.", model.numVertici(),model.numArchi()));
		for(DistrictDistance dd: model.getAdiacenti(2)) {
			System.out.println(dd);
		}
	}

}
