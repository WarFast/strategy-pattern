public class DebitCard {
    private String cardNumber;
    private double balance;
    private String pin;

    public DebitCard(String cardNumber, double balance, String pin) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public boolean validate(double amount) {
        return balance >= amount;
    }

    public void pay(double amount) {
        balance -= amount;
        System.out.println("Pembayaran debit berhasil, sisa saldo: " + (long) balance);
    }
}
