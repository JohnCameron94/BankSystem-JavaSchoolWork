package dateNight;

import cst8132.restaurant.MenuItem;
/**
 * 
 * @author jon_cameron
 *@Version 1.0
 *Class used to extend Menu Item of type Entree
 *
 */

public class Entree extends MenuItem{

	/**
	 * 
	 * @param name Of the entree item
	 * @param price double price of the item, of type entree
	 * Entree Constructor calling super and adding name and price to the type of meal entree.
	 */
	public Entree(String name, double price) {
		super(name,price);
	}
	
	
}
