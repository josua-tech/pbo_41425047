/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bagian3_TextProcessing;

import java.io.*;
import java.util.*;

/*
 * MODIFIKASI SULIT: memproses file CSV dan menghasilkan summary statistics
 * (jumlah, min, max, total, rata-rata, median, standar deviasi) untuk tiap kolom angka.
 */
public class CsvStatistics {
    public static void main(String[] args) {
        String csvFile = "nilai.csv";
        String summaryFile = "csv_summary.txt";

        createSampleCsv(csvFile);                 // 1. buat data contoh
        processCsv(csvFile, summaryFile);         // 2. hitung statistik dan tulis ke file
        displaySummary(summaryFile);              // 3. tampilkan hasil
    }

    // Membuat file CSV contoh (sengaja ada data kotor: "N/A" dan baris kurang kolom)
    public static void createSampleCsv(String filename) {
        String[] rows = {
            "Nama,Umur,Nilai_PBO,Nilai_Basdat",
            "Alice,20,85,90",
            "Bob,21,70,65",
            "Charlie,20,92,88",
            "Diana,22,78,N/A",          // nilai Basdat tidak valid
            "Eva,21,66,72",
            "Fajar,23,88",              // kolom kurang
            "Gita,20,95,91"
        };
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String row : rows) {
                writer.write(row);
                writer.newLine();
            }
            System.out.println("Sample CSV created: " + filename);
        } catch (IOException e) {
            System.out.println("Error creating CSV: " + e.getMessage());
        }
    }

    public static void processCsv(String csvFile, String summaryFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(summaryFile))) {

            String headerLine = reader.readLine();               // baris pertama = nama kolom
            if (headerLine == null) {
                System.out.println("File CSV kosong");
                return;
            }
            String[] header = headerLine.split(",");

            // Satu daftar angka untuk tiap kolom (mulai kolom 1; kolom 0 = Nama, bukan angka)
            List<List<Double>> kolom = new ArrayList<>();
            for (int i = 1; i < header.length; i++) {
                kolom.add(new ArrayList<>());
            }

            String line;
            int nomorBaris = 1;
            while ((line = reader.readLine()) != null) {
                nomorBaris++;
                String[] data = line.split(",");
                for (int i = 1; i < header.length; i++) {
                    if (i >= data.length) {                       // kolom hilang
                        System.out.println("Baris " + nomorBaris + ": kolom '" + header[i] + "' tidak ada");
                        continue;
                    }
                    try {
                        kolom.get(i - 1).add(Double.parseDouble(data[i].trim()));
                    } catch (NumberFormatException e) {           // nilai bukan angka
                        System.out.println("Baris " + nomorBaris + ": '" + data[i] + "' bukan angka, dilewati");
                    }
                }
            }

            writer.write("=== CSV SUMMARY STATISTICS ===");
            writer.newLine();
            for (int i = 1; i < header.length; i++) {
                List<Double> nilai = kolom.get(i - 1);
                writer.write(header[i] + ": " + ringkasan(nilai));
                writer.newLine();
            }
            System.out.println("Summary disimpan ke " + summaryFile);

        } catch (FileNotFoundException e) {
            System.out.println("File CSV tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error processing CSV: " + e.getMessage());
        }
    }

    // Menghitung statistik dari sebuah daftar angka
    private static String ringkasan(List<Double> nilai) {
        if (nilai.isEmpty()) {
            return "tidak ada data";
        }
        Collections.sort(nilai);                                  // urut naik: min di depan, max di belakang
        int n = nilai.size();
        double min = nilai.get(0);
        double max = nilai.get(n - 1);

        double total = 0;
        for (double v : nilai) {
            total += v;
        }
        double rata = total / n;

        // Median: nilai tengah (jika genap, rata-rata dua nilai tengah)
        double median = (n % 2 == 1) ? nilai.get(n / 2)
                                     : (nilai.get(n / 2 - 1) + nilai.get(n / 2)) / 2;

        // Standar deviasi populasi: akar dari rata-rata kuadrat selisih terhadap rata-rata
        double jumlahKuadrat = 0;
        for (double v : nilai) {
            jumlahKuadrat += (v - rata) * (v - rata);
        }
        double stdDev = Math.sqrt(jumlahKuadrat / n);

        return String.format("n=%d, min=%.1f, max=%.1f, total=%.1f, mean=%.2f, median=%.1f, stdDev=%.2f",
                n, min, max, total, rata, median, stdDev);
    }

    public static void displaySummary(String summaryFile) {
        System.out.println("\n=== CSV SUMMARY ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(summaryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading summary: " + e.getMessage());
        }
    }
}
/*
BAGIAN 3: INTEGRASI & STUDI KASUS
1. Bagaimana cara kerja HashMap untuk menghitung frekuensi karakter?
HashMap menyimpan setiap karakter sebagai key dan jumlah kemunculannya sebagai value. Setiap karakter yang ditemukan akan dihitung dan nilainya bertambah jika karakter tersebut sudah ada.
2. Mengapa kita menggunakan \\s+ untuk split words daripada spasi biasa?
Karena \\s+ dapat memisahkan teks berdasarkan satu atau lebih karakter whitespace, seperti spasi, tab, dan baris baru. Dengan demikian, pemisahan kata menjadi lebih akurat.
3. Apa keuntungan menggunakan BufferedReader/BufferedWriter?
Dapat meningkatkan efisiensi membaca dan menulis file karena menggunakan buffer (penyimpanan sementara), sehingga mengurangi jumlah operasi langsung ke file dan membuat proses I/O lebih cepat.
*/