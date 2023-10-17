package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction {

  private double amount;
  private String category;
  private String timestamp;

  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  // Protected constructor for copying transaction
  protected Transaction(double amount, String category, String timestamp) {
    this.amount = amount;
    this.category = category;
    this.timestamp = timestamp;
  }

  public double getAmount() {
    return amount;
  }

  public String getCategory() {
    return category;
  }

  public String getTimestamp() {
    return timestamp;
  }

  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    return sdf.format(new Date());
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other.getClass() != this.getClass())
      return false;
    Transaction t = (Transaction) other;
    return this.amount == t.getAmount() &&
        this.category == t.getCategory() &&
        this.timestamp == t.getTimestamp();
  }
}