/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
class MultipleCatch {
    public static void main(String args[]) {
        int n = args.length;
        int a = 1;
        int c[] = new int[1];
        try {
            if (n == 0)
                a = a / (a - 1);      // a-1 = 0 -> ArithmeticException
            else
                c[21] = 5;            // indeks 21 di luar array -> ArrayIndexOutOfBoundsException
        } catch (ArithmeticException e) {
            System.out.println("Ups, seem out we have an Arithmetic " + "Exception here...");
        } catch (Exception e) {
            System.out.println("Let the other exception handler here...");
        }
    }
}

/*
Penjelasan kode:
Satu blok try boleh punya beberapa catch, dan Java memilih yang pertama yang cocok.
Tanpa argument: a / (a - 1) menjadi 1 / 0, yaitu ArithmeticException.
Dengan argument: c[21] di luar batas array (ukuran array hanya 1), yaitu ArrayIndexOutOfBoundsException.

Jawaban:
a. Tanpa argument: catch ArithmeticException yang dijalankan.
b. Dengan satu argument: catch Exception yang dijalankan, karena ArrayIndexOutOfBoundsException bukan ArithmeticException.
c. Urutan dibalik: terjadi compile error exception ArithmeticException has already been caught. 
Catch Exception di atas sudah menangkap semua exception, termasuk ArithmeticException, sehingga catch di bawahnya tidak akan pernah tercapai. 
Karena itu catch yang spesifik harus ditulis lebih dulu.*/