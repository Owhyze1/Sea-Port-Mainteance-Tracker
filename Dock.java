/**
 *  File: Dock.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: A class that provides information on Ships located at a Dock
 */

import java.util.Scanner;

public class Dock extends Thing {

    // ship located at dock
	private Ship ship;
	
	// index of ship at dock
	private int shipIndex;
	
	
	
	/**
	 * Creates Dock object from Scanner object
	 * @param sc	Scanner object containing data for Dock object
	 */
	public Dock(Scanner sc)
	{
		super(sc);
		
		if (sc.hasNextInt()) {
			shipIndex = sc.nextInt();
		}
	}
	
		
    
    /**
     * Adds a Ship Object to the Dock object
     * @param s		Ship to be added
     */
    public void setShip(Ship s) {
    	ship = s;
    }
    
    /**
     * Returns index of Ship object in a Dock object
     * @return		ship object index
     */
    public int getShipIndex()
    {
    	return shipIndex;
    }
	
	
	
	/**
	 * Prints fields of a Ship Object
	 */
	@Override
	public String toString()
	{
		// display Dock object
		String str = "\tDock: ";
		str += super.toString();
		
		// display Ship located at the Dock object
		str += "\n\t  Ship: ";
		str += ship.toString();
		str += "\n";
		
		return str;
	}
}

