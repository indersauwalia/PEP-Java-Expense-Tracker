import java.time.LocalDate;
import java.util.ArrayList;

public class DataSeeder {
    public static void initialize(ArrayList<Transaction> transactions) {
        transactions.add(new Transaction("Income", 60000, "Monthly Salary", LocalDate.of(2024, 1, 1)));
        transactions.add(new Transaction("Income", 15000, "Freelance Work", LocalDate.of(2024, 1, 15)));
        transactions.add(new Transaction("Income", 60000, "Monthly Salary", LocalDate.of(2024, 2, 1)));
        
        transactions.add(new Transaction("Expense", 2500, "Groceries", LocalDate.of(2024, 1, 5)));
        transactions.add(new Transaction("Expense", 1200, "Internet Bill", LocalDate.of(2024, 1, 10)));
        transactions.add(new Transaction("Expense", 5000, "Electricity Bill", LocalDate.of(2024, 1, 20)));
        transactions.add(new Transaction("Expense", 3000, "Dining Out", LocalDate.of(2024, 2, 5)));
        transactions.add(new Transaction("Expense", 1500, "Movie Tickets", LocalDate.of(2024, 2, 14)));
        
        System.out.println("--- Dummy data loaded successfully! ---");
    }
}
