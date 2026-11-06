/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package No4_CatchNullPointer;

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
        } catch (NullPointerException e) {      // sebelumnya: Exception e
            System.out.println("Catching exception: " + e);
        } finally {
            System.out.println("After trying or catching...");
        }
    }
}

/*Penjelasan kode:

Exception yang terjadi tetap ArithmeticException, tetapi catch sekarang hanya menerima NullPointerException.
Jenis exception tidak cocok, sehingga catch dilewati.
Blok finally tetap dijalankan, setelah itu exception naik ke JVM dan program crash.

Jawaban: tanpa argument, finally tercetak dulu, lalu program crash dengan ArithmeticException. Jika sebuah exception tidak punya catch yang cocok, exception itu tidak tertangani, naik ke JVM, dan program berhenti. finally tetap jalan sebelum program berhenti.
*/