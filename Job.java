
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JProgressBar;


public class Job extends Thing implements Runnable{

	// expected time to complete job
	private double duration;
	
	// required skills to perform the job
	private ArrayList<String> requirements = new ArrayList<String>();
	
	private JProgressBar pbar;
	
	
	/**
	 * Creates Job object from a Scanner object
	 * MUST read Scanner in this order: name index parent duration skill 
	 * (There may be more than one skill)
	 * 
	 * @param scan		Scanner object containing information about Job
	 * @param startJob	boolean that starts job if true
	 */
	public Job(Scanner scan, boolean startJob) {
		
		// job name, index, and parent index
		super(scan);
		
		// job duration
		if (scan.hasNextDouble()) {
			duration = scan.nextDouble();
		}
				
		// add job requirements
		while (scan.hasNext()) {			
			requirements.add( scan.next() );
		}			
		
		// start thread if ship is docked
		if (startJob) {
			new Thread(this).start();
		}		
	}
	
	
	
	
	
	/**
	 * Displays fields of a Job object
	 */
	@Override
	public String toString() {
		
		String display = "\n\tJob: \n\t\t";
		
		display += this.getName();
		display += "\tDuration: " + duration;
		display += "\n\t\tRequirements: ";
				
		for (String r : requirements) {
			display += r + " ";
		}
		
		display += "\n";
		
		return display;
	}



	@Override
	public void run() {
					
		pbar = new JProgressBar(0, 100);		
		pbar.setValue(0);
		pbar.setStringPainted(true);
		pbar.getPercentComplete();
		
		for (int i = 0; i < duration; i++)
		{
			pbar.setValue(i);			
		}		
		
		
	}
	
		
}
