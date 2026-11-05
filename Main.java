import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Wallet wallet = new Wallet("Wallet Utama", 10000);
        DebitCard debit = new DebitCard("1111-2222", 100000, "1234");

        while (true) {
            System.out.print("Masukkan jumlah yang dibayar (0 untuk keluar): ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) break;

            System.out.println("Pilih metode pembayaran:");
            System.out.println("1. Cash");
            System.out.println("2. Debit");
            System.out.print("Pilih: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                if (wallet.validate(amount)) {
                    wallet.pay(amount);
                } else {
                    System.out.println("Saldo wallet tidak cukup.");
                }
            } else if (choice.equals("2")) {
                if (!debit.validate(amount)) {
                    System.out.println("Saldo debit tidak cukup.");
                    continue;
                }
                System.out.print("Masukkan PIN: ");
                if (debit.getPin().equals(scanner.nextLine().trim())) {
                    debit.pay(amount);
                } else {
                    System.out.println("PIN salah.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
