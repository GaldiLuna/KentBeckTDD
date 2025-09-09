package dinheiro;

public interface Expression {
    Money reduce(Bank bank, String to);
}
