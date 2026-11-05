import java.util.List;

public class DebitCard implements PaymentStrategy, HasPin {
    private String cardNumber;
    private double balance;
    private String pin;

    public DebitCard(String cardNumber, double balance, String pin) {
        this.cardNumber = cardNumber;
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
        System.out.println("Pembayaran debit berhasil, sisa saldo: " + (long) balance);
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of(AuthorizationMethod.PIN);
    }

    @Override
    public String toString() {
        return "Debit Card - " + cardNumber + " (saldo: " + (long) balance + ")";
    }
}
