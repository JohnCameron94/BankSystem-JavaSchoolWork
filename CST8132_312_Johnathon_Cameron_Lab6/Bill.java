package dateNight;

import java.util.ArrayList;
import java.util.HashMap;
import cst8132.restaurant.Appetizer;
import cst8132.restaurant.Drink;
import cst8132.restaurant.MenuItem;
/**
 * Class: BIll
 * @author jon_cameron
 * 
 * Class: Bill
 * Description: This Class is used to calculate the guests bill. With either happy hour discount or not depending if the 
 * happy hour discount is false or true;
 *
 */
public class Bill {
	
	private HashMap<String, ArrayList<MenuItem>> orders = new HashMap<String, ArrayList<MenuItem>>(4);

	private boolean isHappyHour = false;
	private double subtotal;
	private double hstRate = 0.15;
	private final int MAX_MENU_ITEM_LENGTH = 30;
	private double hst;

	
	/**
	 * 
	 * @param guest String that will be there during the date night and what they have ordered.
	 * @param item MenuItem type, item type ordered by guests
	 * @return boolean true after the order items are placeOrder method
	 * Method adding items to the order for each guest
	 */
	public boolean addOrderItem(String guest, MenuItem item) {

		ArrayList<MenuItem> o = orders.getOrDefault(guest, new ArrayList<MenuItem>(4));
		o.add(item);

		orders.put(guest, o);
		
		subtotal += item.getPrice();
		
			
		
		return true;
	}
	
	/**
	 * 
	 * @param isHappyHour boolean that either true or false if the dinner is at 6:00pm then happy hour is true else happy hour is false.
	 * Setter method setting the happy hour time to that the date will go for supper.
	 */

	public void setHappyHour(boolean isHappyHour) {
		this.isHappyHour = isHappyHour;

	}
	/**
	 * 
	 * @return HappyHourDiscount
	 * Method used to get the happy hour discount of 1/2 price drinks and appetizer
	 */

	public double getHappyHourDiscount() {

		double happyHourDiscount = 0;

		if (!isHappyHour)
			return 0;

		for (ArrayList<MenuItem> a : orders.values()) {

			for (MenuItem m : a) {

				if (m instanceof Drink) {
					happyHourDiscount += 2;
				}

				if (m instanceof Appetizer) {
					happyHourDiscount += m.getPrice() / 2;
				}

			}

		}

		return happyHourDiscount;
	}
	
	/**
	 * 
	 * @return subtotal regarding if happy hour discount was applied or not.
	 * Method is used to get the orders from every guests and adding the total value of the bill
	 */
	
	public HashMap<String, ArrayList<MenuItem>> getOrders(){
		return orders;
	}
	
	public double getSubtotal() {
		return this.subtotal;
		
	}
	
	public double getHst() {
		
		
		 hst = hstRate * subtotal;
		return hst ;
		
		
	}
	
	public double getTotal() {
		if(!isHappyHour) 
			return subtotal+hst;
		else 
			return subtotal-getHappyHourDiscount() + hst;
		
	}

	/**
	 * @Overrides toString method
	 * Method used to print the bill and total amount into a string based on guests orders
	 * 
	 */


	public String toString() {

		String s = "";
		String format = "\t%-" + MAX_MENU_ITEM_LENGTH + "s \t $%6.2f\n";

		for (String o : orders.keySet()) {

			s += "Dinner Guest: " + o + "\n";

			for (MenuItem item : orders.get(o)) {
				s += String.format(format, item.getName(), item.getPrice());
			}

			s += "\n";

		}

		s += String.format(format, "Subtotal", getSubtotal());
		s += String.format(format, "Happy Hour Discount", getHappyHourDiscount());
		s += String.format(format, "HST " + (int) (hstRate * 100) + "%", getHst());

		s += String.format(format, "Total", getTotal());

		return s;
	}

}
