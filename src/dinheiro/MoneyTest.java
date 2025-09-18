package dinheiro;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {

    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    public void testFrancMultiplication() {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = sum.reduce(bank, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }

    public void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    public void testArrayEquals() {
        assertEquals(new Object[] {"abc"}, new Object[] {"abc"});
    }

    public void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    public void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    public void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    public void testSumTimes() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }

    public void testPlusSameCurrencyReturnsMoney() {
        Expression sum = Money.dollar(1).plus(Money.dollar(1));
        assertTrue(sum instanceof Money);
    }

    public void testSumPrinting() {
        Sum sum = new Sum(Money.dollar(5), Money.franc(7));
        assertEquals("5 USD + 7 CHF", sum.toString());
        assertEquals("+\n\t5 USD\n\t7 CHF", sum.toString());
    }

//    String toString() {
//        IndentingStream writer = new IndentingStream();
//        toString(writer);
//        return writer.contents();
//    }
//
//    void toString(IndentingWriter writer) {
//        writer.println("+");
//        writer.indent();
//        augend.toString(writer);
//        writer.println();
//        addend.toString(writer);
//        writer.exdent();
//    }
//
//    public void testRate() {
//        exchange.addRate("USD", "GBP", 2);
//        int rate = exchange.findRate("USD", "GBP");
//        assertEquals(2, rate);
//    }
//
//    public void testMissingRate() {
//        try {
//            exchange.findRate("USD", "GBP");
//            fail();
//        } catch (IllegalArgumentException expected) {
//        }
//    }
//
//    public void testConvertTransaction() {
//        Bank bank = new Bank();
//        bank.addRate("USD", "GBP", STANDARD_RATE);
//        bank.commission(STANDARD_COMMISSION);
//        Money result = bank.convert(new Note(100, "USD"), "GBP");
//        assertEquals(new Note(49.25, "GBP"), result);
//    }
//
//    public void testConvertTransactionWithCalcs() {
//        Bank bank = new Bank();
//        bank.addRate("USD", "GBP", 2);
//        bank.commission(0.015);
//        Money result = bank.convert(new Note(100, "USD"), "GBP");
//        assertEquals(new Note(100 / 2 * (1 - 0.015), "GBP"), result);
//    }

}
