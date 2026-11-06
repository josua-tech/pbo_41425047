/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package No6_ThrowExc;

public class ThrowExc {
    public static void main(String args[]) {
        try {
            System.out.println("I'm throwing an exception from here...");
            throw new NullPointerException("throw demo");
        } catch (NullPointerException e) {
            System.out.println("I'm catching an exception: [" + e + "] " + "from here...");
        }
    }
}
// HASIL:
//   I'm throwing an exception from here...
//   I'm catching an exception: [java.lang.NullPointerException: throw demo] from here...
// KESIMPULAN: kata kunci throw dipakai programmer untuk melempar exception secara manual.

/*Penjelasan kode:
Exception tidak harus muncul dari error. Programmer bisa melemparnya sendiri dengan throw new NamaException("pesan").
Begitu throw dijalankan, program langsung loncat ke catch yang cocok.
Output [java.lang.NullPointerException: throw demo] berasal dari e yang berisi nama class dan pesan “throw demo”.
*/