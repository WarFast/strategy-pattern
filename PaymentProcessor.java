import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PaymentProcessor {
    private final Map<AuthorizationMethod, AuthorizationStrategy> authorizations =
            new EnumMap<>(AuthorizationMethod.class);
    private final Scanner scanner;

    public PaymentProcessor(Scanner scanner) {
        this.scanner = scanner;
        authorizations.put(AuthorizationMethod.PIN, new PinAuthorization());
        authorizations.put(AuthorizationMethod.CCV, new CcvAuthorization());
        authorizations.put(AuthorizationMethod.OTP, new OtpAuthorization());
    }

    public void processPayment(PaymentStrategy strategy, double amount) {
        if (!strategy.validate(amount)) {
            throw new PaymentException("Saldo/limit tidak mencukupi.");
        }

        List<AuthorizationMethod> required = strategy.getRequiredAuthorizations();
        for (int i = 0; i < required.size(); i++) {
            AuthorizationStrategy auth = authorizations.get(required.get(i));
            if (auth == null) {
                throw new PaymentException("Otorisasi " + required.get(i) + " belum didukung.");
            }
            System.out.print(auth.getPrompt());
            String input = scanner.nextLine().trim();
            if (!auth.authorize(input, strategy)) {
                throw new PaymentException("Otorisasi " + required.get(i) + " tidak valid.");
            }
        }

        strategy.pay(amount);
    }
}
