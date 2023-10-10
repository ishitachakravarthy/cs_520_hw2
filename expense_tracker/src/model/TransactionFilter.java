package model;

import java.util.List;

/**
 * @author Mattis
 * 
 *         Interface which can be implemented for different filtering
 *         operations.
 */
public interface TransactionFilter {
    List<Transaction> filter(List<Transaction> list);
}
