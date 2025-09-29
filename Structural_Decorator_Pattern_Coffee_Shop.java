// Component
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
class BasicCoffee implements Coffee {
    public String getDescription() { return "Basic Coffee"; }
    public double getCost() { return 50.0; }
}

// Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    public CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }
    public String getDescription() { return coffee.getDescription(); }
    public double getCost() { return coffee.getCost(); }
}

// Concrete Decorators
class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) { super(coffee); }
    public String getDescription() { return super.getDescription() + ", Milk"; }
    public double getCost() { return super.getCost() + 10; }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee coffee) { super(coffee); }
    public String getDescription() { return super.getDescription() + ", Sugar"; }
    public double getCost() { return super.getCost() + 5; }
}

class Caramel extends CoffeeDecorator {
    public Caramel(Coffee coffee) { super(coffee); }
    public String getDescription() { return super.getDescription() + ", Caramel"; }
    public double getCost() { return super.getCost() + 20; }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        System.out.println(coffee.getDescription() + " -> Rs." + coffee.getCost());

        coffee = new Milk(coffee);
        coffee = new Sugar(coffee);
        coffee = new Caramel(coffee);

        System.out.println(coffee.getDescription() + " -> Rs." + coffee.getCost());
    }
}
