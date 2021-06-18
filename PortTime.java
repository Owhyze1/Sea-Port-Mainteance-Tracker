/**
 *  File: PortTime.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: A class that provides the time at Port.
 */



public class PortTime {

    // time at SeaPort object
	private int time;
	
	
	
	
	/**
	 * Creates PortTime object
	 * @param militaryTime		time in military format (0000)
	 */
	public PortTime(int militaryTime)
	{
		time = militaryTime;
	}
	
	
	
	/**
	 * Prints time in PortTime object
	 */
	@Override
	public String toString() { 
		return "Time: " + time; 
	}
}
