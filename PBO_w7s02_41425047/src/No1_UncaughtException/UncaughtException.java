/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package No1_UncaughtException;

/**
 *
 * @author ASUS
 */

public class UncaughtException {
    public static void main(String args[]) {
        System.out.println(3/0);
        // HASIL: Exception in thread "main" java.lang.ArithmeticException: / by zero
        //        at No1_UncaughtException.UncaughtException.main(UncaughtException.java:13)
        // Program berhenti (crash) karena exception tidak ditangkap oleh try-catch.
        // BUILD FAILED di NetBeans hanya laporan kode keluar 1, bukan error kode.
    }
}


/*Penjelasan kode:

Statement 3/0 adalah pembagian bilangan bulat dengan nol, yang tidak diperbolehkan di Java.
Java melempar ArithmeticException dengan pesan / by zero.
Tidak ada try-catch yang menangani exception itu, sehingga exception naik sampai ke JVM dan program berhenti dengan pesan error.

Jawaban:
a. Analisis: program crash karena exception tidak ditangani.
b. Output:
  Exception in thread "main" java.lang.ArithmeticException: / by zero
  	at UncaughtException.main(UncaughtException.java:3)

*/