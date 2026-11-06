/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package No2_HandlingException;

public class HandlingException {
    public static void main(String[] args) {
        try {
            System.out.println(3/0);
        } catch (ArithmeticException ex) {
            System.out.println("Error " + ex);
            // HASIL: Error java.lang.ArithmeticException: / by zero

            System.out.println("getMessage: " + ex.getMessage());
            // HASIL: getMessage: / by zero (hanya pesan, tanpa nama class)

            System.out.println("getStackTrace: " + java.util.Arrays.toString(ex.getStackTrace()));
            // HASIL: array jejak pemanggilan (class, method, nomor baris)

            ex.printStackTrace();
            // HASIL: mencetak exception + jejak pemanggilan, program tetap lanjut
        }
        // Soal 2b: jika catch (ArithmeticException ex) diganti catch (Exception ex),
        // hasil tetap sama karena Exception adalah induk dari ArithmeticException,
        // hanya cakupan catch menjadi lebih luas.
    }
}

/*Penjelasan kode:

Kode berisiko (3/0) ditaruh di blok try.
Saat exception terjadi, program loncat ke blok catch dan tidak crash.
Di dalam catch, objek ex dicetak dengan empat cara berbeda sesuai soal 2a.

Jawaban:
a. Perbedaan hasil:
ex mencetak nama class dan pesan (java.lang.ArithmeticException: / by zero).
ex.getMessage() hanya mencetak pesannya (/ by zero).
ex.getStackTrace() berupa array jejak pemanggilan (asalnya: class, method, nomor baris).
ex.printStackTrace() mencetak exception beserta jejak pemanggilan, tetapi program tetap lanjut.

b. Ganti ArithmeticException dengan Exception: ubah catch (ArithmeticException ex) menjadi catch (Exception ex). 
Hasilnya tetap sama (Error java.lang.ArithmeticException: / by zero). Exception adalah induk semua exception,
jadi ArithmeticException ikut tertangkap. Bedanya, catch menjadi lebih luas dan kurang spesifik.
*/