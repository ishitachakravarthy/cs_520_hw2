# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## New functionality
As part of the homework, we implemented the following functionalities:

- The getter method for the list of transactions now returns a deep copy of all transactions for immutability.
- Filtering for amount and category was added. Rows that match the filter inputs are highlighted in green. Through extensive testing, all major exceptions have been identified and are being properly handled.


## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.