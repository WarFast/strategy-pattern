public class Wallet {
    private String name;
    private double cashValue;

    public Wallet(String name, double cashValue) {
        this.name = name;
        this.cashValue = cashValue;
    }

    public boolean validate(double amount) {
        return cashValue >= amount;
    }

    public void pay(double amount) {
        cashValue -= amount;
        System.out.println("Pembayaran cash berhasil, sisa wallet: " + (long) cashValue);
    }
}
