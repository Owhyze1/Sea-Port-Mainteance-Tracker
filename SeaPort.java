/**
 *  File: SeaPort.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: A class that defines the Docks and Ships located at a SeaPort and 
 *  		 provides methods for searching the SeaPort object
 */


import java.util.ArrayList;
import java.util.Scanner;



public class SeaPort extends Thing {

	// docks at the port
	private ArrayList<Dock> docks = new ArrayList<Dock>();

	// ships waiting to dock at the port
	protected ArrayList<Ship> que = new ArrayList<Ship>();

	// all ships at the port
	private ArrayList<Ship> ships = new ArrayList<Ship>();

	// skilled people at the port
	private ArrayList<Person> persons = new ArrayList<Person>();


	
	
	
	

	
	
	/**
	 * Creates SeaPort object from Scanner object
	 * 
	 * MUST be read in following order: name, index, parent
	 */
	public SeaPort(Scanner sc) 
	{
		super(sc);
	}






	/**
	 * Adds Dock object to SeaPort
	 * @param d		Dock to be added
	 */
	public void setDock(Dock d) { 
		docks.add(d);
	}


	/**
	 * Adds Ship object to que ArrayList of ships waiting to dock 
	 * at SeaPort object
	 * @param q		Ship to be added
	 */
	public void setQue(Ship q) { 
		que.add(q); 
	}


	/**
	 * Adds Ship object to ships ArrayList of all ships at the SeaPort
	 * @param s		Ship to be added
	 */
	public void setShip(Ship s) { 
		ships.add(s); 
	}


	/**
	 * Adds Person object to SeaPort
	 * @param p		Person to be added
	 */
	public void setPersons(Person p) {
		persons.add(p); 
	}
	
	

	/**
	 * Returns a copy of the ArrayList of Ship objects waiting to dock
	 * @return	list of ships waiting to dock
	 */
	protected ArrayList<Ship> getQue() {
		ArrayList<Ship> copy = new ArrayList<Ship>();
		
		for (Ship s : que) {
			copy.add(s);
		}		
		return copy;
	}

	
	/**
	 * Returns a copy of the ArrayList of all Ship objects at the Seaport
	 * @return	list of ships waiting to dock
	 */
	protected ArrayList<Ship> getShips() {
		ArrayList<Ship> copy = new ArrayList<Ship>();
		
		for (Ship s : ships) {
			copy.add(s);
		}		
		return copy;
	}



	/**
	 * Returns all Dock objects with a specified field that matches a provided search term
	 * @param findThis     search term
	 * @param field        field in Dock object that needs to be searched
	 */
	public ArrayList<String> searchDocks(String findThis, String field)
	{
		// list of Dock objects that match the search term
		ArrayList<String> matches = new ArrayList<String>();	   

		// if the search term (findThis) matches the value in the field of 
		// the Dock object, the Dock object is added to the list of matches found
		for (Dock d : docks)  {
			if( d.match(findThis, field) )  { 
				matches.add( "Dock: " + d.getName() );
			}
		}
		return matches;
	}


	/**
	 * Returns all Ship objects with a specified field that matches a provided search term
	 * @param findThis     search term
	 * @param field        field in Ship object that needs to be searched
	 */
	public ArrayList<String> searchShips(String findThis, String field)
	{
		// list of Ship objects with a field value that matches the search term
		ArrayList<String> matches = new ArrayList<String>();	   

		// if the current Ship object from the ArrayList of Ship objects has a field 
		// value that matches the search term (findThis) in the specified field, the 
		// Ship object is added to the ArrayList (matches)
		for (Ship s : ships)  {
			if( s.match(findThis, field) )  { 
				matches.add( "Ship: " + s.getName() );
			}
		}
		return matches;
	}


	/**
	 * Returns all Person objects with a specified field that matches a provided search term
	 * @param findThis     search term
	 * @param field        field in Person object that needs to be searched
	 */
	public ArrayList<String> searchPersons(String findThis, String field)
	{
		// list of Person objects with a field value that matches the search term (findThis)
		ArrayList<String> matches = new ArrayList<String>();	   

		// if the current Person object has value in its specified field (field) that matches
		// the search term (findThis), the Person object is added to the ArrayList (matches)
		for (Person p : persons)  {
			if( p.match(findThis, field) )  { 
				matches.add( "Person: " + p.getName() );
			}
		}
		return matches;
	}


	/**
	 * Prints the Dock objects, Ship objects, and Person objects at
	 * a SeaPort object
	 */
	@Override
	public String toString()
	{
		String str = "SeaPort: " + super.toString() + "\n\n";

		// display all docks
		for (Dock d : docks)
			str += d.toString();

		// display all ships waiting to dock
		str += "\n\nAll ships in que: \n";
		for (Ship q : que)
			str += q.toString();

		str += "\n";
		
		// display all ships at the port
		str += "\n\nAll ships at port: \n";	
		for (Ship s : ships)
			str += s.toString();

		// display all persons at the port
		str += "\n\nAll persons: \n";
		for (Person p : persons)
			str += p.toString();

		return str;
	}
}