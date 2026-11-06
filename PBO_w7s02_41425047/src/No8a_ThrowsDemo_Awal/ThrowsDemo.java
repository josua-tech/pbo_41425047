/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package No8a_ThrowsDemo_Awal;

/**
 *
 * @author ASUS
 */
class ThrowsDemo {
    static void throwOne() {
        System.out.println("I'm throwing a non Runtime exception that I " + "don't handle");
        System.out.println("Without specifying in method declaration");
        throw new IllegalAccessException("demo");
    }

    public static void main(String args[]) {
        throwOne();
    }
}
/*Penjelasan kode:
IllegalAccessException adalah checked exception, yaitu exception yang wajib ditangani saat compile.
Method throwOne() melemparnya tanpa throws di deklarasi method dan tanpa try-catch, sehingga kompiler menolak kode ini.
Jawaban a: program tidak bisa dijalankan karena compile error, dengan alasan di atas.
Jawaban b: jika IllegalAccessException diganti ArithmeticException, kode berhasil dikompilasi lalu crash saat dijalankan:
Exception in thread "main" java.lang.ArithmeticException: demo
	at ThrowsDemo.throwOne(ThrowsDemo.java:5)
	at ThrowsDemo.main(ThrowsDemo.java:9)
ArithmeticException adalah unchecked exception (turunan RuntimeException), jadi tidak wajib ditangkap atau dideklarasikan. Karena tidak ditangani, program crash saat runtime.
*/