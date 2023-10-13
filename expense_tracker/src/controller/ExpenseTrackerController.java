package controller;

import view.ExpenseTrackerView;

import java.util.LinkedList;
import java.util.List;

import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import model.Transaction;
import model.TransactionFilter;

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
    AmountFilter amountFilter = new AmountFilter(view.getAmountFilterField());
    CategoryFilter categoryFilter = new CategoryFilter(view.getCategoryFilterField());
    List<Transaction> results = new LinkedList<>();
    results = categoryFilter.filter(amountFilter.filter(transactions));
    return results;
  }
}