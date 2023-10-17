package controller;

import view.ExpenseTrackerView;

import java.util.LinkedList;
import java.util.List;

import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import model.Transaction;
import model.TransactionFilter;
import javax.swing.JOptionPane;

public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }

    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[] { t.getAmount(), t.getCategory(), t.getTimestamp() });
    refresh();
    return true;
  }

  // Other controller methods
  public List<Transaction> applyFilter() {
    List<Transaction> transactions = model.getTransactions();
    Double amountFilterInput = view.getAmountFilterField();
    String categoryFilterInput = view.getCategoryFilterField();
    try {
      if (amountFilterInput != null) {
        AmountFilter amountFilter = new AmountFilter(view.getAmountFilterField());
        transactions = amountFilter.filter(transactions);
      }
      if (categoryFilterInput != null) {
        CategoryFilter categoryFilter = new CategoryFilter(view.getCategoryFilterField());
        transactions = categoryFilter.filter(transactions);
      }
    } catch (IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(view, "Please check your filtering options.");
      transactions = new LinkedList<Transaction>();
    }
    return transactions;
  }
}