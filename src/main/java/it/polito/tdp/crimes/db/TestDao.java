package it.polito.tdp.crimes.db;

import it.polito.tdp.crimes.model.Event;

public class TestDao {

	public static void main(String[] args) {
		EventsDao dao = new EventsDao();
		//for(Event e : dao.listAllEvents())
			//System.out.println(e);
		for(Integer i: dao.getYears()){
			System.out.println(i);
		}
		System.out.println(dao.getLonMedia(2014, 2));
	}

}
