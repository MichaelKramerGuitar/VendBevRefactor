package edu.bu.met.cs665.customers;

import edu.bu.met.cs665.beverages.HotBeverage;
import edu.bu.met.cs665.store.Menu;

/**
 * The purpose of this class is to implement the Command Pattern assembling
 * all of the details for a customers choices for an order.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public interface Choice {

  Menu menu = new Menu();

  HotBeverage choose();
}
