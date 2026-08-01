import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Transaction {
    String type;
    double amount;
    String description;
    LocalDate date;

    public Transaction(String type, double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return date + " | " + type + " | Rs." + amount + " | " + description;
    }
}

public class ExpenseTracker {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        DataSeeder.initialize(transactions);

        while (true) {
            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Remove Transaction");
            System.out.println("4. View Balance & Summary (Monthly/Yearly)");
            System.out.println("5. View All Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    addTransaction("Income");
                    break;
                case 2:
                    addTransaction("Expense");
                    break;
                case 3:
                    removeTransaction();
                    break;
                case 4:
                    viewSummary();
                    break;
                case 5:
                    viewTransactions();
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    return; // Exit the loop and end program
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addTransaction(String type) {
        System.out.print("Enter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Transaction cancelled.");
            return;
        }

        System.out.print("Enter description (e.g. Salary, Groceries): ");
        String description = scanner.nextLine();

        System.out.print("Enter date (yyyy-MM-dd) or leave blank for today: ");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.now(); // Default to today
        
        if (!dateString.trim().isEmpty()) {
            try {
                date = LocalDate.parse(dateString, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Using today's date instead.");
            }
        }

        // Create a new Transaction object and add it to our list
        Transaction newTransaction = new Transaction(type, amount, description, date);
        transactions.add(newTransaction);
        
        System.out.println(type + " added successfully!");
    }

    private static void removeTransaction() {
        viewTransactions();
        if (transactions.isEmpty()) return;

        System.out.print("\nEnter the transaction number to remove: ");
        try {
            // Subtract 1 because our list is 0-indexed, but display is 1-indexed
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (index >= 0 && index < transactions.size()) {
                transactions.remove(index);
                System.out.println("Transaction removed successfully.");
            } else {
                System.out.println("Invalid transaction number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        
        System.out.println("\n--- All Transactions ---");
        for (int i = 0; i < transactions.size(); i++) {
            // Print the index (1-based) and the transaction details
            System.out.println((i + 1) + ". " + transactions.get(i).toString());
        }
    }

    private static void viewSummary() {
        double totalIncome = 0;
        double totalExpense = 0;

        System.out.print("Enter year (e.g. 2024) to filter, or leave blank for all-time: ");
        String yearStr = scanner.nextLine();
        int filterYear = -1; // -1 means no filter
        
        if (!yearStr.trim().isEmpty()) {
            try {
                filterYear = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Showing all-time summary.");
            }
        }
        
        int filterMonth = -1; // -1 means no filter
        if (filterYear != -1) {
            System.out.print("Enter month (1-12) to filter, or leave blank for the whole year: ");
            String monthStr = scanner.nextLine();
            if (!monthStr.trim().isEmpty()) {
                try {
                    filterMonth = Integer.parseInt(monthStr);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid month. Showing whole year summary.");
                }
            }
        }

        // Loop through all transactions and calculate totals based on filters
        for (Transaction t : transactions) {
            // Skip if the year doesn't match the filter
            if (filterYear != -1 && t.date.getYear() != filterYear) {
                continue; 
            }
            // Skip if the month doesn't match the filter
            if (filterMonth != -1 && t.date.getMonthValue() != filterMonth) {
                continue;
            }

            if (t.type.equals("Income")) {
                totalIncome += t.amount;
            } else {
                totalExpense += t.amount;
            }
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Total Income: Rs." + totalIncome);
        System.out.println("Total Expense: Rs." + totalExpense);
        System.out.println("Net Balance: Rs." + (totalIncome - totalExpense));
    }
}
