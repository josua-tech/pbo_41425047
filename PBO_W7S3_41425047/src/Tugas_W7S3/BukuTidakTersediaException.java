/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas_W7S3;


public class BukuTidakTersediaException extends Exception {

    private final String kodeBuku; // menyimpan kode buku yang bermasalah

    // Constructor: meneruskan pesan ke class induk (Exception) lewat super()
    public BukuTidakTersediaException(String pesan, String kodeBuku) {
        super(pesan);
        this.kodeBuku = kodeBuku;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }
}


/*
* Exception buatan sendiri (custom exception).
  extends Exception -> termasuk CHECKED exception, sehingga wajib di-catch atau di-throws.
Penjelasan: class ini adalah exception milik aplikasi kita. Ia mewarisi Exception sehingga bersifat checked (wajib ditangani). Selain pesan, ia membawa data tambahan berupa kode buku yang bermasalah.
 */