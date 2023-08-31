import java.util.HashMap;

public class ATM {
    private HashMap<String, Double> balances;

    public ATM() {
        balances = new HashMap<>();
    }

    public void openAccount(String userID, double amount) {
        if (balances.get(userID) != null) {
            throw new ArithmeticException("ID is already linked to an account.");
        }
        if (amount < 0) {
            throw new ArithmeticException("Amount can't be a negative number.");
        }
        balances.put(userID, amount);
    }
}