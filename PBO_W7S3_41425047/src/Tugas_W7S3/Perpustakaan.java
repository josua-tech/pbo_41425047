/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Tugas_W7S3;

import java.util.ArrayList;
import java.util.List;

/* Class Perpustakaan: mengelola koleksi buku dan proses peminjaman / pengembalian. */
public class Perpustakaan {

    private final List<Buku> koleksi = new ArrayList<>();

    public void tambahBuku(Buku buku) {
        koleksi.add(buku);
    }

    // Mengubah teks menjadi angka stok; NumberFormatException ditangani di sini
    public int parseStok(String teks) {
        try {
            return Integer.parseInt(teks);        // bisa gagal jika teks bukan angka
        } catch (NumberFormatException e) {       // exception bawaan Java: teks bukan angka
            // dibungkus menjadi IllegalArgumentException dengan pesan yang lebih jelas
            throw new IllegalArgumentException("Stok '" + teks + "' bukan angka yang valid", e);
        }
    }
    

    // Mencari buku berdasarkan kode; melempar exception buatan sendiri jika tidak ada
    public Buku cariBuku(String kode) throws BukuTidakTersediaException {
        for (Buku b : koleksi) {
            if (b.getKode().equals(kode)) {
                return b;
            }
        }
        throw new BukuTidakTersediaException("Buku dengan kode " + kode + " tidak ditemukan", kode);
    }

    public void pinjamBuku(Anggota anggota, String kode) throws BukuTidakTersediaException {
        try {
            Buku buku = cariBuku(kode);           // bisa melempar BukuTidakTersediaException
            buku.kurangiStok();                   // bisa melempar BukuTidakTersediaException
            try {
                anggota.tambahPinjaman(kode);     // bisa melempar IllegalStateException
            } catch (IllegalStateException e) {
                buku.tambahStok(1);               // batalkan pengurangan stok agar data tetap konsisten
                throw e;                          // re-throw: serahkan ke pemanggil
            }
            System.out.println("[BERHASIL] " + anggota.getNama() + " meminjam '" + buku.getJudul() + "'");
        } finally {
            // finally SELALU dijalankan, baik berhasil maupun gagal
            System.out.println("[LOG] Proses peminjaman kode " + kode + " oleh " + anggota.getNim() + " selesai");
        }
    }

    public void kembalikanBuku(Anggota anggota, String kode) throws BukuTidakTersediaException {
        Buku buku = cariBuku(kode);
        anggota.hapusPinjaman(kode);              // bisa melempar IllegalStateException
        buku.tambahStok(1);
        System.out.println("[BERHASIL] " + anggota.getNama() + " mengembalikan '" + buku.getJudul() + "'");
    }

    public void tampilkanKoleksi() {
        System.out.println("--- Koleksi Perpustakaan ---");
        for (Buku b : koleksi) {
            System.out.println(b.getKode() + " | " + b.getJudul() + " | stok: " + b.getStok());
        }
    }
}

/*
Penjelasan:
-parseStok() mengubah teks jadi angka. Jika teks bukan angka, NumberFormatException ditangkap, lalu dibungkus menjadi IllegalArgumentException dengan pesan yang lebih jelas (exception asli tetap tersimpan sebagai penyebab).
-cariBuku() melempar exception buatan sendiri bila kode buku tidak ada.
-pinjamBuku() memakai try-finally, jadi log selalu tercetak. Ada try-catch bersarang: jika batas pinjam terlampaui, stok dikembalikan dulu supaya data tetap konsisten, lalu exception dilempar ulang dengan throw e.
-kembalikanBuku() dan tampilkanKoleksi() melengkapi alur pengembalian dan tampilan data.
*/