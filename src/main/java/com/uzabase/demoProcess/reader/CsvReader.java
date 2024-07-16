package com.uzabase.demoProcess.reader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class CsvReader implements InputReader {
    String filePath;

    public CsvReader(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public List<String> read() {
        try {
            List<String> x = Files.lines(Paths.get(filePath)).collect(Collectors.toList());
            return x;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
