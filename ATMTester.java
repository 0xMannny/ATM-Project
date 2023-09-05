import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ATMTester {

    public static void main(String[] args) {
        ATM bank = new ATM();
        ATM bank2 = new ATM();
        int workingFunctions = 0;
        int auditCount = 0;
        FileWriterReader reader = new FileWriterReader();

        try {
            bank.openAccount("user1@example.com", 1000);
            workingFunctions++;

            bank.openAccount("user2@example.com", 500);
            workingFunctions++;

            bank.depositMoney("user1@example.com", 200);
            workingFunctions++;

            bank.withdrawMoney("user2@example.com", 100);
            workingFunctions++;

            bank.transferMoney("user1@example.com", "user2@example.com", 150);
            workingFunctions++;

            System.out.println("Balance for user1@example.com: " + bank.checkBalance("user1@example.com")); // Should be
                                                                                                            // 1050.0
            workingFunctions++;

            System.out.println("Balance for user2@example.com: " + bank.checkBalance("user2@example.com")); // Should be
                                                                                                            // 550.0
            workingFunctions++;

            bank.audit();
            workingFunctions++;

            System.out.println("Audit completed successfully.");
            verifyAuditFile("AccountAudit.txt", 2);

            File file = bank2.audit();

            if (reader.readFileMethod(file).equals("")) {
                auditCount++;
            }

            bank2.openAccount("test1@gmail.com", 111);
            bank2.audit();

            if (reader.readFileMethod(file).equals("test1@gmail.com balance: 111.0")) {
                auditCount++;
            }

            bank2.openAccount("test2@gmail.com", 222);
            bank2.audit();

            if (reader.readFileMethod(file).equals("test1@gmail.com balance: 111.0\ntest2@gmail.com balance: 222.0")) {
                auditCount++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Valid Use Cases: " + workingFunctions);
            if (auditCount == 3) {
                System.out.println("Audit works correctly!");
            } else {
                System.out.println("Audit doesn't work correctly :(");
            }
        }
    }

    private static void verifyAuditFile(String fileName, int expectedEntries) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int entryCount = 0;
            while (reader.readLine() != null) {
                entryCount++;
            }
            if (entryCount == expectedEntries) {
                System.out.println("Audit file entries match expected count.");
            } else {
                System.out.println(
                        "Audit file entries " + entryCount + " do not match expected count " + expectedEntries + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
