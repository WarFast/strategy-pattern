public interface AuthorizationStrategy {
    String getPrompt();
    boolean authorize(String input, PaymentStrategy payment);
}
