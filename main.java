import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Scanner;

interface InMemoryDB {
    int get(String key);
    void put(String key, int val);
    void beginTransaction();
    void commit();
    void rollback();
}

class TransactionalDatabase implements InMemoryDB {
    private Map<String, Integer> data;
    private Stack<Map<String, Integer>> transactionStack;

    public TransactionalDatabase() {
        this.data = new HashMap<>();
        this.transactionStack = new Stack<>();
    }

    @Override
    public int get(String key) {
        return data.getOrDefault(key, -1);
    }

    @Override
    public void put(String key, int val) {
        data.put(key, val);
    }

    @Override
    public void beginTransaction() {
        transactionStack.push(new HashMap<>(data));
    }

    @Override
    public void commit() {
        if (!transactionStack.isEmpty()) {
            transactionStack.clear();
        }
    }

    @Override
    public void rollback() {
        if (!transactionStack.isEmpty()) {
            data = transactionStack.pop();
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        InMemoryDB db = new TransactionalDatabase();
        int option = 0;
        System.out.println("Hello! This is a Data Processing and Storage assignment for my CIS4930 Special Topics course. My name is Angela Ung :)");
        while (option != 7) {

            System.out.println("1. Begin a transaction [beginTransaction()]");
            System.out.println("2. Get value [get(key)]");
            System.out.println("3. Insert value [put(key, val)]");
            System.out.println("4. Commit [commit()]");
            System.out.println("5. Rollback [rollback()]");
            System.out.println("6. Demo Testing");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");

            option = s.nextInt();
            s.nextLine();

            if (option == 1) {

                System.out.println("Selected: Begin Transaction");
                db.beginTransaction();
                System.out.println("Transaction began. Select another option.");

            } else if (option == 2) {

                System.out.println("Selected: Get Value");
                System.out.print("Input Key: ");
                String key = s.nextLine();
                System.out.println("The value for " + key + " is " + db.get(key) + ". Select another option.");

            } else if (option == 3) {

                System.out.println("Selected: Insert value");
                System.out.print("Create or Use Previous Key: ");
                String key = s.nextLine();
                System.out.print("Value for Key: ");
                int value = s.nextInt();
                db.put(key, value);
                System.out.println("The value for " + key + " is " + db.get(key) + ". Select another option.");

            } else if (option == 4) {

                System.out.println("Selected: Commit");
                db.commit();
                System.out.println("Commit successful. Select another option.");

            } else if (option == 5) {

                System.out.println("Selected: Rollback");
                db.rollback();
                System.out.println("Rollback successful. Select another option.");

            } else if (option == 6) {

                System.out.println("This is Demo 1. We put '1' into 'a' and '2' into 'b'. Then begin transaction and put '10' into 'a' and '30' into 'c'. Now call rollback.");

                db.put("a", 1);
                db.put("b", 2);

                // Begin a transaction
                db.beginTransaction();
                db.put("a", 10);
                db.put("c", 30);

                // Rollback the transaction
                db.rollback();

                // Check if changes were rolled back
                System.out.println("Value of 'a' after rollback is 1: " + db.get("a"));  // Output: 1
                System.out.println("Value of 'c' after rollback is not present: " + db.get("c"));  // Output: -1 (not present)

                System.out.println("Begin transaction. Now put '20' into 'b' and '40' into 'd'. Now commit.");

                // Begin a new transaction
                db.beginTransaction();
                db.put("b", 20);
                db.put("d", 40);

                // Commit the transaction
                db.commit();

                // Check if changes were committed
                System.out.println("Value of 'b' after commit is 20: " + db.get("b"));  // Output: 20
                System.out.println("Value of 'd' after commit is 40: " + db.get("d"));  // Output: 40

            } else if (option == 7) {

                System.out.println("Thank you for using my program! Please read the README for suggestions.");

            } else {

                System.out.println("Invalid input. Input a number 1-7.");

            }
        }
        s.close();
    }
}