package dinheiro;

public class Transaction {
    Transaction(Money value) {
        this.value = value;
    }

    Money balance() {
        return value;
    }
}
