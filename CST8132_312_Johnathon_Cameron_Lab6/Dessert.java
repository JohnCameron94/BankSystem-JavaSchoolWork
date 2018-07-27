package dateNight;

import cst8132.restaurant.MenuItem;

/**
 * 
 * @author jon_cameron Class used to add a Dessert to the menu items.
 *
 */
public class Dessert extends MenuItem {
	
	/**
	 * 
	 * @param name String of the dessert
	 * @param price price of the String name dessert
	 * Constructor used to add desserts (name and price).
	 */
	public Dessert(String name, double price) {
		super(name, price);
	}

}
