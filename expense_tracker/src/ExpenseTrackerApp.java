import java.util.LinkedList;
import java.util.List;
import java.util.Locale.Category;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {

    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Reset color renderer
      view.colorTableRows(new LinkedList<Integer>(), null);
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);

      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    view.getFilterTransactionBtn().addActionListener(e -> {
      List<Transaction> transactions = model.getTransactions();
      // Remove former filter operations
      controller.refresh();
      // Get filtered transactions
      List<Transaction> filtered = controller.applyFilter();
      // Get indices of filtered rows
      List<Integer> filteredIndices = new LinkedList<>();
      for (int i = 0; i < transactions.size(); i++) {
        Transaction t = transactions.get(i);
        for (Transaction f : filtered) {
          // TODO: After we have fixed the creation of new Transaction instances:
          // change this comparison to t == f
          if (t.equals(f)) {
            filteredIndices.add(i);
          }
        }
      }
      System.out.println(filteredIndices);

      // Overwrite renderer for row colors
      view.colorTableRows(filteredIndices, new Color(173, 255, 168));
    });

    // Create test entries
    // TODO: Remove this after testing is over!
    double i = 0;
    for (String label : InputValidation.VALIDWORDS) {
      double amount = ++i;
      String category = label;
      controller.addTransaction(amount, category);
    }
  }
}