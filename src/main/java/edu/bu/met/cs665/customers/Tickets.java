package edu.bu.met.cs665.customers;

import edu.bu.met.cs665.beverages.HotBeverage;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to maintain a List of "Tickets", Customers
 * Choices to be made into orders such that a history is maintained and an
 * order can be "undone". It is part of the command pattern.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class Tickets {

  private List<Choice> history = new ArrayList<Choice>();

  /**
   * Modeled after the professor's example repo. This example makes sense,
   * it is an alternative to providing an Array of Commands, iterating through
   * and executing separately in constructor and execute methods respectively.
   * In this case, it makes sense to add to the history array list and execute
   * one order at a time. This example also returns the customized beverage
   * for printing.
   * @param beverage HotBeverage order to execute.
   */
  public HotBeverage placeOrder(HotBeverage beverage) {
    this.history.add(beverage.getChoice());
    beverage = beverage.getChoice().choose();
    return beverage;
  }

  /**
   * Important to the Command pattern is the ability to undo a command. Not
   * currently being used, but included to complete the design pattern.
   * @param beverage HotBeverage order to execute.
   */
  public void undoOrder(HotBeverage beverage) {
    Choice target = beverage.getChoice();
    if (history.contains(beverage.getChoice())) {
      for (int i = 0; i < history.size(); i++) {
        if (history.get(i).equals(beverage.getChoice())) {
          target = history.remove(i);
          System.out.println("we're undoing this choice " + target);
        }
      }
    }
  }
}
