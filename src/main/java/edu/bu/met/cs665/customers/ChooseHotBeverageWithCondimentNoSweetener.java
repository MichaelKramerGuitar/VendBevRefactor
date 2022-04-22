package edu.bu.met.cs665.customers;

import edu.bu.met.cs665.beverages.Coffee;
import edu.bu.met.cs665.beverages.CondimentException;
import edu.bu.met.cs665.beverages.HotBeverage;
import edu.bu.met.cs665.beverages.Tea;
import java.util.Arrays;

/**
 * The purpose of this class is to be a concrete command in the Command Pattern.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class ChooseHotBeverageWithCondimentNoSweetener implements Choice {

  private Customer customer;
  private HotBeverage beverage;

  public ChooseHotBeverageWithCondimentNoSweetener(Customer customer, HotBeverage beverage) {
    this.customer = customer;
    this.beverage = beverage;
  }

  /**
   * The purpose of this method is to override the implemented Choose interface
   * choosing a Beverage and size with a condiment but no sweetener.
   */
  @Override
  public HotBeverage choose() {
    customer.chooseBeverage();
    boolean isCoffee =
        Arrays.stream(menu.getCoffeeChoices()).anyMatch(customer.getChoice()::equals);
    boolean isTea =
        Arrays.stream(menu.getTeaChoices()).anyMatch(customer.getChoice()::equals);
    if (isCoffee) {
      beverage = new Coffee();
      beverage.setBrewTime(23);
    } else if (isTea) {
      beverage = new Tea();
      beverage.setBrewTime(21);
    }
    beverage.setType(customer.getChoice());
    customer.chooseSize();
    beverage.setSize(customer.getSizeChoice());
    customer.chooseCondiment();
    try {
      beverage.addCondiment(customer.getCondiment(), customer.getCondimentUnits());
    } catch (CondimentException e) {
      System.out.println(e);
    }

    return beverage;
  }
}
