
package com.mycompany.josua_r_s_sitorus_047;

import java.io.*;

public class Josua_R_S_Sitorus_047 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(
                new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(
                new FileWriter("output.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                bw.write(line.toUpperCase());
                bw.newLine();
            }

            System.out.println("Konversi selesai!");
 
        } catch (IOException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        }
    }
}
