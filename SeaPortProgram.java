
/** 
 *  File: SeaPortProgram.java
 *  Date: 04/10/2017
 *  Author: Tiffany Wise
 *  
 *  Purpose:  Create a GUI for displaying and searching and sorting the contents
 *  of a World object 
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class SeaPortProgram extends JFrame {
	// World object created from user-chosen file
	private World world;

	// displays contents of World object and search results
	private JTextArea displayArea;

	// user-selected object field used in searching
	private String searchThisField;

	// user-selected object type used in searching
	private String searchThisObject;
	
	
	/**
	 * Builds GUI using a border layout
	 */
	public SeaPortProgram() {
		setTitle("Project 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// searching a World object (components: text field, label, radio
		// buttons, and button)
		add(southPanel(), BorderLayout.SOUTH);

		// open file and display world (components: buttons)
		add(northPanel(), BorderLayout.NORTH);

		// displaying World object and search results (components: text area,
		// scroll bar)
		add(centerPanel(), BorderLayout.CENTER);

		// list for sorting ships in que
		add(eastPanel(), BorderLayout.EAST);
		
		// panel for job progress
		add(westPanel(), BorderLayout.WEST);

		pack();
		setVisible(true);
	}

	/**
	 * Creates the north panel of the border layout which contains buttons for
	 * allowing the user to select a file that will be used to create a World
	 * object and for displaying the World object
	 */
	private JPanel northPanel() {

		// panel that contains two JButton components
		JPanel openFilePanel;

		// displays the World object in a display area
		JButton displayWorldButton;

		// opens a dialog box so that the user can select a file
		JButton openFileButton; // JFileChooser

		// formatting for buttons
		final int FONT_SIZE = 20;
		final String FONT_TYPE = "SansSerif";

		openFilePanel = new JPanel();

		openFileButton = new JButton("Open File");
		openFileButton.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));

		displayWorldButton = new JButton("Show the World");
		displayWorldButton.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));

		// Action Listener that allows user to choose file
		openFileButton.addActionListener(a -> {

			// catch NullPointerException error if user selects "Cancel" instead
			// of
			// choosing a file
			try {
				world = new World(askForFile());
				displayArea.setText("File Scanned...\n\nThe World is Ready ");				
			} catch (NullPointerException error) {
				displayArea.setText("World was not created. Please select a file.");
			}
		});

		// Action Listeners for displaying World object
		displayWorldButton.addActionListener(e -> {

			// verify that the World object contains data before displaying
			if (world != null) {
				displayArea.setText(world.toString());
			} else if (world == null) {
				JOptionPane.showMessageDialog(null, "Select \"Open File\" to choose a file");
			}
		});

		// add buttons to panel
		openFilePanel.add(openFileButton);
		openFilePanel.add(displayWorldButton);

		return openFilePanel;
	}

	/**
	 * Creates the south panel of the border layout which contains the
	 * components for searching a World object: text field and its label for
	 * user input, two radio button groups for selecting an object's field and
	 * type, and a search button
	 */
	private JPanel southPanel() {

		// main panel to be added to south panel of border layout
		JPanel searchPanel;

		// two nested panels used to create a border around two sets of radio
		// buttons to emphasize
		// to the user the need to select one option in each category
		JPanel nestedSearchFieldPanel;
		JPanel nestedSearchObjectPanel;

		// text field for user input and its corresponding label
		JTextField searchTextField;
		JLabel enterSearchTermLabel;

		// formatting for text field component
		final int TEXT_FIELD_SIZE = 10;
		final int TEXT_FIELD_FONT_SIZE = 12;
		final String FONT_TYPE = "SansSerif";

		// search button used to initiate search of World object
		JButton searchButton;

		// first radio button group and 3 corresponding radio buttons for user
		// to
		// select the field (name, index, job skill) of the object to be
		// searched
		ButtonGroup searchParametersGroup;
		JRadioButton nameRadio;
		JRadioButton indexRadio;
		JRadioButton skillRadio;

		// second radio button group and 4 corresponding radio buttons for the
		// user to
		// select which type of object (Ship, Dock, Person, SeaPort) to search
		ButtonGroup searchObjectsGroup;
		JRadioButton shipRadio;
		JRadioButton dockRadio;
		JRadioButton personRadio;
		JRadioButton seaPortRadio;

		// Label and Text field for entering search terms
		enterSearchTermLabel = new JLabel("Enter search term: ");
		searchTextField = new JTextField(TEXT_FIELD_SIZE);
		searchButton = new JButton("Search");
		searchButton.setFont(new Font(FONT_TYPE, Font.BOLD, TEXT_FIELD_FONT_SIZE));

		// Radio Buttons for selecting which field (name, index, skill) of the
		// object to search
		searchParametersGroup = new ButtonGroup();
		nameRadio = new JRadioButton("Name");
		indexRadio = new JRadioButton("Index");
		skillRadio = new JRadioButton("Parent");

		// add field selection radio buttons to button group
		searchParametersGroup.add(nameRadio);
		searchParametersGroup.add(indexRadio);
		searchParametersGroup.add(skillRadio);

		// Radio buttons for selecting which object type (Ship, Dock, Person,
		// SeaPort) to search
		searchObjectsGroup = new ButtonGroup();
		shipRadio = new JRadioButton("Ship");
		dockRadio = new JRadioButton("Dock");
		personRadio = new JRadioButton("Person");
		seaPortRadio = new JRadioButton("Port");

		// add object selection radio buttons to button group
		searchObjectsGroup.add(shipRadio);
		searchObjectsGroup.add(dockRadio);
		searchObjectsGroup.add(personRadio);
		searchObjectsGroup.add(seaPortRadio);

		// a nested panel containing the radio buttons which fields of the
		// object to search
		nestedSearchFieldPanel = new JPanel();
		nestedSearchFieldPanel.setBorder(BorderFactory.createTitledBorder("Choose a Field"));
		nestedSearchFieldPanel.add(nameRadio);
		nestedSearchFieldPanel.add(indexRadio);
		nestedSearchFieldPanel.add(skillRadio);

		// a nested panel containing the radio buttons for object types to be
		// searched
		nestedSearchObjectPanel = new JPanel();
		nestedSearchObjectPanel.setBorder(BorderFactory.createTitledBorder("Choose an Object"));
		nestedSearchObjectPanel.add(shipRadio);
		nestedSearchObjectPanel.add(dockRadio);
		nestedSearchObjectPanel.add(personRadio);
		nestedSearchObjectPanel.add(seaPortRadio);

		// Panel that contains search options for the world object: the label,
		// text field, two radio button
		// groups (for field and object type selection), and a search button
		searchPanel = new JPanel();
		searchPanel.add(enterSearchTermLabel);
		searchPanel.add(searchTextField);
		searchPanel.add(nestedSearchFieldPanel);
		searchPanel.add(nestedSearchObjectPanel);
		searchPanel.add(searchButton);

		// Action Listeners for radio buttons for field selection
		nameRadio.addActionListener(e -> {
			searchThisField = e.getActionCommand().toLowerCase();
		});
		indexRadio.addActionListener(e -> {
			searchThisField = e.getActionCommand().toLowerCase();
		});
		skillRadio.addActionListener(e -> {
			searchThisField = e.getActionCommand().toLowerCase();
		});

		// Action listeners for radio buttons for object type selection
		shipRadio.addActionListener(e -> {
			searchThisObject = e.getActionCommand().toLowerCase();
		});
		dockRadio.addActionListener(e -> {
			searchThisObject = e.getActionCommand().toLowerCase();
		});
		personRadio.addActionListener(e -> {
			searchThisObject = e.getActionCommand().toLowerCase();
		});
		seaPortRadio.addActionListener(e -> {
			searchThisObject = e.getActionCommand().toLowerCase();
		});

		// Action listener for performing the search
		searchButton.addActionListener(e -> {

			// before searching, verify that the World object is not null and
			// that the user
			// has selected the object's field and object's type

			if (world == null) {
				JOptionPane.showMessageDialog(null, "Choose a file to create the world");
			} else if (searchThisField == null || searchThisObject == null) {
				JOptionPane.showMessageDialog(null, "Select a field and an object type");
			}
			// with user input selected, begin searching world object
			else {

				// key word entered by user
				String searchTerm = searchTextField.getText().toLowerCase();

				// list of objects that match search input
				ArrayList<String> searchResults = new ArrayList<String>();

				// display user input and selected options
				String display = "Searching For: " + searchTerm;
				display += "\nIn this field: " + searchThisField;
				display += "\nOf This Object: " + searchThisObject;
				display += "\n\n";


				// list of ports needed to search for a ship, dock, or person
				// (if selected by user)
				ArrayList<SeaPort> ports = world.getPorts();

				// search base on chosen object type
				switch (searchThisObject) {
				case "port": {
					searchResults.addAll( world.searchPorts(searchTerm, searchThisField) );
					break;
				}
				case "ship": {

					for (SeaPort sp : ports)
						searchResults.addAll( sp.searchShips(searchTerm, searchThisField) );
					break;
				}
				case "dock": {

					for (SeaPort sp : ports)
						searchResults.addAll( sp.searchDocks(searchTerm, searchThisField) );
					break;
				}
				case "person": {

					for (SeaPort sp : ports)
						searchResults.addAll( sp.searchPersons(searchTerm, searchThisField) );
					break;
				}
				} // end switch block

				// display results of search
				if (searchResults.size() == 0) {
					display += "No matches found";
				} else {
					for (String eachMatch : searchResults) {
						display += eachMatch + "\n";
					}
				}
				displayArea.setText(display);
			} // end if-else block
		});

		return searchPanel;
	} // end southPanel() method

	/**
	 * Creates the center panel of the border layout which contains the display
	 * area for outputting the data contained in a World object as well as the
	 * results from a search of a World object
	 */
	private JScrollPane centerPanel() {

		// scroll pane used to create scroll bar
		JScrollPane scroll;

		// formatting for text area component
		final int TEXT_AREA_ROWS = 30;
		final int TEXT_AREA_COLUMNS = 80;
		final int TEXT_AREA_FONT_SIZE = 12;
		final String TEXT_AREA_FONT = "Monospaced";
		final int TEXT_AREA_STYLE = Font.PLAIN;

		// formatting for scroll pane
		final int SCROLLPANE_WIDTH = 300;
		final int SCROLLPANE_HEIGHT = 100;

		// create text area component
		displayArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
		displayArea.setFont(new Font(TEXT_AREA_FONT, TEXT_AREA_STYLE, TEXT_AREA_FONT_SIZE));
		displayArea.setEditable(false);

		// create scroll plane
		scroll = new JScrollPane(displayArea);
		scroll.setSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		return scroll;
	}




	/**
	 * Creates a JPanel that contains a JList of Sort Options for Ship objects waiting
	 * to dock. The user can choose to sort by ship's length, weight, width, or draft.
	 * The results will display in the display area
	 * 
	 * @return	JPanel containing a JList component
	 */
	private JPanel eastPanel() {

		JPanel sortPanel = new JPanel();
		JList<String> sortList;

		// Formatting for JList component
		final int FONT_SIZE = 16;
		final int FONT_WEIGHT = Font.BOLD;
		final String FONT_TYPE = "SansSerif";


		String[] sortOptions = { 
				"Sort By Ship Weight",
				"Sort By Ship Width",
				"Sort By Ship Length",
				"Sort By Ship Draft"
		};

		sortList = new JList<String>(sortOptions);
		sortList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
		sortList.setFont( new Font(FONT_TYPE, FONT_WEIGHT, FONT_SIZE) );

		sortList.addListSelectionListener( e -> {

			// display message if the World object has not been created
			if ( world == null ){ 
				JOptionPane.showMessageDialog(null, "The World has not been created yet. Select a file."); 
			}
			// sort Ship objects in que using Comparators
			else {

				// type of sort chosen by user
				int option = sortList.getSelectedIndex();

				// list of SeaPorts in a World Object
				ArrayList<SeaPort> ports = world.getPorts();

				// list of Ship objects waiting to dock at a SeaPort
				ArrayList<Ship> portQue = new ArrayList<Ship>();

				// String object displaying sorted list of Ship objects
				String display = "";



				for (SeaPort sp : ports) {

					portQue = sp.getQue();

					switch (option)
					{
					// no sort option chosen
					case -1: { break; }

					// Comparator: Ship Weight 
					case  0: {

						// lambda expression to initiate instance of Comparator by ship weight
						portQue.sort( 

								(Ship s1, Ship s2) -> {
									return (   s1.getWeight()  ).compareTo(  s2.getWeight()  );
								});


						// display sorted list
						display += "\n\nSorted by Weight in Port: " + sp.getName() + "\n\n";
						for (Ship s1 : portQue) {
							display += "Ship: " + s1.getName();
							display += "\t\tWeight: " + s1.getWeight();
							display += "\n";
						}
						break;}


					// Comparator: Ship Width
					case  1: { 

						// lambda expression for Comparator using ship width
						portQue.sort( 

								(Ship s1, Ship s2) -> {									
									return (  s1.getWidth()  ).compareTo(  s2.getWidth()  );
								});

						// display sorted list
						display += "\n\nSorted by Width in Port: " + sp.getName() + "\n\n"; 
						for (Ship s1 : portQue) {
							display += "Ship: " + s1.getName();
							display += "\t\tWidth: " + s1.getWidth();
							display += "\n";
						}
						break;
					}
					//Comparator: ship length 
					case  2: {
						// lambda expression for Comparator of ship length
						portQue.sort( 

								(Ship s1, Ship s2) -> {
									return (  s1.getLength()  ).compareTo(  s2.getLength()  );
								});

						// display sorted list
						display += "\n\nSorted by Length in Port: " + sp.getName() + "\n\n";
						for (Ship s1 : portQue) {
							display += "Ship: " + s1.getName();
							display += "\t\tLength: " + s1.getLength();
							display += "\n";
						}
						break;}
					// Comparator: ship draft
					case  3: {

						// lambda expression for Comparator with ship draft
						portQue.sort( 

								(Ship s1, Ship s2) -> {
									return (  s1.getDraft()  ).compareTo(  s2.getDraft()  );
								});

						// display sorted list
						display += "\n\nSorted by Draft in Port: " + sp.getName() + "\n\n";
						for (Ship s1 : portQue) {
							display += "Ship: " + s1.getName();
							display += "\t\tDraft: " + s1.getDraft();
							display += "\n";
						}
						break;}
					} // end switch statement
				} // end for statement		

				displayArea.setText(display);
			} // else statement
		}); // end List Selection Listener


		sortPanel.setBorder(BorderFactory.createTitledBorder("Sort Ships Waiting to Dock"));
		sortPanel.add( sortList );

		return sortPanel;
	}




	
	
	private JPanel westPanel() {
		
		// progress bar showing job progress
		JProgressBar jobProgress = new JProgressBar(0, 100);		
		jobProgress.setValue(0);
		jobProgress.setStringPainted(true);
		jobProgress.getPercentComplete();
				
		
		// buttons to suspend and cancel the current job
		JButton pauseButton = new JButton("Pause Job");
		JButton cancelButton = new JButton("Cancel Job");		
	
		JPanel buttonPanel = new JPanel();		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(pauseButton);
		buttonPanel.add(cancelButton);
		
		// text area for remaining jobs
		JTextArea taRemain = new JTextArea(10,20);
		taRemain.setEditable(false);
		taRemain.setBorder(BorderFactory.createTitledBorder("Remaining Jobs"));
		JScrollPane scrollRemain = new JScrollPane(taRemain);
		scrollRemain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// text area for completed jobs
		JTextArea taComplete = new JTextArea(10,20);
		taComplete.setEditable(false);		
		taComplete.setBorder(BorderFactory.createTitledBorder("Completed Jobs"));
		JScrollPane scrollComplete = new JScrollPane(taComplete);
		scrollComplete.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		
		
		
		
	
		// panel containing all components for job progress
		JPanel west = new JPanel();
		west.setLayout( new GridLayout(4,1));		
		west.add(jobProgress);
		west.add(buttonPanel);
		west.add(scrollRemain);
		west.add(scrollComplete);
		
		
		return west;
	}

	/**
	 * Displays a file chooser allowing the user to choose a file
	 * 
	 * @return File object chosen
	 */
	private File askForFile() {
		// create File and JFileChooser objects to select a file from directory
		File chosenFile = null;
		JFileChooser pickFile = new JFileChooser(".");
		int choice = pickFile.showOpenDialog(null);

		if (choice == JFileChooser.APPROVE_OPTION) {
			chosenFile = pickFile.getSelectedFile();
		}

		return chosenFile;
	}

	/**
	 * Method to initialize GUI
	 */
	public static void main(String[] args) {
		new SeaPortProgram();		
	}
}