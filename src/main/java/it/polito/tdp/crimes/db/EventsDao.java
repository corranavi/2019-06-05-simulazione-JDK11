package it.polito.tdp.crimes.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Event;



public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> getYears(){
		List<Integer> result=new ArrayList<>();
		String sql="SELECT DISTINCT YEAR(reported_date) as year " + 
				"FROM EVENTS " + 
				"ORDER BY EVENTS.reported_date";
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet res=st.executeQuery();
			while(res.next()) {
				result.add(res.getInt("year"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Integer> getDistrictIds(){
		List<Integer> result=new ArrayList<>();
		String sql="SELECT DISTINCT district_id " + 
				"FROM EVENTS " + 
				"ORDER BY district_id";
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet res=st.executeQuery();
			while(res.next()) {
				result.add(res.getInt("district_id"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Double getLatMedia(Integer year, Integer v) {
		final String sql="SELECT AVG(geo_lat)AS latMedia " + 
				"FROM EVENTS " + 
				"WHERE YEAR(reported_date)=? AND district_id=?";
		Double latMedia=null;
		try {Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, year);
			st.setInt(2, v);
			ResultSet res=st.executeQuery();
			if(res.first()) { //Devo avere solo una riga
				latMedia=res.getDouble("latMedia");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return latMedia;
	}
	
	public Double getLonMedia(Integer year, Integer v) {
		final String sql="SELECT AVG(geo_lon)AS lonMedia " + 
				"FROM EVENTS " + 
				"WHERE YEAR(reported_date)=? AND district_id=?";
		Double lonMedia=null;
		try {Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, year);
			st.setInt(2, v);
			ResultSet res=st.executeQuery();
			if(res.first()) { //Devo avere solo una riga
				lonMedia=res.getDouble("lonMedia");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lonMedia;
	}
	
	public List<Integer> getMonth(Integer year){
		final String sql="SELECT DISTINCT MONTH(reported_date) as mese from events "+
	                     "where YEAR(reported_date)=?";
		List<Integer> result=new ArrayList<>();
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet res=st.executeQuery();
			while(res.next()) {
				result.add(res.getInt("mese"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public List<Integer> getDay(Integer year){
		final String sql="SELECT DISTINCT DAY(reported_date) as giorno from events "+
	                     "where YEAR(reported_date)=?";
		List<Integer> result=new ArrayList<>();
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet res=st.executeQuery();
			while(res.next()) {
				result.add(res.getInt("giorno"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
