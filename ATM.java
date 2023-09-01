import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

    public void closeAccount(String userID) {
        if (balances.get(userID) == null) {
            throw new ArithmeticException("ID doesn't match any open account.");
        }
        if (balances.get(userID) != 0) {
            throw new ArithmeticException("Balance needs to be 0 to close account.");
        }
        balances.put(userID, null);
    }

    public double checkBalance(String userID) {
        if (balances.get(userID) == null) {
            throw new ArithmeticException("ID doesn't match any open account.");
        }
        return balances.get(userID);
    }

    public double depositMoney(String userID, double amount) {
        if (balances.get(userID) == null) {
            throw new ArithmeticException("ID doesn't match any open account.");
        }
        if (amount < 0) {
            throw new ArithmeticException("Amount can't be a negative number.");
        }
        balances.put(userID, balances.get(userID) + amount);
        return balances.get(userID);
    }

    public double withdrawMoney(String userID, double amount) {
        if (balances.get(userID) == null) {
            throw new ArithmeticException("ID doesn't match any open account.");
        }
        if (amount < 0) {
            throw new ArithmeticException("Amount can't be a negative number.");
        }
        if (balances.get(userID) - amount < 0.0) {
            throw new ArithmeticException("Don't have enough money.");
        }
        balances.put(userID, balances.get(userID) - amount);
        return balances.get(userID);
    }

    public boolean transferMoney(String fromUserID, String toUserID, double amount) {
        if (balances.get(fromUserID) == null) {
            throw new ArithmeticException("Sender ID doesn't match any open account.");
        }
        if (balances.get(toUserID) == null) {
            throw new ArithmeticException("Reciever ID doesn't match any open account.");
        }
        if (amount < 0) {
            throw new ArithmeticException("Amount can't be a negative number.");
        }
        if (balances.get(fromUserID) - amount < 0.0) {
            return false;
        }
        balances.put(fromUserID, balances.get(fromUserID) - amount);
        balances.put(toUserID, balances.get(toUserID) + amount);
        return true;
    }

    public void audit() throws IOException {
        FileWriterReader writer = new FileWriterReader();
        String output = "";
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            output += key + " balance: " + value + "\n";
            // do something with key and/or tab
        }
        writer.writeFileMethod(output, "AccountAudit.txt");
    }
}