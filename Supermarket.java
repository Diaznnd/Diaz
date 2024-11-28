package Tugas5;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

// Parent class Barang
class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;

    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
}

// Subclass Faktur
class Faktur extends Barang {
    private String noFaktur;
    private int jumlahBeli;

    public Faktur(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.noFaktur = noFaktur;
        this.jumlahBeli = jumlahBeli;
    }

    // Menghitung total harga
    public double hitungTotal() {
        return hargaBarang * jumlahBeli;
    }

    // Menampilkan detail faktur
    public void tampilkanFaktur(String namaKasir) {
        System.out.println("\n+----------------------------------------------------+");
        System.out.println("        Selamat Datang di Supermarket Bintang");
        System.out.println("Tanggal dan Waktu: " + getCurrentDateTime());
        System.out.println("+----------------------------------------------------+");
        System.out.println("No. Faktur      : " + noFaktur);
        System.out.println("Kode Barang     : " + kodeBarang);
        System.out.println("Nama Barang     : " + namaBarang);
        System.out.println("Harga Barang    : Rp " + hargaBarang);
        System.out.println("Jumlah Beli     : " + jumlahBeli);
        System.out.println("TOTAL           : Rp " + hitungTotal());
        System.out.println("+----------------------------------------------------+");
        System.out.println("Kasir           : " + namaKasir);
        System.out.println("+----------------------------------------------------+");
    }

    // Mendapatkan tanggal dan waktu saat ini
    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(formatter);
    }
}

// Main class
public class Supermarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String namaKasir;

        try {
            // Login
            if (!login(scanner)) {
                System.out.println("Login gagal! Program dihentikan.");
                return;
            }
            System.out.println("Login berhasil!");
            System.out.println("+----------------------------------------------------+");
            System.out.print("Masukkan nama kasir: ");
            namaKasir = scanner.nextLine();

            // Input data dari pengguna dengan validasi
            System.out.print("Masukkan No Faktur: ");
            String noFaktur = validasiInputString(scanner.nextLine(), "No Faktur");

            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = validasiInputString(scanner.nextLine(), "Kode Barang");

            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = validasiInputString(scanner.nextLine(), "Nama Barang");

            System.out.print("Masukkan Harga Barang: ");
            double hargaBarang = validasiInputDouble(scanner.nextDouble(), "Harga Barang");

            System.out.print("Masukkan Jumlah Beli: ");
            int jumlahBeli = validasiInputInt(scanner.nextInt(), "Jumlah Beli");

            // Membuat objek Faktur dan menampilkan detailnya
            Faktur faktur = new Faktur(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
            faktur.tampilkanFaktur(namaKasir);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Login method
    public static boolean login(Scanner scanner) {
        String username = "admin";
        String password = "admin123";
        int maxAttempts = 3;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.println("+----------------------------------------------------+");
            System.out.print("Username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Password: ");
            String inputPassword = scanner.nextLine();

            // Generate captcha
            String captcha = generateCaptcha(6);
            System.out.println("Captcha: " + captcha);
            System.out.print("Masukkan Captcha: ");
            String inputCaptcha = scanner.nextLine();
            System.out.println("+----------------------------------------------------+");

            // Validasi login
            if (inputUsername.equals(username) && inputPassword.equals(password) && inputCaptcha.equals(captcha)) {
                return true;
            } else {
                System.out.println("Login gagal! Silakan coba lagi.");
            }
        }

        return false; // Gagal login setelah beberapa percobaan
    }

    // Generate Captcha
    public static String generateCaptcha(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }

        return captcha.toString();
    }

    // Validasi input string
    public static String validasiInputString(String input, String fieldName) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            throw new Exception(fieldName + " tidak boleh kosong.");
        }
        return capitalize(input);
    }

    // Validasi input double
    public static double validasiInputDouble(double input, String fieldName) throws Exception {
        if (input <= 0) {
            throw new Exception(fieldName + " harus lebih dari 0.");
        }
        return input;
    }

    // Validasi input int
    public static int validasiInputInt(int input, String fieldName) throws Exception {
        if (input <= 0) {
            throw new Exception(fieldName + " harus lebih dari 0.");
        }
        return input;
    }

    // Method untuk mengubah string menjadi huruf kapital di awal kata
    public static String capitalize(String input) {
        String[] words = input.split("\\s+");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            capitalized.append(word.substring(0, 1).toUpperCase())
                       .append(word.substring(1).toLowerCase())
                       .append(" ");
        }
        return capitalized.toString().trim();
    }
}
