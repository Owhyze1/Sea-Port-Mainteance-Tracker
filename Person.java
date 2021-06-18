/**
 *  File: Person.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: A class that defines the Skill of a Person
 */



import java.util.Scanner;


public class Person extends Thing {

    // a person's professional skill 
	private String skill;
	
	
	
	
	
	/**
	 * Creates a Person object from a Scanner object
	 * @param	Scanner object containing data needed to create
	 * 			a Person object
	 */
	public Person(Scanner sc)
	{
		super(sc);
		if (sc.hasNext()) {
			skill = sc.next();
		}
	}
	
	
	public String getSkill() {
		return skill;
	}
   
	
    /**
     * Prints the fields of a Person object
     */
	@Override
	public String toString()
	{
		String str = "  Person: ";	
		str += super.toString() + " ";
		str += skill + "\n";
		
		return str;
	}
}
