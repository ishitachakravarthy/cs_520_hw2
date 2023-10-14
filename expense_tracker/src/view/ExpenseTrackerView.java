package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;

  private JFormattedTextField amountFilterField;
  private JComboBox<String> categoryFilterField;
  private JButton filterTransactionBtn;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = { "serial", "Amount", "Category", "Date" };

    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);

    // Text Fields and Label panel for Amount and Category:
    JLabel amountFilterLabel = new JLabel("AmountFilter:");
    amountFilterField = new JFormattedTextField(format);
    amountFilterField.setColumns(10);

    JLabel categoryFilterLabel = new JLabel("CategoryFilter:");
    categoryFilterField = new JComboBox<>(InputValidation.VALIDWORDS);
    categoryFilterField.setSelectedIndex(-1);
    categoryFilterField.insertItemAt("", 0);

    filterTransactionBtn = new JButton("Filter Transaction");

    // Panel for new filter fields
    JPanel inputPanelFilter = new JPanel();
    inputPanelFilter.add(amountFilterLabel);
    inputPanelFilter.add(amountFilterField);
    inputPanelFilter.add(categoryFilterLabel);
    inputPanelFilter.add(categoryFilterField);
    inputPanelFilter.add(filterTransactionBtn);

    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel);
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    // Add in new Filter Panel to existing panel
    inputPanel.add(inputPanelFilter);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);

    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Set frame properties
    setSize(1200, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  public void refreshTable(List<Transaction> transactions) {
    // Clear existing rows
    model.setRowCount(0);
    // Get row count
    int rowNum = model.getRowCount();
    double totalCost = 0;
    // Calculate total cost
    for (Transaction t : transactions) {
      totalCost += t.getAmount();
    }
    // Add rows from transactions list
    for (Transaction t : transactions) {
      model.addRow(new Object[] { rowNum += 1, t.getAmount(), t.getCategory(), t.getTimestamp() });
    }
    // Add total row
    Object[] totalRow = { "Total", null, null, totalCost };
    model.addRow(totalRow);

    // Fire table update
    transactionsTable.updateUI();

  }

  /**
   * This method takes a list of indices and colors the respective rows in the
   * provided color.
   */
  public void colorTableRows(List<Integer> indices, Color color) {
    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
          int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
          // If row is selected, get default color for this action
          Color defaultSelected = javax.swing.UIManager.getDefaults().getColor("Table.selectionBackground");
          c.setBackground(defaultSelected);
        } else if (indices.stream().anyMatch(i -> i == row)) {
          c.setBackground(color);
        } else {
          c.setBackground(Color.WHITE);
        }
        return c;
      }
    });
  }

  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  public JButton getFilterTransactionBtn() {
    return filterTransactionBtn;
  }

  public DefaultTableModel getTableModel() {
    return model;
  }

  // Other view methods
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if (amountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(amountField.getText());
      return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public double getAmountFilterField() {
    if (amountFilterField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(amountFilterField.getText());
      return amount;
    }
  }

  public String getCategoryFilterField() {
    return categoryFilterField.getSelectedItem().toString();
  }
}
