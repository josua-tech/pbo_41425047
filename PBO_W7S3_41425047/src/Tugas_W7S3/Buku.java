/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas_W7S3;

/* Class Buku: menyimpan data buku dan jumlah stoknya. */
public class Buku {

    private final String kode;
    private final String judul;
    private int stok;

    public Buku(String kode, String judul, int stok) {
        this.kode = kode;
        this.judul = judul;
        this.stok = stok;
    }

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }
    

    public int getStok() {
        return stok;
    }

    // throws: method ini memberi tahu pemanggil bahwa ia bisa melempar exception buatan kita
    public void kurangiStok() throws BukuTidakTersediaException {
        if (stok == 0) {
            // throw: melempar exception secara manual karena stok habis
            throw new BukuTidakTersediaException("Stok buku '" + judul + "' habis", kode);
        }
        stok--; // stok berkurang satu karena dipinjam
    }

    public void tambahStok(int jumlah) {
        stok += jumlah; // stok bertambah (buku dikembalikan / ditambah)
    }
}

/*
Penjelasan: kurangiStok() memakai throw untuk melempar BukuTidakTersediaException saat stok 0, 
dan throws di deklarasi method karena exception ini checked. Jika stok masih ada, stok dikurangi satu
*/