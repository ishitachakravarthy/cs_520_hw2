package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerModel {

  private List<Transaction> transactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>();
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  public List<Transaction> getTransactions() {
    List<Transaction> transactionsCopy = new ArrayList<>();
    for (Transaction t : transactions) {
      double amount = t.getAmount();
      String category = t.getCategory();
      String timestamp = t.getTimestamp();
      transactionsCopy.add(new Transaction(amount, category, timestamp));
    }
    return transactionsCopy;
  }

}