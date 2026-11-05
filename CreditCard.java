public class CreditCard {
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

    public String getCcv() {
        return ccv;
    }

    public String getPin() {
        return pin;
    }

    public boolean validate(double amount) {
        return currentDebt + amount <= limit;
    }

    public void pay(double amount) {
        currentDebt += amount;
        System.out.println("Pembayaran kredit berhasil, total hutang: " + (long) currentDebt
                + ", sisa limit: " + (long) (limit - currentDebt));
    }
}
