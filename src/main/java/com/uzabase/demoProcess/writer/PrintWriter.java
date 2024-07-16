package com.uzabase.demoProcess.writer;

import java.util.List;

public class PrintWriter implements OutputWriter {
    @Override
    public void write(List<String> data) {
        data.forEach(System.out::println);
    }
}
