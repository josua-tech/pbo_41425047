/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bagian3_TextProcessing;

import java.io.*;
import java.util.*;

public class TextProcessor {
    public static void main(String[] args) {
        String sourceFile = "source.txt";
        String processedFile = "processed.txt";
        String reportFile = "report.txt";

        // Buat file sumber contoh
        createSampleFile(sourceFile);

        // Process file: uppercase + statistics
        processTextFile(sourceFile, processedFile, reportFile);

        // Tampilkan report
        displayReport(reportFile);
    }

    // Membuat file sumber berisi 5 kalimat
    public static void createSampleFile(String filename) {
        String[] sampleText = {
            "Java programming is fun and powerful.",
            "Streams and Files are important concepts.",
            "Exception handling makes code robust.",
            "String manipulation is frequently used.",
            "Practice makes perfect in programming."
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : sampleText) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Sample file created: " + filename);
        } catch (IOException e) {
            System.out.println("Error creating sample file: " + e.getMessage());
        }
    }

    // Membaca inputFile, menulis versi UPPERCASE ke outputFile, dan membuat laporan statistik
    public static void processTextFile(String inputFile, String outputFile, String reportFile) {
        // 3 resource sekaligus; semuanya otomatis ditutup oleh try-with-resources
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
             BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFile))) {

            String line;
            int totalLines = 0;
            int totalWords = 0;
            int totalChars = 0;
            int totalSentences = 0;                                       // MODIFIKASI MUDAH
            Map<Character, Integer> charFrequency = new HashMap<>();
            Map<String, Integer> wordFrequency = new TreeMap<>();         // MODIFIKASI SEDANG (TreeMap = terurut A-Z)

            while ((line = reader.readLine()) != null) {
                totalLines++;

                // Process line: convert to uppercase
                String processedLine = line.toUpperCase();
                writer.write(processedLine);
                writer.newLine();

                // Calculate statistics
                String[] words = line.split("\\s+");                      // pecah per satu/lebih spasi
                totalWords += words.length;
                totalChars += line.length();

                // MODIFIKASI MUDAH: hitung kalimat = jumlah tanda titik
                for (char c : line.toCharArray()) {
                    if (c == '.') {
                        totalSentences++;
                    }
                }

                // MODIFIKASI SEDANG: hitung frekuensi tiap kata
                for (String w : words) {
                    String bersih = w.toLowerCase().replaceAll("[^a-z]", ""); // buang tanda baca
                    if (!bersih.isEmpty()) {
                        wordFrequency.put(bersih, wordFrequency.getOrDefault(bersih, 0) + 1);
                    }
                }

                // Character frequency
                for (char c : line.toLowerCase().toCharArray()) {
                    if (Character.isLetter(c)) {
                        // getOrDefault: ambil hitungan lama (atau 0 jika belum ada), lalu +1
                        charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
                    }
                }
            }

            // Write report
            reportWriter.write("=== TEXT PROCESSING REPORT ===");
            reportWriter.newLine();
            reportWriter.write("Total Lines: " + totalLines);
            reportWriter.newLine();
            reportWriter.write("Total Words: " + totalWords);
            reportWriter.newLine();
            reportWriter.write("Total Characters: " + totalChars);
            reportWriter.newLine();
            reportWriter.write("Average Words per Line: " + (double) totalWords / totalLines);
            reportWriter.newLine();
            reportWriter.write("Total Sentences: " + totalSentences);       // MODIFIKASI MUDAH
            reportWriter.newLine();

            // MODIFIKASI SEDANG: cari frekuensi tertinggi lalu kumpulkan semua kata yang sama tingginya
            int maks = 0;
            for (int f : wordFrequency.values()) {
                if (f > maks) {
                    maks = f;
                }
            }
            List<String> terbanyak = new ArrayList<>();
            for (Map.Entry<String, Integer> e : wordFrequency.entrySet()) {
                if (e.getValue() == maks) {
                    terbanyak.add(e.getKey());
                }
            }
            reportWriter.write("Most Frequent Word(s): " + terbanyak + " (" + maks + "x)");
            reportWriter.newLine();
            reportWriter.newLine();
            reportWriter.write("CHARACTER FREQUENCY:");
            reportWriter.newLine();

            // Sort characters by frequency (terbanyak dulu)
            List<Map.Entry<Character, Integer>> sortedChars = new ArrayList<>(charFrequency.entrySet());
            sortedChars.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            for (Map.Entry<Character, Integer> entry : sortedChars) {
                reportWriter.write(entry.getKey() + ": " + entry.getValue());
                reportWriter.newLine();
            }

            System.out.println("File processing completed!");
            System.out.println("  - Input: " + inputFile);
            System.out.println("  - Output: " + outputFile);
            System.out.println("  - Report: " + reportFile);

        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }

    // Membaca file laporan dan menampilkannya ke layar
    public static void displayReport(String reportFile) {
        System.out.println("\n=== PROCESSING REPORT ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading report: " + e.getMessage());
        }
    }
}
