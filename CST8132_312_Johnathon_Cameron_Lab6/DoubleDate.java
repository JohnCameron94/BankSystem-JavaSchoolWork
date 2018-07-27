package dateNight;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import cst8132.restaurant.Menu;
import cst8132.restaurant.Restaurant;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * 
 * @author jon_cameron
 * @version 2.0 Class used to simulate a double date between two couples with
 *          GUI's. DoubleDate Class extends the JFrame class to simulate the
 *          double date with a GUI frame.
 * 
 *
 */
public class DoubleDate extends JFrame {
	// Swing Components
	public JPanel inputPanel;
	public JPanel guestList;
	public JLabel addGuestPrompt;
	public JLabel guestListHeader;
	public JTextField newGuestName;
	public JButton addGuest;
	public JButton letsGo;

	// instance Variables
	private ArrayList<String> guests;
	private String[] movies;
	private String movieTitle;
	private int movieTime;
	private Restaurant restaurant;
	private Menu menu;
	private Bill bill;
	public static Random random;
	public static Formatter formatToFile;
	public static Scanner input;
	public static Formatter output;

	/**
	 * DoubleDate constructor The constructor instantiates all of the GUI components
	 * in the program. Initializing the String array of movies to movie names
	 * possibilities (3). Instantiates the bill object, and Array List of guests
	 */

	public DoubleDate() {

		// Instantiating the Swing Components
		super("Double Date");
		setLayout(new GridLayout(2, 2));
		getContentPane().setBackground(Color.darkGray);

		// Guest Prompt Header
		addGuestPrompt = new JLabel("Enter Guest Name");
		addGuestPrompt.setHorizontalAlignment(JLabel.CENTER);
		addGuestPrompt.setBorder(new EmptyBorder(5, 5, 5, 5));
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);
		addGuestPrompt.setBorder(border);
		addGuestPrompt.setFont(new Font("Serif", Font.BOLD, 14));
		addGuestPrompt.setForeground(Color.WHITE);
		add(addGuestPrompt);

		// Guest List Header
		guestListHeader = new JLabel("Guest List");
		guestListHeader.setHorizontalAlignment(JLabel.CENTER);
		guestListHeader.setBorder(border);
		guestListHeader.setFont(new Font("Serif", Font.BOLD, 14));
		guestListHeader.setForeground(Color.WHITE);
		add(guestListHeader);

		// Panel Borders
		Border border2 = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);
		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		inputPanel.setBorder(border2);
		inputPanel.setBackground(Color.darkGray);

		// TextField
		newGuestName = new JTextField(20);
		newGuestName.setForeground(Color.BLACK);
		newGuestName.setFont(new Font("Serif", Font.BOLD, 14));
		inputPanel.add(newGuestName);

		// Add Guest Button
		addGuest = new JButton("Add Guest to List");
		addGuest.addActionListener(new AddGuestHandler());
		inputPanel.add(addGuest);

		// Start Date Button
		letsGo = new JButton("Lets Go Out");
		letsGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goOnDate((DoubleDate) SwingUtilities.getRoot((Component) e.getSource()));
				setVisible(false);
				// frame.setVisible(true);
				dispose();
			}
		});
		// Button to panel
		inputPanel.add(letsGo);
		letsGo.setVisible(false);

		add(inputPanel);

		// Guest List Panel
		guestList = new JPanel();
		guestList.setLayout(new FlowLayout());

		guestList.setBorder(border2);
		guestList.setBackground(Color.darkGray);

		// Adding guest list to JFrame
		add(guestList);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 250);

		// ArrayList to a max size of 4
		guests = new ArrayList<String>(4);
		restaurant = Restaurant.getInstance();
		// Getting the menu
		menu = restaurant.getMenu();
		// Calling Bill default constructor
		bill = new Bill();

		// initializing 3 String Movie titles to the array of movies.
		movies = new String[3];
		movies[0] = "Red Sparrow";
		movies[1] = "Black Panther";
		movies[2] = "Player1";

	}

	/**
	 * 
	 * @param date
	 *            DoubleDate is the object that is run through the parameters of the
	 *            method This method is used to run the date for all four guests.
	 *            Either getting and Happy hour discount from the restaurant or not.
	 *            Also getting the specific times line for the movie and dinner.
	 *            Also a random generator will place a random order for each guest
	 *            member, one Appetizer will be bought and two desserts between
	 *            couples will be shared. Every guest gets an entrees. The Bill and
	 *            Menu is also exported to a filed named "Menu.txt" and "Bill.txt".
	 */

	public void goOnDate(DoubleDate date) {

		movieTitle = pickAMovie(movies);
		System.out.println(toString());

		boolean isHappyHour;
		if (movieTime == 10) {
			isHappyHour = true;
			bill.setHappyHour(isHappyHour);

		} else {
			isHappyHour = false;
			bill.setHappyHour(isHappyHour);
		}
		openMenuFile();
		addMenuItem();
		closeMenuFile();

		for (int i = 0; i < guests.size(); i++) {
			if (i == 0)
				placeOrder(guests.get(1), "Appetizers");

			placeOrder(guests.get(i), "Drinks");
			placeOrder(guests.get(i), "Entrees");

			if (i % 2 == 0)
				placeOrder(guests.get(i), "Desserts");

		}
		System.out.println(date);
		
		openBillFile();
		addBillToFile();
		closeBillFile();

	}

	/**
	 * 
	 * @param movies
	 *            array of string movies containing 3 movie titles
	 * @return a random movie title is returned from an array of movie titles This
	 *         method is used to randomly choose a movie title from the array of
	 *         string movies.
	 */

	public String pickAMovie(String[] movies) {

		// Initializing a random Movie Variable to find a random movie from movies ARRAY
		int randomMovie = new Random().nextInt(movies.length);

		// returning to String method from array.
		return movies[randomMovie].toString();

	}

	/**
	 * 
	 * @return movieTime that is randomly chosen from an array of two set times 6 or
	 *         10. Method is used to Get the movie showing to decide whether the
	 *         movie will be before of after dinner. Array of two set times is used
	 *         to hold both time values of 6:00pm or 10:00pm
	 */

	public int getShowing() {

		// Array of time values 6 or 10
		int[] times = { 6, 10 };

		// randomizing the times
		int randomTime = new Random().nextInt(times.length);
		movieTime = times[randomTime];

		return movieTime;

	}

	/**
	 * Method used to add Menu Items to the menu of the restaurant. 4 types of menu
	 * items will be added and 4 menu items of each type The types are
	 * entrees,appetizer,drinks,desserts
	 */
	public void openMenuFile() {
		try {
			input = new Scanner(Paths.get("Menu.txt"));
		} catch (IOException e) {
			System.err.println("Error Opening File");
			e.printStackTrace();
		}
	}

	public void addMenuItem() {

		try {
			while (input.hasNext()) {
				String itemType = input.next();
				String name = input.next();
				double price = input.nextDouble();

				menu.addMenuItem(itemType, name, price);
				input.nextLine();

			}
		} catch (NoSuchElementException elementException) {
			System.out.println("File Improprely Formed");
		} catch (IllegalStateException ilese) {
			System.err.println("Error reading File");
		}

	}

	public static void closeMenuFile() {
		if (input != null)
			input.close();
	}

	/**
	 * 
	 * @param guest
	 *            is the guest that will be placing the order
	 * @param itemType
	 *            item Type is is the type of item the guest is going to order,
	 *            drinks, desserts etc..
	 * @return boolean of random menu items to the guests bill
	 */

	public boolean placeOrder(String guest, String itemType) {

		// Collecting a random order of menu items, from a random guest
		return bill.addOrderItem(guest, menu.getRandomMenuItem(itemType));
	}

	/**
	 * 
	 * @param agrs
	 *            Main method instantiating the double date object, and setting the
	 *            object to visible to view the JFrame
	 */

	public static void main(String[] agrs) {

		// Adding Guests
		DoubleDate dateNight = new DoubleDate();
		dateNight.setVisible(true);
	

	}

	/**
	 * Graphic method calling to the super paint method
	 */

	public void paint(Graphics g) {
		super.paint(g);
	}

	/**
	 * @Override Overrides toString() Overriding the toString method, also in charge
	 *           to print into a string the plans for the date night. (Movie Time,
	 *           Happy Hour or not, also restaurant menu item and total bill.
	 * 
	 */

	public void openBillFile() {
		try {
			
			output = new Formatter("Bill.txt");
		} catch (FileNotFoundException e) {
			System.err.println("Error Opening File");
			e.printStackTrace();
		}
	}
	public void addBillToFile() {
		 
			output.format("%s",bill.toString());
			
		
		
	}
	public void closeBillFile() {
		if(output !=null) 
			output.close();
		
	}
		
		
	
	public String toString() {
		// To String Override, to print out Movie,Time,Happy hour or not, menu and bill.

		String m;
		String b;
		System.out.println("MOVIE Selected: " + movieTitle);

		System.out.println(("TIME: " + getShowing() + ":00 pm"));
		System.out.println();

		if (movieTime == 10) {
			System.out.println("We Will be going To see the movie after diner");
			System.out.println("=============================================");
			System.out.println("It's happy hour! $2 off drinks, and 1/2 price appetizers!!\n");
		} else {
			System.out.println("We will be going to the movie before Diner");
			System.out.println("=============================================");
			System.out.println("We'll be missing happy hour, but we'll still be happy!");

		}
		System.out.println();
		m = menu.toString();
		b = bill.toString();

		return m + b;

	}

	/**
	 * 
	 * @author jon_cameron Class used to implement action listeners for the buttons
	 *         and guestList
	 * 
	 *
	 */

	private class AddGuestHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			guests.add(newGuestName.getText());
			guestList.add(new JLabel(newGuestName.getText()));
			guestList.validate();
			guestList.repaint();
			guestList.setVisible(true);
			addGuest.setVisible(guests.size() < 4);
			letsGo.setVisible(true);
			newGuestName.setText("");

		}
	}
}
