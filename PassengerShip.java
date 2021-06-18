/**
 *  File: PassengerShip.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: A class that defines the additional specifications of a Passenger 
 *  		 Ship: total number of rooms, number of rooms occupied, and the 
 *  		 number of passengers
 */


import java.util.Scanner;

public class PassengerShip extends Ship {

    // number of occupied rooms on ship
	private int numberOfOccupiedRooms;
	
	// number of people on ship
	private int numberOfPassengers;
	
	// total number rooms on ship
	private int numberOfRooms;
	
	
	
	
	
	/**
	 * Creates PassengerShip object from Scanner object 
	 * MUST be read in this order:  
	 * name index parent weight length width draft numPassengers numRooms numOccupied
	 * 
	 * @param sc	Scanner object with data needed to create PassengerShip object
	 */
	public PassengerShip(Scanner sc)
	{
		super(sc);
		
		if (sc.hasNextInt()) {
			numberOfOccupiedRooms = sc.nextInt();
		}
		if (sc.hasNextInt()) {
			numberOfPassengers = sc.nextInt();
		}
		if (sc.hasNextInt()) {
			numberOfRooms = sc.nextInt();
		}		
	}
	
	/**
	 * Returns number of occupied rooms on ship
	 * @return		number of occupied rooms
	 */
	public int getOccupiedRooms() {
		return numberOfOccupiedRooms;
	}
	
	
	/**
	 * Returns number of passengers on ship
	 * @return		number of passengers
	 */
	public int getPassengers() {
		return numberOfPassengers;
	}
		
	/**
	 * Returns number of rooms on ship
	 * @return		number of rooms
	 */
	public int getRooms() {
		return numberOfRooms;
	}
	
	
	
	/**
	 * Prints Passenger ship's name, index, and parent index
	 */
	@Override
	public String toString()
	{
		String str = "  Passenger Ship: ";
		str += super.toString();
		str += "\n";
		
		return str;
	}
}
