public class CcvAuthorization implements AuthorizationStrategy {
    @Override
    public String getPrompt() {
        return "Masukkan CCV: ";
    }

    @Override
    public boolean authorize(String input, PaymentStrategy payment) {
        return payment instanceof HasCcv c && c.getCcv().equals(input);
    }
}
