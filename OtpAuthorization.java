import java.util.Random;

public class OtpAuthorization implements AuthorizationStrategy {
    private final Random random = new Random();
    private String currentOtp;

    @Override
    public String getPrompt() {
        currentOtp = String.format("%06d", random.nextInt(1_000_000));
        System.out.println("[SMS] Kode OTP Anda: " + currentOtp);
        return "Masukkan OTP: ";
    }

    @Override
    public boolean authorize(String input, PaymentStrategy payment) {
        return currentOtp != null && currentOtp.equals(input);
    }
}
