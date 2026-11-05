import java.util.List;

public class CreditCard implements PaymentStrategy, HasPin, HasCcv {
    private String cardNumber;
    private double limit;
    private double currentDebt;
    private String ccv;
    private String pin;

    public CreditCard(String cardNumber, double limit, String ccv, String pin) {
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.currentDebt = 0;
        this.ccv = ccv;
        this.pin = pin;
    }

    @Override
    public String getPin() {
        return pin;
    }

    @Override
    public String getCcv() {
        return ccv;
    }

    @Override
    public boolean validate(double amount) {
        return currentDebt + amount <= limit;
    }

    @Override
    public void pay(double amount) {
        currentDebt += amount;
        System.out.println("Pembayaran kredit berhasil, total hutang: " + (long) currentDebt
                + ", sisa limit: " + (long) (limit - currentDebt));
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of(AuthorizationMethod.CCV, AuthorizationMethod.PIN);
    }

    @Override
    public String toString() {
        return "Credit Card - " + cardNumber + " (sisa limit: " + (long) (limit - currentDebt) + ")";
    }
}
