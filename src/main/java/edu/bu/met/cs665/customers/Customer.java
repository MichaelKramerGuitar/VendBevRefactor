package edu.bu.met.cs665.customers;

import edu.bu.met.cs665.Main;
import edu.bu.met.cs665.beverages.Coffee;
import edu.bu.met.cs665.beverages.CondimentException;
import edu.bu.met.cs665.beverages.HotBeverage;
import edu.bu.met.cs665.beverages.Tea;
import edu.bu.met.cs665.employees.Barista;
import edu.bu.met.cs665.store.Menu;
import edu.bu.met.cs665.store.Order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * The purpose of this class is to realize the concept of a person who
 * orders the beverage.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class Customer {

  private Random rand = new Random();
  private String name;
  private String choice;
  private String condiment;
  private int condimentUnits;
  private String sweetener;
  private int sweeterUnits;
  private String sizeChoice;
  private Menu menu = new Menu();

  // constructor
  public Customer(String name) {
    this.name = name;
  }

  // getters
  public String getName() {
    return name;
  }

  public String getCondiment() {
    return condiment;
  }

  public int getCondimentUnits() {
    return condimentUnits;
  }

  public String getSweetener() {
    return sweetener;
  }

  public int getSweetenerUnits() {
    return sweeterUnits;
  }

  public String getChoice() {
    return choice;
  }

  public String getSizeChoice() {
    return sizeChoice;
  }

  /**
   * The purpose of this method is to randomly select a beverage from the menu
   * simulating a customer's beverage choice.
   */
  public void chooseBeverage() {
    int upperbound = menu.getMenu().length;
    int choiceIndex = rand.nextInt(upperbound);
    this.choice = menu.getMenu()[choiceIndex];
  }

  /**
   * The purpose of this method is to simulate a customer randomly chosing a
   * size of beverage from the menu.
   */
  public void chooseSize() {
    int upperbound = menu.getSizes().length;
    int choiceIndex = rand.nextInt(upperbound);
    this.sizeChoice = menu.getSizes()[choiceIndex];
  }

  /**
   * The purpose of this method is to add a condiment to a beverage randomly
   * simulating customer behavior.
   */
  public void chooseCondiment() {
    int upperbound = menu.getCondiments().length;
    int choiceIndex = rand.nextInt(upperbound);
    this.condiment = menu.getCondiments()[choiceIndex];
    // generate random int between 1 and 3
    this.condimentUnits = (int) ((Math.random() * (3 - 1)) + 1);

  }

  /**
   * The purpose of this method is to choose a sweetener for this beverage
   * randomly simulating customer behavior.
   */
  public void chooseSweetener() {
    int upperbound = menu.getSweeteners().length;
    int choiceIndex = rand.nextInt(upperbound);
    this.sweetener = menu.getSweeteners()[choiceIndex];
    this.sweeterUnits = (int) ((Math.random() * (3 - 1)) + 1);
  }

  /**
   * The purpose of this class is to simulate the brewing process when a
   * Customer vends from the machine.
   */
  public static class OrderBeverage implements Runnable {

    private static Logger logger = Logger.getLogger(Main.class);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private String[] names = new String[]{"Michael", "Glennys", "Maya", "Akiva"};

    @Override
    public void run() {
      LocalDateTime now = LocalDateTime.now();
      int upperbound = names.length;
      // gets a random name from names
      HotBeverage beverage = new HotBeverage();
      Customer customer =
          new Customer(names[(int) ((Math.random() * (upperbound - 1)) + 1)]);
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
      // adds the customers choice to the ticket history and customizations to beverage
      beverage = tickets.placeOrder(beverage);
      Barista barista = new Barista(); // vending machines internal agent
      boolean ready;
      try {
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
        // approximate brewing time divided by three
        Thread.currentThread().sleep(beverage.getBrewTime() * 1000 / 3);
        ready = true;
        order.setStatus(ready);
        System.out.println(customer.getName() + " Your "
            + customer.getChoice() + " is ready.");
        logger.info(customer.getName() + "'s order complete.");
      } catch (InterruptedException e) {
        System.out.println(e);
      }
    }
  }
}
