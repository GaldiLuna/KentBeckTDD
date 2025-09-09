package dinheiro;

public class Dollar extends Money {

    Dollar(int amount, String currency) {
        super(amount, currency);
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

}
