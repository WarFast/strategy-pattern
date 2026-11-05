import java.util.List;

public class EWallet implements PaymentStrategy, HasPin {
    private String phoneNumber;
    private double balance;
    private String pin;

    public EWallet(String phoneNumber, double balance, String pin) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.pin = pin;
    }

    @Override
    public String getPin() {
        return pin;
    }

    @Override
    public boolean validate(double amount) {
        return balance >= amount;
    }

    @Override
    public void pay(double amount) {
        balance -= amount;
        System.out.println("Pembayaran e-wallet berhasil, sisa saldo: " + (long) balance);
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of(AuthorizationMethod.PIN, AuthorizationMethod.OTP);
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 1_000 && amount <= 20_000_000;
    }

    @Override
    public String toString() {
        return "E-Wallet - " + phoneNumber + " (saldo: " + (long) balance + ")";
    }
}
