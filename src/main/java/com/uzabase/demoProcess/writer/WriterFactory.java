package com.uzabase.demoProcess.writer;

public class WriterFactory {

    public static OutputWriter createWriter(String type, String destination) {
        switch (type.toLowerCase()) {
            case "file":
                return new CSVWriter(destination);
            case "print":
                return new PrintWriter();
            default:
                throw new IllegalArgumentException("Unknown output type: " + type);
        }
    }
}
