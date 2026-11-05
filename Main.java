import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Wallet wallet = new Wallet("Wallet Utama", 10000);

        while (true) {
            System.out.print("Masukkan jumlah yang dibayar (0 untuk keluar): ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) break;

            if (wallet.validate(amount)) {
                wallet.pay(amount);
            } else {
                System.out.println("Saldo wallet tidak cukup.");
            }
        }
    }
}
