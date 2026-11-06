/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package No3_CaughtExceptionFinally;

public class CaughtExceptionFinally {
    public static void main(String args[]) {
        int n = args.length;
        try {
            if (n == 0) {
                System.out.println(3/n);
                System.out.println("This won't be executed");
            } else {
                System.out.println("This will be executed if " + "arguments is not zero");
            }
        } catch (Exception e) {
            System.out.println("Catching exception: " + e);
        } finally {
            System.out.println("After trying or catching...");
        }
    }
}
// HASIL tanpa argument:
//   Catching exception: java.lang.ArithmeticException: / by zero
//   After trying or catching...
// HASIL dengan argument:
//   This will be executed if arguments is not zero
//   After trying or catching...
// KESIMPULAN: blok finally SELALU dijalankan, ada exception atau tidak.

/*Penjelasan kode:
args.length adalah jumlah argument saat program dijalankan. Nilainya disimpan di n.
Jika n == 0, 3/n menjadi pembagian nol, sehingga exception terjadi dan baris setelahnya dilewati.
Blok catch (Exception e) menangani exception.
Blok finally selalu dijalankan di akhir.

Jawaban:
a. Tanpa argument (java CaughtExceptionFinally): Catching exception: java.lang.ArithmeticException: / by zero, lalu After trying or catching....
b. Dengan argument (java CaughtExceptionFinally x): This will be executed if arguments is not zero, lalu After trying or catching....
c. Kesimpulan: finally selalu dieksekusi, ada atau tidak ada exception. Cocok untuk pekerjaan yang wajib dilakukan, seperti menutup file atau koneksi.
*/