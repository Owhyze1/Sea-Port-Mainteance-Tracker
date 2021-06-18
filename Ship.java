/**
 *  File: Ship.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: A class that defines the specifications of a Ship, including its weight,
 *  		 length, width, and draft, as well as the Ships port and dock arrival times.
 */


import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends Thing {

    // time that Ship arrived at port
	private PortTime arrivalTime;
	
	// time that Ship docked at port
	private PortTime dockTime;
	
    // weight of ship
	private double weight;
	
    // length of ship
	private double length;
	
    // width of ship
	private double width;
	
    // draft of ship
	private double draft;
	
	// available jobs on Ship object
	private ArrayList<Job> jobs = new ArrayList<Job>();
	
		
	
	
	

	/**
	 * Creates Ship object from Scanner object
	 * 
	 * MUST read Scanner object in this order: name index parent weight length width draft
	 * 
	 * @param sc	Scanner object containing data needed to create Ship object
	 */
	public Ship(Scanner sc)
	{
		super(sc);
		
		if (sc.hasNextDouble()) {
			weight = sc.nextDouble();
		}
		if (sc.hasNextDouble()) {
			length = sc.nextDouble();
		}
		if (sc.hasNextDouble()) {
			width = sc.nextDouble();		
		}
		if (sc.hasNextDouble()) {
			draft = sc.nextDouble();	
		}
	}

	    
	/**
	 * Returns arrival time of Ship object at a Seaport object
	 * @return		ship arrival time
	 */
	public PortTime getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Returns dock time of Ship object at a Dock object
	 * @return		time ship docked
	 */
	public PortTime getDockTime() {
		return dockTime;
	}

	/**
	 * Returns weight of ship
	 * @return		ship weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * Returns length of ship
	 * @return		ship length
	 */
	public Double getLength() {
		return length;
	}

	/**
	 * Returns width of ship
	 * @return		ship width
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * Returns draft of ship
	 * @return		ship draft
	 */
	public Double getDraft() {
		return draft;
	}
	
	
	
	
	/**
	 * Adds jobs to a ship
	 * @param j		Job object to add
	 */
	public void setJob(Job j) {
		jobs.add(j);
	}
	
	
	/**
	 * Returns list of jobs on ship
	 * @return		jobs list
	 */
	public ArrayList<Job> getJob() {
		
		// create deep copy of ArrayList of Job objects
		ArrayList<Job> copy = new ArrayList<Job>();
		
		for (Job j : jobs) {
			copy.add(j);
		}
		
		return copy;			
	}
		
	

	/**
	 * Prints ship's name, index, and parent index
	 */
	@Override
	public String toString()
	{	
		//return super.getName() + " " + super.getIndex();
		
		String display = super.getName() + " " + super.getIndex() + " ";
		
		
		for (Job j: jobs) {
			display += j.toString();
		}
		
		return display;
	}
}
