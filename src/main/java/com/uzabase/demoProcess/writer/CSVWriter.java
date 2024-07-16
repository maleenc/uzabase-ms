package com.uzabase.demoProcess.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class CSVWriter implements OutputWriter {


    String filepath;

    public CSVWriter(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void write(List<String> data) {
        System.out.println("AAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAA");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            System.out.println("AAAAAAAAAAAAA");
            System.out.println("AAAAAAAAAAAAA");
            System.out.println(data);
            for (String line : data) {
                System.out.println("===============");
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data has been written to " + filepath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
