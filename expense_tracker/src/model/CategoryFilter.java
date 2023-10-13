package model;

import java.util.LinkedList;
import java.util.List;
import controller.InputValidation;

/**
 * @author Mattis
 * 
 *         Implements category-based filtering.
 * 
 */
public class CategoryFilter implements TransactionFilter {
    private String category;

    public CategoryFilter(String category) {
        if (InputValidation.isValidCategory(category)) {
            this.category = category;
        } else {
            throw new IllegalArgumentException(category + " is not a valid category.");
        }

    }

    /**
     * This method filters a list of transactions with respect to the category.
     * 
     */
    public List<Transaction> filter(List<Transaction> list) {
        List<Transaction> results = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            Transaction t = list.get(i);
            boolean isCorrectCategory = category.equals(t.getCategory());
            if (isCorrectCategory) {
                results.add(t);
            }
        }
        return results;
    }
}
