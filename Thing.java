/**
 *  File: Thing.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: Create a superclass that describes the basic data structure
 *  		 and methods of objects in the SeaPort project series
 */


import java.util.Scanner;

public class Thing implements Comparable<Thing>{

	// name of Thing object
	private String name;

	// index of Thing object
	private int index;

	// index of parent of a Thing object
	private int parent;
	
	
	
	/**
	 * Constructor: creates Thing object
	 * @param name		Name of object
	 * @param index		index of object
	 * @param parent	parent index of object
	 */
	public Thing(String name, int index, int parent)
	{
		this.name = name;
		this.index = index;
		this.parent = parent;
	}

	
	/**
	* Creates Thing object from Scanner object
	* @param sc			Scanner object containing data to be added 
	* 					to Thing object
	*/
	public Thing(Scanner sc)
	{
		if (sc.hasNext()) {
			this.name = sc.next();
		}		
		if (sc.hasNextInt()) {
			this.index = sc.nextInt();
		}		
		if (sc.hasNextInt()) {
			this.parent = sc.nextInt();
		}
	}
	
	
	/**
	* Returns parent index of Thing object
	* @return		 parent index
	*/
	protected int getParent() { 
		return parent;
	}
	
	/**
	* Returns index of Thing object
	* @return		index of Thing object
	*/
	protected int getIndex() { 
		return index; 
	}
	
	
	/**
	* Returns name of Thing object
	* @return		name of Thing object
	*/
	protected String getName() { 
		return name;
	}
	
	
		
	
	/**
	 * Determines if the Thing object calling the compareTo method is less than,
	 * equal, or greater than
	 * @param that     Thing object to compare with
	 * @return 			Returns integer to indicate comparison between objects
	 * 					Positive integer = calling object is greater than object passed to the parameter
	 * 					Zero = both are equal
	 * 					Negative integer = calling object is less than object passed to the parameter
	 */
	@Override
	public int compareTo(Thing that) {		
		
		int compare;
		
		// names of both objects are compared first
		compare = (this.name).compareTo(that.name);
		if ( compare != 0 )  {
			return compare;
		}
		
		// if the names are equal, then the indices of both objects are equal
		compare = Integer.compare(this.index, that.index);
		if ( compare != 0 )  {
			return compare;		
		}
		
		// if names and indices are equal, then the parent indices are compared
		compare = Integer.compare(this.parent, that.parent);
		if ( compare != 0 ) {
			return compare;		
		}
			
		return compare;
	}
	
	
		
	/**
	 * Returns boolean that determines if current object contains a provided search term
	 * in a chosen field
	 * @param findThis     search term
	 * @param field        field that is tested for equality with search term
	 * @return 				boolean (true) stating that the two arguments match
	 */
	public boolean match(String findThis, String field)
	{	    
	    switch (field)
	    {
	        case "name":  { if (findThis.equals( this.name.toLowerCase()     ))  return true; break;}
	        case "index": { if (findThis.equals( String.valueOf(this.index ) ))  return true; break;}
	        case "parent":{ if (findThis.equals( String.valueOf(this.parent) ))  return true; break;}	        
	    }
	    return false;
	}
	
		
	
	/**
	 * Prints the fields of a Thing object
	 */
	@Override
	public String toString()
	{
		String str = name + " ";
		str += String.valueOf(index) + " ";	
		str += String.valueOf(parent);		
		
		return str;
	}	
}

