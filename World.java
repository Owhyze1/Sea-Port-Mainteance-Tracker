/**
 *  File: World.java
 *  Date: 4/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose: The World object contains methods that will read an input file 
 *  		 to define the Docks, Ships, and Persons belonging to the SeaPort(s)
 *  		 of a World object 
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class World extends Thing {

	// seaports in the word
	private ArrayList<SeaPort> ports = new ArrayList<SeaPort>();

	// time
	private PortTime time;


	
	




	/**
	 * Creates World object from File object
	 */
	public World(File file)
	{
		// initializes an "empty" World object
		super("THE WORLD", 0, 0);
		setTime(null);

		
		// hash maps for SeaPort, Dock, Person, and Job objects
		HashMap<Integer, SeaPort> portMap   = new HashMap<>();
		HashMap<Integer, Dock> 	  dockMap   = new HashMap<>();
		HashMap<String,  Person>  personMap = new HashMap<>();		
		HashMap<Integer, Ship>	  shipMap	= new HashMap<>();
//		HashMap<Integer, Job>	  jobMap	= new HashMap<>();

	
		// start reading a File object to create a World object
		try {
			Scanner scan = new Scanner(file);
						
			while (scan.hasNext()) {
				
				switch (scan.next())
				{
				case "//": 	   scan.nextLine(); 												  break;
				case "port":   addPort  ( new SeaPort(scan), 	   portMap 			 		   ); break;
				case "dock":   addDock  ( new Dock(scan), 	 	   portMap, dockMap			   ); break;
				case "pship":  addShip  ( new PassengerShip(scan), portMap, dockMap,  shipMap  ); break;
				case "cship":  addShip  ( new CargoShip(scan),	   portMap, dockMap,  shipMap  ); break;
				case "person": addPerson( new Person(scan), 	   portMap, personMap		   ); break;				
				case "job":    addJob	( scan.nextLine(), 		   shipMap, dockMap			   ); break;					
				}	    // switch statement       	  
			} 			// end while statement			
		}				// end try statement
		catch (FileNotFoundException e) { 
			System.out.println("File not found"); 
		}				
	}


	
	/**
	 * Returns time in World object
	 * @return		Port Time object
	 */
	public PortTime getTime() {
		return time;
	}


	/**
	 * Sets time in World object
	 * @param time	Port Time object
	 */
	public void setTime(PortTime time) {
		this.time = time;
	}
	
	
	/**
	 * Adds a SeaPort object to a World object and adds each SeaPort object to
	 * a HashMap where the SeaPort index is the key and the SeaPort object is 
	 * the value in the HashMap
	 * @param port  SeaPort object to add
	 * @param map	HashMap with key = port index, value = SeaPort object
	 */
	private void addPort(SeaPort port, HashMap<Integer, SeaPort> map)  {
		
		// add SeaPort object to World object
		ports.add(port);
		
		// create HashMap with the SeaPort index as the key and the SeaPort object as the value
		map.put( port.getIndex(), port);
	}


	/**
	 * Adds Dock object to appropriate Port object using HashMaps. Creates a HashMap where
	 * Dock objects are the values and the index of the Dock objects are the key
	 * 
	 * @param dock  Dock object to be added to SeaPort object	
	 * @param pMap	HashMap with key = SeaPort index, value = SeaPort object
	 * @param dMap	HashMap with key = Dock index, value = Dock object	
	 */
	private void addDock(Dock dock, HashMap<Integer, SeaPort> pMap, HashMap<Integer, Dock> dMap)
	{				
		// find the SeaPort object whose index matches the parent index of the 
		// Dock object and add that Dock object to its matching SeaPort object		
		SeaPort matchingPort = pMap.get( dock.getParent() );
		
		if ( matchingPort != null ) {
			matchingPort.setDock( dock );
		}		
		
		// add dock to HashMap for Dock objects with its index as its key
		dMap.put( dock.getIndex(), dock);		
	}

	
	/**
	 * Ships (cargo or passenger) can be at the dock or at the port. Ships are added to the
	 * Dock or SeaPort object whose index matches the parent index of the Ship object. 
	 * 
	 * @param s		Ship to be added to dock or port
	 * @param pMap	HashMap: key = SeaPort index, value = SeaPort object
	 * @param dMap	HashMap: key = Dock index,    value = Dock object
	 */
	private void addShip(Ship s, HashMap<Integer, SeaPort> pMap, HashMap<Integer, Dock> dMap, HashMap<Integer, Ship> sMap)
	{
		// if a ship's parent index matches a dock's index, the ship is located at that dock.
		// Add the ship to the Dock object and add it to the list of all ships at the port
		// where the dock is located
		Dock d = dMap.get( s.getParent() );
		
		if ( d != null ) {
			d.setShip( s );	
			
			// find the dock's SeaPort using the dock's parent index. The dock parent index 
			// should match the index of its SeaPort. Add the ship to that sea port's 
			// ArrayList of all ships at port 
			SeaPort theDocksSeaPort = pMap.get( d.getParent() );
			
			if ( theDocksSeaPort != null ) {
				theDocksSeaPort.setShip(s);
			}
		}
		
		// if the ship's parent index does not match a dock index, it should match the index of
		// a port. Add the ship to the matching SeaPort's list of ships waiting in queue and its 
		// list of all ships at the port
		else if ( d == null ) {	
			SeaPort p = pMap.get( s.getParent() );
			if ( p != null ) {
				p.setShip(s);
				p.setQue(s);
			}				
		}			
		
		// create HashMap with ship index as the key and Ship object as the value
		sMap.put( s.getIndex(), s);
	}

	
	/**
	 * Adds Person object to the SeaPort object whose index matches the parent index of the 
	 * Person object. Creates HashMap whose keys are a Person object's skill are the keys and 
	 * the values are the Person object.
	 * 
	 * @param p     Person object to add to SeaPort
	 * @param spMap	HashMap:   key = SeaPort index,  value = SeaPort object
	 * @param pMap	PersomMap: key = Person's skill, value = Person object
	 */
	private void addPerson(Person p, HashMap<Integer, SeaPort> spMap, HashMap<String, Person> pMap)
	{
		// if the parent index of a Person object matches a SeaPort's index, the Person
		// object is at that SeaPort. Add the Person object to the SeaPort ArrayList of 
		// Person objects
		
		SeaPort port = spMap.get( p.getParent() );
		
		if ( port != null ) {
			port.setPersons(p);
		}		
		
		// create hash map with where the key is a person's skill and the value is the Person object
		pMap.put( p.getSkill(), p);		
	}

	
	
	/**
	 * Creates a Job object and determines if the Job is located on a docked Ship in order to start
	 * the job thread
	 * @param sc			Line of text file containing information for a Job object
	 * @param mapOfShips	HashMap of Ship objects used to assign Job to its ship
	 * @param dMap			HashMap of Dock objects to determine if ship is docked
	 */
	private void addJob(String sc, HashMap<Integer, Ship> mapOfShips, HashMap<Integer, Dock> dMap) 
	{	
		// create Thing object to obtain job parent index
		Thing jobThing;
		
		// Job object to be created from Scanner
		Job j;
		
		// Ship where job is needed
		Ship s;
		
		// Boolean that determines if a job can be started (ship must be docked)
		boolean dockedShip;
				
		
		
		// if the parent index of a Job matches the index of a Ship object, the Job object
		// is a Job at the Ship.
				
		jobThing = new Thing( new Scanner(sc));		
		s = mapOfShips.get(  jobThing.getParent() );

		
		// if parent index of Ship is a key value in the HashMap of Dock objects, the 
		// ship is located at a dock and the job can be started
		if ( s != null ) {			
			
			dockedShip = dMap.containsKey( s.getParent() );
			
			// create Job object and start the job if the ship is located at a dock
			j = new Job( new Scanner(sc), dockedShip );
			
			// add the Job object to the Ship object
			s.setJob(j);
		}		
	}
	
	
	
	
	
	/**
	 * Creates copy of ArrayList of SeaPort objects 
	 * @return		copy of the ArrayList of SeaPort objects in a World object
	 */
	protected ArrayList<SeaPort> getPorts()
	{
		ArrayList<SeaPort> p = new ArrayList<SeaPort>();

		for (SeaPort sp : ports) {
			p.add(sp);
		}
		return p;
	}


	/**
	 * Returns all SeaPorts with a specified field that matches a provided search term
	 * @param findThis     search term
	 * @param field        field in SeaPort that needs to be searched
	 */
	protected ArrayList<String> searchPorts(String findThis, String field)
	{
		ArrayList<String> matches = new ArrayList<String>();

		for (SeaPort p : ports) {	
			
			if( p.match(findThis, field) ) { 
				matches.add( "Sea Port: " + p.getName() );} 
		}
		return matches;
	}



	/**
	 * Prints the SeaPort objects in a World object
	 */
	@Override
	public String toString()
	{
		String str = super.getName() + "\n\n";

		for (SeaPort p : ports)
			str += p.toString() + "\n";

		return str;
	}
}