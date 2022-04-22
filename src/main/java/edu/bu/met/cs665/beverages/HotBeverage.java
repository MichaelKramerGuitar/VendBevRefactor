package edu.bu.met.cs665.beverages;

import edu.bu.met.cs665.customers.Choice;
import edu.bu.met.cs665.store.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The purpose of this class is to extend the Beverage class and provide the
 * additional functionality of adding a condiment to this hot beverage such as
 * milk or sugar (or any other).
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class HotBeverage extends Beverage {

  private HashMap<String, Integer> condiments = new HashMap<String, Integer>();
  private Choice choice;
  private int condimentCount = 0; // cannot be > 6
  private int brewTime; // in seconds round up
  private String type;

  // getters

  public int getBrewTime() {
    return brewTime;
  }

  public Choice getChoice() {
    return choice;
  }

  // setters

  public void setBrewTime(int brewTime) {
    this.brewTime = brewTime;
  }

  public void setChoice(Choice choice) {
    this.choice = choice;
  }

  /**
  * The purpose of this method is to add a condiment to a hot beverage.
  * <p>Precondition: an object extending HotBeverage has been instantiated</p>
  * <p>Postcondition: the condiment along with appropriate units has been added</p>
  * @param condiment a String, flexible but examples: "milk", "sugar"
  * @param condimentUnits int between 1 and 3
  */
  public void addCondiment(String condiment, int condimentUnits) throws CondimentException {
    if (condimentUnits > 3 || condimentUnits < 1) {
      throw new CondimentException("Error: " + condiment
        + " units must be between 1 and 3");
    }
    if (condimentCount == 6) {
      throw new CondimentException("Error: cannot add " + condiment
                                     + ". Max condiments in Beverage reached.");
    }
    Menu menu = new Menu();
    boolean goodCondiment = Arrays.stream(menu.getCondiments()).anyMatch(condiment::equals)
                            || Arrays.stream(menu.getSweeteners()).anyMatch(condiment::equals);
    if (goodCondiment && condimentCount < 6) {
      this.condiments.put(condiment, condimentUnits);
      condimentCount += condimentUnits; // track condimentUnits in beverage
    }
  }

  public HashMap<String, Integer> getCondiments() {
    return (HashMap<String, Integer>) condiments.clone();
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Prints the beverages description according to the condiments it's tracking.
   */
  public String toString() {
    String description = "[DEFAULT]";
    if (type != null) {
      if (condiments.size() == 0) {
        description = type.toUpperCase();
        return description;
      } else if (condiments.size() == 1) {
        Object condiment = condiments.keySet().toArray()[0];
        Object units = condiments.values().toArray()[0];
        description =
          type.toUpperCase() + " with " + units.toString() + " units of " + condiment.toString();
        return description;
      } else if (condiments.size() == 2) {
        Object condiment1 = condiments.keySet().toArray()[0];
        Object units1 = condiments.values().toArray()[0];
        Object condiment2 = condiments.keySet().toArray()[1];
        Object units2 = condiments.values().toArray()[1];
        description =
          type.toUpperCase() + " with " + units1.toString() + " units of " + condiment1.toString()
            + " and "
            + units2.toString() + " units of " + condiment2.toString();
        return description;
      }
    }
    return description;
  }
}
