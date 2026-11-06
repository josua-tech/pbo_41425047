/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Tugas_W7S3;

import java.util.ArrayList;
import java.util.List;

/* Class Anggota: peminjam buku dengan batas maksimal 3 buku. */
public class Anggota {

    private static final int MAKS_PINJAM = 3; // batas maksimal peminjaman
    private final String nama;
    private final String nim;
    private final List<String> daftarPinjam = new ArrayList<>(); // kode buku yang sedang dipinjam

    public Anggota(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public int getJumlahPinjam() {
        return daftarPinjam.size();
    }

    public void tambahPinjaman(String kodeBuku) {
        if (daftarPinjam.size() >= MAKS_PINJAM) {
            // IllegalStateException: kondisi objek tidak memungkinkan operasi ini (unchecked)
            throw new IllegalStateException(nama + " sudah mencapai batas " + MAKS_PINJAM + " buku");
        }
        daftarPinjam.add(kodeBuku);
    }

    public void hapusPinjaman(String kodeBuku) {
        if (!daftarPinjam.remove(kodeBuku)) {
            // buku yang mau dikembalikan ternyata tidak sedang dipinjam anggota ini
            throw new IllegalStateException(nama + " tidak sedang meminjam buku " + kodeBuku);
        }
    }
}


/*
Penjelasan: anggota maksimal meminjam 3 buku. Pelanggaran aturan ini dilempar sebagai IllegalStateException, yaitu unchecked exception, jadi tidak wajib memakai throws. 
hapusPinjaman() juga melempar exception yang sama jika buku yang dikembalikan tidak sedang dipinjam.
*/