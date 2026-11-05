import java.util.List;

public class Wallet implements PaymentStrategy {
    private String name;
    private double cashValue;

    public Wallet(String name, double cashValue) {
        this.name = name;
        this.cashValue = cashValue;
    }

    @Override
    public boolean validate(double amount) {
        return cashValue >= amount;
    }

    @Override
    public void pay(double amount) {
        cashValue -= amount;
        System.out.println("Pembayaran cash berhasil, sisa wallet: " + (long) cashValue);
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of();
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 100 && amount <= 1_000_000;
    }

    @Override
    public String toString() {
        return "Cash - " + name + " (saldo: " + (long) cashValue + ")";
    }
}
