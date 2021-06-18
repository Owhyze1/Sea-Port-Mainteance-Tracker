/**
 *  File: CargoShip.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: A class that defines additional specifications of a Cargo Ship: the value of
 *  		 its cargo, the volume of cargo, and cargo weight
 */


import java.util.Scanner;

public class CargoShip extends Ship {

    // value of cargo (in US dollars) on ship
	private double cargoValue;
	
	// volume of cargo on ship
	private double cargoVolume;
	
	// weight of cargo on ship
	private double cargoWeight;
	
	
	
    /**
	 * Creates CargoShip object from Scanner object
	 * MUST be read in this order: 
	 * name index parent weight length width draft cargoWeight cargoVolume cargoValue
	 * 
	 * @param sc	Scanner object containing data needed to create CargoShip object
	 */
	public CargoShip(Scanner sc)
	{
		super(sc);
		
		if (sc.hasNextDouble()) {
			cargoWeight = sc.nextDouble();
		}
		if (sc.hasNextDouble()) {
			cargoVolume = sc.nextDouble();
		}
		if (sc.hasNextDouble()) {
			cargoValue = sc.nextDouble();
		}		
	}
	
	/**
	 * Returns value of cargo
	 * @return		cargo value
	 */
	public double getCargoValue() {
		return cargoValue;
	}
	
	/**
	 * Returns volume of cargo
	 * @return		cargo volume
	 */
	public double getCargoVolume() {
		return cargoVolume;
	}
	
	/**
	 * Returns weight of cargo
	 * @return		cargo weight
	 */
	public double getCargoWeight() {
		return cargoWeight;
	}

   
    /**
	 * Prints Cargo ship's name, index, and parent index
	 */
	@Override
	public String toString()
	{	
		// display type of ship
		String str = "  Cargo Ship: ";
		
		// display ships's name, index, and parent index
		str += super.toString();
		str += "\n";
		
		return str;
	}
	
	
}

