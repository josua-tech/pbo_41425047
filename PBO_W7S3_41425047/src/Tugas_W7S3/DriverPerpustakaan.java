/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Tugas_W7S3;

/** Class driver: menginstansiasi semua class dan memanggil semua method. */
public class DriverPerpustakaan {

    public static void main(String[] args) {
        Perpustakaan perpus = new Perpustakaan();
        Anggota budi = new Anggota("Budi", "41425047");

        // Skenario 1: input stok bukan angka -> NumberFormatException (dibungkus IllegalArgumentException)
        try {
            perpus.tambahBuku(new Buku("B01", "Pemrograman Java", perpus.parseStok("2")));
            perpus.tambahBuku(new Buku("B02", "Struktur Data", perpus.parseStok("1")));
            perpus.tambahBuku(new Buku("B03", "Basis Data", perpus.parseStok("dua"))); // salah input
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR INPUT] " + e.getMessage());
            System.out.println("Penyebab asli : " + e.getCause()); // exception asli yang dibungkus
            perpus.tambahBuku(new Buku("B03", "Basis Data", 1));   // diperbaiki dengan nilai valid
        }
        perpus.tampilkanKoleksi();

        // Skenario 2: peminjaman normal
        try {
            perpus.pinjamBuku(budi, "B01");
        } catch (BukuTidakTersediaException e) {
            System.out.println("[GAGAL] " + e.getMessage());
        }

        // Skenario 3: stok habis -> exception buatan sendiri
        try {
            perpus.pinjamBuku(budi, "B02");       // stok B02 tinggal 1 -> berhasil
            perpus.pinjamBuku(new Anggota("Sari", "41425048"), "B02"); // stok sudah 0 -> gagal
        } catch (BukuTidakTersediaException e) {
            System.out.println("[GAGAL] " + e.getMessage() + " (kode: " + e.getKodeBuku() + ")");
        }

        // Skenario 4: kode buku tidak ada -> exception buatan sendiri
        try {
            perpus.cariBuku("B99");
        } catch (BukuTidakTersediaException e) {
            System.out.println("[GAGAL] " + e.getMessage());
        }

        // Skenario 5: melebihi batas pinjam (maks 3) -> IllegalStateException
        try {
            perpus.pinjamBuku(budi, "B03");       // pinjaman ke-3 -> berhasil
            perpus.pinjamBuku(budi, "B01");       // pinjaman ke-4 -> ditolak
        } catch (BukuTidakTersediaException | IllegalStateException e) {
            System.out.println("[GAGAL] " + e.getMessage());
        }
        System.out.println("Jumlah pinjaman " + budi.getNama() + ": " + budi.getJumlahPinjam());

        // Skenario 6: pengembalian buku
        try {
            perpus.kembalikanBuku(budi, "B01");   // sah
            perpus.kembalikanBuku(budi, "B01");   // sudah dikembalikan -> IllegalStateException
        } catch (BukuTidakTersediaException | IllegalStateException e) {
            System.out.println("[GAGAL] " + e.getMessage());
        } finally {
            perpus.tampilkanKoleksi();            // tampil di akhir apa pun hasilnya
        }
    }
}

/*
Penjelasan: driver membuat objek Perpustakaan dan Anggota, lalu menjalankan enam skenario: input salah, peminjaman normal,stok habis, kode tidak ada, batas pinjam terlampaui, dan pengembalian buku.
Dengan begitu semua class diinstansiasi dan semua method dipanggil,sesuai syarat no. 3 modul.
*/