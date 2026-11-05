import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<PaymentStrategy> methods = new ArrayList<>();
    private static final PaymentProcessor processor = new PaymentProcessor(scanner);

    public static void main(String[] args) {
        // Data awal
        methods.add(new Wallet("Wallet Utama", 10000));
        methods.add(new DebitCard("1111-2222", 100000, "1234"));
        methods.add(new CreditCard("3333-4444", 5_000_000, "123", "1234"));

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Bikin Pembayaran");
            System.out.println("2. Tambah Metode Pembayaran");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> makePayment();
                case "2" -> addMethod();
                case "0" -> { System.out.println("Terima kasih."); return; }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void makePayment() {
        double amount;
        try {
            System.out.print("Masukkan jumlah yang dibayar: ");
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Jumlah tidak valid.");
            return;
        }

        // Tahap 6: filter dengan canPay
        List<PaymentStrategy> available = new ArrayList<>();
        for (PaymentStrategy m : methods) {
            if (m.canPay(amount)) available.add(m);
        }
        if (available.isEmpty()) {
            System.out.println("Tidak ada metode pembayaran yang tersedia untuk nominal ini.");
            return;
        }

        System.out.println("Pilih metode pembayaran:");
        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i));
        }
        System.out.print("Pilih: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (idx < 0 || idx >= available.size()) {
                System.out.println("Pilihan tidak valid.");
                return;
            }
            processor.processPayment(available.get(idx), amount);
        } catch (NumberFormatException e) {
            System.out.println("Pilihan tidak valid.");
        } catch (PaymentException e) {
            System.out.println("Pembayaran gagal: " + e.getMessage());
        }
    }

    private static void addMethod() {
        System.out.println("Jenis pembayaran:");
        System.out.println("1. Cash (Wallet baru)");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card");
        System.out.print("Pilih: ");
        String choice = scanner.nextLine().trim();
        try {
            switch (choice) {
                case "1" -> {
                    String name = ask("Nama wallet: ");
                    double cash = Double.parseDouble(ask("Jumlah uang: "));
                    methods.add(new Wallet(name, cash));
                }
                case "2" -> {
                    String no = ask("Nomor kartu: ");
                    double bal = Double.parseDouble(ask("Saldo: "));
                    String pin = ask("PIN: ");
                    methods.add(new DebitCard(no, bal, pin));
                }
                case "3" -> {
                    String no = ask("Nomor kartu: ");
                    double limit = Double.parseDouble(ask("Limit kredit: "));
                    String ccv = ask("CCV: ");
                    String pin = ask("PIN: ");
                    methods.add(new CreditCard(no, limit, ccv, pin));
                }
                default -> { System.out.println("Pilihan tidak valid."); return; }
            }
            System.out.println("Metode pembayaran berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            System.out.println("Input angka tidak valid.");
        }
    }

    private static String ask(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }
}
