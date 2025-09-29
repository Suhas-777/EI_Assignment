// Strategy
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class UpiPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }
}

class WalletPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Wallet.");
    }
}

// Context
class PaymentContext {
    private PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public void checkout(int amount) { strategy.pay(amount); }
}

public class StrategyDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CreditCardPayment());
        context.checkout(500);

        context.setStrategy(new UpiPayment());
        context.checkout(200);

        context.setStrategy(new WalletPayment());
        context.checkout(100);
    }
}

