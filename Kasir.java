import java.util.Scanner;

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
class Faktur extends Barang { // Menggunakan konsep inheritance (Faktur adalah turunan dari Barang)
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
    public void tampilkanFaktur() {
        System.out.println("\n=== Detail Faktur ===");
        System.out.println("No Faktur   : " + noFaktur);
        System.out.println("Kode Barang : " + kodeBarang);
        System.out.println("Nama Barang : " + namaBarang);
        System.out.println("Harga Barang: Rp " + hargaBarang);
        System.out.println("Jumlah Beli : " + jumlahBeli);
        System.out.println("Total Harga : Rp " + hitungTotal());
    }
}

// Main class
public class Kasir {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
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
           // Membuat objek Faktur dan menampilkan detailnya
           Faktur faktur = new Faktur(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
           faktur.tampilkanFaktur();

       } catch (Exception e) { // Exception handling jika terjadi kesalahan input
           System.out.println("Error: " + e.getMessage()); // Menampilkan pesan error
       } finally {
           scanner.close(); // Menutup scanner
       }
   }

   // Validasi input string
   // Memastikan string tidak kosong atau null
   public static String validasiInputString(String input, String fieldName) throws Exception {
       if (input == null || input.trim().isEmpty()) {
           throw new Exception(fieldName + " tidak boleh kosong."); // Melempar exception jika input tidak valid
       }
       return input;
   }

   // Validasi input double
   // Memastikan nilai bertipe double lebih dari 0
   public static double validasiInputDouble(double input, String fieldName) throws Exception {
       if (input <= 0) {
           throw new Exception(fieldName + " harus lebih dari 0."); // Melempar exception jika nilai tidak valid
       }
       return input;
   }

   // Validasi input int
   // Memastikan nilai bertipe int lebih dari 0
   public static int validasiInputInt(int input, String fieldName) throws Exception {
       if (input <= 0) {
           throw new Exception(fieldName + " harus lebih dari 0."); // Melempar exception jika nilai tidak valid
       }
       return input;
   }
}
