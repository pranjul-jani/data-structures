import java.util.*;

// Singleton Pattern
class UserManager {
    private static UserManager instance;
    private Map<String, User> users;

    private UserManager() {
        users = new HashMap<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}

class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

// Factory Pattern
interface ExpenseFactory {
    Expense createExpense(double totalAmount, List<User> participants);
}

class EqualExpenseFactory implements ExpenseFactory {
    public Expense createExpense(double totalAmount, List<User> participants) {
        return new EqualExpense(totalAmount, participants);
    }
}

class UnequalExpenseFactory implements ExpenseFactory {
    public Expense createExpense(double totalAmount, List<User> participants) {
        return new UnequalExpense(totalAmount, participants);
    }
}

// Observer Pattern
interface Observer {
    void update();
}

class ExpenseObserver implements Observer {
    public void update() {
        // Update logic for expenses...
    }
}

// Strategy Pattern
interface SplitStrategy {
    Map<User, Double> splitExpense(double totalAmount, List<User> participants);
}

class EqualSplitStrategy implements SplitStrategy {
    public Map<User, Double> splitExpense(double totalAmount, List<User> participants) {
        Map<User, Double> shares = new HashMap<>();
        double share = totalAmount / participants.size();
        for (User participant : participants) {
            shares.put(participant, share);
        }
        return shares;
    }
}

class UnequalSplitStrategy implements SplitStrategy {
    public Map<User, Double> splitExpense(double totalAmount, List<User> participants) {
        // Custom splitting logic based on user preferences, weights, etc.
        // For simplicity, we'll use equal splitting for demonstration purposes.
        return new EqualSplitStrategy().splitExpense(totalAmount, participants);
    }
}

// Command Pattern
interface ExpenseCommand {
    void execute();
}

class AddExpenseCommand implements ExpenseCommand {
    private Expense expense;

    public AddExpenseCommand(Expense expense) {
        this.expense = expense;
    }

    public void execute() {
        // Execute add expense logic...
        expense.calculateShares();
    }
}

// Facade Pattern
class SplitwiseFacade {
    private UserManager userManager;
    private List<Expense> expenses;
    private List<Observer> observers;

    public SplitwiseFacade() {
        userManager = UserManager.getInstance();
        expenses = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void addUser(User user) {
        userManager.addUser(user);
    }

    public void addEqualExpense(double totalAmount, List<User> participants) {
        ExpenseFactory factory = new EqualExpenseFactory();
        Expense expense = factory.createExpense(totalAmount, participants);
        expenses.add(expense);

        notifyObservers();
    }

    // Other facade methods...

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Expense Classes
abstract class Expense {
    protected double totalAmount;
    protected List<User> participants;
    protected Map<User, Double> shares;
    protected SplitStrategy splitStrategy;

    public Expense(double totalAmount, List<User> participants, SplitStrategy splitStrategy) {
        this.totalAmount = totalAmount;
        this.participants = participants;
        this.splitStrategy = splitStrategy;
    }

    public abstract void calculateShares();
}

class EqualExpense extends Expense {
    public EqualExpense(double totalAmount, List<User> participants) {
        super(totalAmount, participants, new EqualSplitStrategy());
    }

    public void calculateShares() {
        shares = splitStrategy.splitExpense(totalAmount, participants);
    }
}

class UnequalExpense extends Expense {
    public UnequalExpense(double totalAmount, List<User> participants) {
        super(totalAmount, participants, new UnequalSplitStrategy());
    }

    public void calculateShares() {
        shares = splitStrategy.splitExpense(totalAmount, participants);
    }
}

// Main Class
public class SplitwiseApp {
    public static void main(String[] args) {
        SplitwiseFacade splitwise = new SplitwiseFacade();

        User user1 = new User("1", "Alice");
        User user2 = new User("2", "Bob");

        splitwise.addUser(user1);
        splitwise.addUser(user2);

        splitwise.addObserver(new ExpenseObserver());

        splitwise.addEqualExpense(100.0, Arrays.asList(user1, user2));

        // Additional operations...
    }
}
