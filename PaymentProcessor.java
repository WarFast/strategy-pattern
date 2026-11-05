import java.util.List;
import java.util.Scanner;

public class PaymentProcessor {
    private final Scanner scanner;

    public PaymentProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    public void processPayment(PaymentStrategy strategy, double amount) {
        if (!strategy.validate(amount)) {
            throw new PaymentException("Saldo/limit tidak mencukupi.");
        }

        List<AuthorizationMethod> required = strategy.getRequiredAuthorizations();
        for (int i = 0; i < required.size(); i++) {
            switch (required.get(i)) {
                case PIN -> {
                    System.out.print("Masukkan PIN: ");
                    String input = scanner.nextLine().trim();
                    if (!(strategy instanceof HasPin p) || !p.getPin().equals(input)) {
                        throw new PaymentException("PIN tidak valid.");
                    }
                }
                case CCV -> {
                    System.out.print("Masukkan CCV: ");
                    String input = scanner.nextLine().trim();
                    if (!(strategy instanceof HasCcv c) || !c.getCcv().equals(input)) {
                        throw new PaymentException("CCV tidak valid.");
                    }
                }
                default -> throw new PaymentException(
                        "Otorisasi " + required.get(i) + " belum didukung.");
            }
        }

        strategy.pay(amount);
    }
}
