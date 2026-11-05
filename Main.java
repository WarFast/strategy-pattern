import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    // Semua metode disimpan sebagai Object -> harus dicek tipenya satu-satu (berantakan!)
    private static final List<Object> methods = new ArrayList<>();

    public static void main(String[] args) {
        methods.add(new Wallet("Wallet Utama", 10000));
        methods.add(new DebitCard("1111-2222", 100000, "1234"));
        methods.add(new CreditCard("3333-4444", 5000000, "123", "1234"));

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Bikin Pembayaran");
            System.out.println("2. Tambah Metode Pembayaran");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                makePayment();
            } else if (choice.equals("2")) {
                addMethod();
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void makePayment() {
        System.out.print("Masukkan jumlah yang dibayar: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Pilih metode pembayaran:");
        for (int i = 0; i < methods.size(); i++) {
            System.out.println((i + 1) + ". " + methods.get(i));
        }
        System.out.print("Pilih: ");
        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (idx < 0 || idx >= methods.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Object m = methods.get(idx);
        if (m instanceof Wallet) {
            Wallet w = (Wallet) m;
            if (w.validate(amount)) {
                w.pay(amount);
            } else {
                System.out.println("Saldo wallet tidak cukup.");
            }
        } else if (m instanceof DebitCard) {
            DebitCard d = (DebitCard) m;
            if (!d.validate(amount)) {
                System.out.println("Saldo debit tidak cukup.");
                return;
            }
            System.out.print("Masukkan PIN: ");
            if (d.getPin().equals(scanner.nextLine().trim())) {
                d.pay(amount);
            } else {
                System.out.println("PIN salah.");
            }
        } else if (m instanceof CreditCard) {
            CreditCard c = (CreditCard) m;
            if (!c.validate(amount)) {
                System.out.println("Limit kredit tidak cukup.");
                return;
            }
            System.out.print("Masukkan CCV: ");
            if (!c.getCcv().equals(scanner.nextLine().trim())) {
                System.out.println("CCV salah.");
                return;
            }
            System.out.print("Masukkan PIN: ");
            if (c.getPin().equals(scanner.nextLine().trim())) {
                c.pay(amount);
            } else {
                System.out.println("PIN salah.");
            }
        }
        // Metode baru? Harus tambah else-if lagi di sini...
    }

    private static void addMethod() {
        System.out.println("Jenis pembayaran:");
        System.out.println("1. Cash (Wallet baru)");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card");
        System.out.print("Pilih: ");
        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            System.out.print("Nama wallet: ");
            String name = scanner.nextLine().trim();
            System.out.print("Jumlah uang: ");
            double cash = Double.parseDouble(scanner.nextLine().trim());
            methods.add(new Wallet(name, cash));
        } else if (choice.equals("2")) {
            System.out.print("Nomor kartu: ");
            String no = scanner.nextLine().trim();
            System.out.print("Saldo: ");
            double bal = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("PIN: ");
            String pin = scanner.nextLine().trim();
            methods.add(new DebitCard(no, bal, pin));
        } else if (choice.equals("3")) {
            System.out.print("Nomor kartu: ");
            String no = scanner.nextLine().trim();
            System.out.print("Limit kredit: ");
            double limit = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("CCV: ");
            String ccv = scanner.nextLine().trim();
            System.out.print("PIN: ");
            String pin = scanner.nextLine().trim();
            methods.add(new CreditCard(no, limit, ccv, pin));
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        System.out.println("Metode pembayaran berhasil ditambahkan.");
    }
}
