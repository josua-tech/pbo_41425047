/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bagian2_FileIO;

import java.io.*;

public class FileIOExample {
    public static void main(String[] args) {
        String inputFile = "data.txt";
        String outputFile = "output.txt";

        // Tulis data ke file
        writeToFile(inputFile);

        // Baca data dari file
        readFromFile(inputFile);

        // Process dan tulis ke file baru
        processFile(inputFile, outputFile);

        // ===== MODIFIKASI =====
        System.out.println("\n##### MODIFIKASI #####");

        // Modifikasi 3: tambah data tanpa menghapus yang lama (mode append)
        appendToFile(inputFile, "Eva,27,Biology");
        appendToFile(inputFile, "Fajar,dua puluh,Economics");   // sengaja salah: usia bukan angka
        readFromFile(inputFile);

        // Modifikasi 2: hitung rata-rata usia (di dalamnya ada penanganan NumberFormatException)
        double rata = hitungRataRataUsia(inputFile);
        System.out.println("Rata-rata usia: " + rata);

        // Modifikasi 1: processFile sekarang juga aman dari NumberFormatException
        processFile(inputFile, outputFile);

        // Uji FileNotFoundException: membaca file yang tidak ada
        readFromFile("tidak_ada.txt");
    }

    // Menulis data ke file. FileWriter(filename) tanpa true = timpa isi lama
    public static void writeToFile(String filename) {
        // try-with-resources: writer otomatis ditutup (close) di akhir blok
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Alice,25,Computer Science");
            writer.newLine();
            writer.write("Bob,30,Mathematics");
            writer.newLine();
            writer.write("Charlie,22,Physics");
            writer.newLine();
            writer.write("Diana,28,Chemistry");
            writer.newLine();

            System.out.println("Data berhasil ditulis ke " + filename);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Membaca file baris demi baris
    public static void readFromFile(String filename) {
        System.out.println("\n=== MEMBACA FILE ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {   // readLine() mengembalikan null saat file habis
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }

            System.out.println("File berhasil dibaca");
        } catch (FileNotFoundException e) {                // lebih spesifik, harus di atas IOException
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {                          // masalah baca/tulis lainnya
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Membaca inputFile, mengubah menjadi format baru, dan menulis ke outputFile
    public static void processFile(String inputFile, String outputFile) {
        System.out.println("\n=== MEMPROSES FILE ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            writer.write("NAME,AGE,MAJOR,STATUS");          // baris header
            writer.newLine();

            while ((line = reader.readLine()) != null) {
                // Split data berdasarkan koma
                String[] data = line.split(",");
                String name = data[0].toUpperCase();

                // MODIFIKASI 1: Integer.parseInt bisa gagal jika teks bukan angka
                int age;
                try {
                    age = Integer.parseInt(data[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Dilewati (usia tidak valid): " + line);
                    continue;                              // lompat ke baris berikutnya
                }

                String major = data[2];
                String status = (age > 25) ? "GRADUATE" : "UNDERGRADUATE";

                // Tulis data yang telah diproses
                String processedLine = String.format("%s,%d,%s,%s", name, age, major, status);
                writer.write(processedLine);
                writer.newLine();

                System.out.println("Processed: " + processedLine);
            }

            System.out.println("File berhasil diproses dan disimpan sebagai " + outputFile);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    // MODIFIKASI 3: FileWriter(filename, true) -> true = mode APPEND (tambah di akhir, tidak menimpa)
    public static void appendToFile(String filename, String dataBaru) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(dataBaru);
            writer.newLine();
            System.out.println("Data ditambahkan: " + dataBaru);
        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }

    // MODIFIKASI 2: membaca file dan menghitung rata-rata usia
    public static double hitungRataRataUsia(String filename) {
        int total = 0;
        int jumlahValid = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    total += Integer.parseInt(data[1].trim());   // kolom ke-2 = usia
                    jumlahValid++;
                } catch (NumberFormatException e) {              // MODIFIKASI 1: usia bukan angka
                    System.out.println("Usia tidak valid, baris dilewati: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (jumlahValid == 0) {
            return 0;                                            // hindari pembagian dengan nol
        }
        return (double) total / jumlahValid;                     // cast ke double agar hasil desimal
    }
}


/*
BAGIAN 2: STREAM I/O DASAR
1. Apa keuntungan menggunakan try-with-resources?
Memudahkan pengelolaan file karena resource akan ditutup secara otomatis setelah selesai digunakan, sehingga mengurangi risiko kebocoran resource.
2. Mengapa perlu menangani FileNotFoundException dan IOException secara terpisah?
Karena FileNotFoundException terjadi ketika file tidak ditemukan atau tidak dapat dibuka, sedangkan IOException menangani kesalahan umum saat proses membaca atau menulis file. Pemisahan keduanya membuat penanganan error lebih spesifik.
3. Apa yang terjadi jika file data.txt sudah ada sebelum program dijalankan?
Jika program menggunakan FileWriter tanpa mode append, isi file lama akan dihapus dan diganti dengan data baru. Jika menggunakan mode append (true), data baru akan ditambahkan tanpa menghapus isi sebelumnya.
*/