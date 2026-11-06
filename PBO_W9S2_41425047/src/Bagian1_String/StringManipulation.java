/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bagian1_String;

public class StringManipulation {
    public static void main(String[] args) {
        // Sengaja ada 2 spasi di awal dan akhir untuk mendemonstrasikan trim()
        String text = "  Hello World! Welcome to Java Programming  ";

        // ===== BASIC STRING OPERATIONS =====
        System.out.println("=== BASIC STRING OPERATIONS ===");
        System.out.println("Original: '" + text + "'");
        System.out.println("Length: " + text.length());                 // jumlah karakter (spasi ikut dihitung)
        System.out.println("Trimmed: '" + text.trim() + "'");           // trim() membuang spasi di awal & akhir
        System.out.println("Uppercase: '" + text.toUpperCase() + "'");  // semua huruf jadi kapital
        System.out.println("Lowercase: '" + text.toLowerCase() + "'");  // semua huruf jadi kecil

        // ===== SUBSTRING DAN INDEXING =====
        System.out.println("\n=== SUBSTRING & INDEXING ===");
        System.out.println("Character at index 5: '" + text.charAt(5) + "'");       // karakter pada indeks ke-5
        System.out.println("Substring (7-12): '" + text.substring(7, 12) + "'");    // indeks 7 s.d. 11 (12 tidak ikut)
        System.out.println("Index of 'World': " + text.indexOf("World"));           // posisi pertama "World"
        System.out.println("Last index of 'a': " + text.lastIndexOf("a"));          // posisi terakhir huruf 'a'

        // ===== REPLACEMENT DAN COMPARISON =====
        System.out.println("\n=== REPLACEMENT & COMPARISON ===");
        System.out.println("Replace 'Java' with 'Python': '" +
                text.replace("Java", "Python") + "'");                               // ganti "Java" -> "Python"
        System.out.println("Contains 'Programming': " + text.contains("Programming"));
        System.out.println("Starts with 'Hello': " + text.startsWith("Hello"));      // false! karena ada spasi di awal
        System.out.println("Ends with 'Programming': " + text.endsWith("Programming")); // false! ada spasi di akhir

        // ===== SPLIT DAN JOIN =====
        System.out.println("\n=== SPLIT & JOIN ===");
        String[] words = text.trim().split(" ");                                     // pecah per spasi (setelah di-trim)
        System.out.println("Number of words: " + words.length);
        System.out.print("Words: ");
        for (String word : words) {
            System.out.print("[" + word + "] ");
        }
        System.out.println();

        // ===== STRINGBUILDER UNTUK EFISIENSI =====
        System.out.println("\n=== STRING BUILDER ===");
        StringBuilder sb = new StringBuilder();
        sb.append("Java");                 // tambah di belakang
        sb.append(" Programming");
        sb.insert(0, "Learn ");            // sisipkan di indeks 0 (paling depan)
        sb.reverse();                      // balik seluruh teks
        System.out.println("Reversed: " + sb.toString());

        // ===== JAWABAN PERTANYAAN =====
        System.out.println("\n=== JAWABAN PERTANYAAN ===");
        // Pertanyaan 1: startsWith("Hello") false karena karakter pertama adalah spasi, bukan 'H'.
        // Solusi: trim() dulu, lalu startsWith.
        System.out.println("startsWith('Hello') setelah trim: " + text.trim().startsWith("Hello"));
        // Pertanyaan 3: mengambil hanya kata "Java"
        int awal = text.indexOf("Java");                                   // cari posisi "Java"
        System.out.println("Cara 1 (substring): " + text.substring(awal, awal + "Java".length()));
        System.out.println("Cara 2 (array hasil split): " + words[4]);       // kata ke-5 (indeks 4)

        // ===== MODIFIKASI =====
        System.out.println("\n=== MODIFIKASI 1: Kata dengan panjang > 4 karakter ===");
        tampilkanKataPanjang(text, 4);

        System.out.println("\n=== MODIFIKASI 2: Jumlah huruf vokal ===");
        System.out.println("Jumlah vokal: " + hitungVokal(text));
    }

    // MODIFIKASI 1: menampilkan hanya kata yang panjangnya lebih dari batas
    public static void tampilkanKataPanjang(String teks, int batas) {
        String[] kata = teks.trim().split("\\s+");        // \\s+ = satu atau lebih spasi
        for (String k : kata) {
            if (k.length() > batas) {                     // saring berdasarkan panjang kata
                System.out.println(k + " (" + k.length() + " karakter)");
            }
        }
    }

    // MODIFIKASI 2: menghitung jumlah huruf vokal (a, i, u, e, o), tidak peduli besar/kecil
    public static int hitungVokal(String teks) {
        int jumlah = 0;
        for (char c : teks.toLowerCase().toCharArray()) {  // ubah ke huruf kecil agar 'A' dan 'a' sama
            if ("aiueo".indexOf(c) != -1) {                // indexOf == -1 artinya bukan vokal
                jumlah++;
            }
        }
        return jumlah;
    }
}

/*
BAGIAN 1: STRING MANIPULATION
1. Mengapa startsWith("Hello") mengembalikan false?
Karena metode startsWith("Hello") memeriksa apakah string diawali dengan kata "Hello". Jika kata tersebut tidak berada di bagian paling awal string, maka hasilnya adalah false.
2. Apa perbedaan antara String dan StringBuilder?
String bersifat immutable (tidak dapat diubah setelah dibuat), sedangkan StringBuilder bersifat mutable (dapat diubah tanpa membuat objek baru). StringBuilder lebih efisien untuk operasi perubahan teks berulang.
3. Bagaimana cara mengambil hanya kata "Java" dari string tersebut?
Dengan menggunakan metode substring() atau split() untuk mengambil bagian string yang berisi kata "Java".
*/