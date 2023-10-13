package model;

import java.util.LinkedList;
import java.util.List;
import controller.InputValidation;

/**
 * @author Ishita
 * 
 *         Implements amount-based filtering.
 * 
 */
public class AmountFilter implements TransactionFilter {
    private double amount;

    public AmountFilter(double amount) {
        if (InputValidation.isValidAmount(amount)) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException(amount + " is not a valid amount.");
        }

    }
    /**
     * This method filters a list of transactions with respect to the amount.
     * 
     */
    public List<Transaction> filter(List<Transaction> list) {
        List<Transaction> results = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            Transaction t = list.get(i);
            boolean isCorrectAmount = (amount==t.getAmount());
            if (isCorrectAmount) {
                results.add(t);
            }
        }
        return results;
    }
}
