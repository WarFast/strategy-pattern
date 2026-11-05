public class PinAuthorization implements AuthorizationStrategy {
    @Override
    public String getPrompt() {
        return "Masukkan PIN: ";
    }

    @Override
    public boolean authorize(String input, PaymentStrategy payment) {
        return payment instanceof HasPin p && p.getPin().equals(input);
    }
}
