# Console Expense Tracker

A simple, console-based Java application to track your daily expenses and income. This project was designed to be easy to understand and explain.

## Features

- **Add Income**: Record money you've received.
- **Add Expense**: Record money you've spent.
- **Remove Transaction**: Delete a transaction if you made a mistake.
- **View All Transactions**: See a complete history of all your entries.
- **View Summary**: See your total income, total expenses, and net balance. You can also filter the summary by a specific year or month.
- **Dummy Data**: Comes with some pre-loaded data for easy testing (handled by `DataSeeder.java`).

## How to Compile and Run

1. Open your terminal or command prompt.
2. Navigate to the project directory.
3. Compile the Java files:
   ```bash
   javac ExpenseTracker.java DataSeeder.java
   ```
4. Run the application:
   ```bash
   java ExpenseTracker
   ```

## Structure

- `ExpenseTracker.java`: The main application containing the user menu, input handling, and the `Transaction` class.
- `DataSeeder.java`: A helper class that loads some initial dummy data when the program starts.
