package edu.bu.met.cs665.beverages;

/**
 * The purpose of this class is to provide a framework for the idea of a beverage.
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public abstract class Beverage {

  private String size;
  private double price;

  // getters
  public String getSize() {
    return size;
  }

  public double getPrice() {
    return price;
  }

  // setters
  public void setSize(String size) {
    this.size = size;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public abstract String getType();

  public abstract void setType(String type);
}
