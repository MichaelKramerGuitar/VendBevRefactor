package edu.bu.met.cs665;

import edu.bu.met.cs665.beverages.Coffee;
import edu.bu.met.cs665.beverages.CondimentException;
import edu.bu.met.cs665.beverages.HotBeverage;
import edu.bu.met.cs665.beverages.Tea;
import edu.bu.met.cs665.customers.*;
import edu.bu.met.cs665.employees.Barista;
import edu.bu.met.cs665.store.Menu;
import edu.bu.met.cs665.store.Order;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

public class MainTest {
  /*
  Because the tests are the focus of this assignment we'll log these as well
   */
  private static Logger logger = Logger.getLogger(Main.class);
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
  private String[] names = new String[]{"Michael", "Glennys", "Maya", "Akiva"};
  public MainTest() {}

  @Test
  public void TestCustomerOrderSimulation(){
    LocalDateTime now = LocalDateTime.now();
    int upperbound = names.length;
    // gets a random name from names
    HotBeverage beverage = new HotBeverage();
    Customer customer =
      new Customer(names[(int) ((Math.random() * (upperbound - 1)) + 1)]);
    // all possible high level choices
    Choice[] choices = new Choice[]{
      new ChooseHotBeverageWithCondimentAndSweetener(customer, beverage),
      new ChooseHotBeverageNoCondimentNoSweetener(customer, beverage),
      new ChooseHotBeverageWithCondimentNoSweetener(customer, beverage)
    };
    upperbound = choices.length; // reset upperbound
    // gets a random choice from choices
    final Choice choice = choices[(int) ((Math.random() * (upperbound - 1)) + 1)];
    beverage.setChoice(choice);
    final Tickets tickets = new Tickets();
    // adds the customers choice to the ticket history and returns the customized beverage
    beverage = tickets.placeOrder(beverage);
    Barista barista = new Barista(); // vending machines internal agent
    boolean ready;
    Order order = new Order(beverage);
    Map<String, Double> price = barista.calculatePrice(order);
    // much simpler than before
    logger.info(customer.getName() + " ordered a "
      + beverage
      + " TOTAL: "
      + "$" +  price.values().toString().replaceAll("[\\[\\]]", "")
      + " " + dtf.format(now));
    System.out.println(customer.getName() + " ordered a "
      + beverage
      + " TOTAL: "
      + "$" +  price.values().toString().replaceAll("[\\[\\]]", ""));
    ready = true;
    order.setStatus(ready);
    System.out.println(customer.getName() + " Your "
      + customer.getChoice() + " is ready.");
    logger.info(customer.getName() + "'s order complete.");
  }
}