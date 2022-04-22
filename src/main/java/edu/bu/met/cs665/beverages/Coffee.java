package edu.bu.met.cs665.beverages;

/**
 * The purpose of this class is to provide a subclass of HotBeverage for coffee.
 * Different coffee types are denoted by condiments (i.e. espresso has no milk,
 * cappuccino is an espresso with milk.)
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class Coffee extends HotBeverage {

  // getters

  public String getType() {
    return super.getType(); // i.e. cappuccino, espresso etc.
  }

  // setters

  public void setType(String type) {
    super.setType(type);
  }
}
