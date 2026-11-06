/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package No7_ReThrowExc;

/**
 *
 * @author ASUS
 */
class ReThrowExc {
    static void demoThrow() {
        try {
            System.out.println("I'm throwing an exception from demoThrow " + "method");
            throw new NullPointerException("demo");
        } catch (NullPointerException e) {
            System.out.println("I'm catching the exception [" + e + "]");
            System.out.println("But I'll throw it again...\n");
            throw e;     // dilempar ulang ke pemanggil
        }
    }

    public static void main(String args[]) {
        try {
            demoThrow();
        } catch (NullPointerException e) {
            System.out.println("I'm catching the exception [" + e + "]\n " + "thrown from demoThrow method");
        }
    }
}
// HASIL:
//   I'm throwing an exception from demoThrow method
//   I'm catching the exception [java.lang.NullPointerException: demo]
//   But I'll throw it again...
//
//   I'm catching the exception [java.lang.NullPointerException: demo]
//    thrown from demoThrow method
// Jika baris "throw e;" dihapus, catch di main TIDAK dijalankan.

/*
Penjelasan kode:
demoThrow() melempar exception, menangkapnya sendiri, mencetak pesan, lalu melemparnya lagi dengan throw e.
main memanggil demoThrow() di dalam try, jadi exception yang dilempar ulang tertangkap di sana.
Hasilnya ada dua kali penangkapan: sekali di demoThrow, sekali di main.
Jawaban:
a. Jika throw e dihapus, exception tidak lagi sampai ke main. Exception sudah selesai ditangani di demoThrow, jadi catch di main tidak dijalankan.
b. Kesimpulan: exception yang sudah ditangkap bisa dilempar ulang dengan throw e. Satu method bisa menangani sebagian (misalnya mencatat log), lalu menyerahkan exception ke pemanggil untuk keputusan lebih lanjut.
*/