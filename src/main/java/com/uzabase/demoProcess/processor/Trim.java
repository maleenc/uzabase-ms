package com.uzabase.demoProcess.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trim extends ProcessingDecorator {

    public static final int TRIM_LENGTH = 10;

    public Trim(BaseProcessor baseProcessor) {
        super(baseProcessor);
    }

    @Override
    public List<String> transform(List<String> originalText, String type) throws Exception {
        List<String> trimmedLines = new ArrayList<>();
        if (type.equals("rss")) {
            processRssData(originalText, trimmedLines);
        } else if (type.equals("csv")) {
            processCSVData(originalText, trimmedLines);
        }
        processRssData(originalText, trimmedLines);
        return trimmedLines;
    }

    private static void processRssData(List<String> originalText, List<String> trimmedLines) {
        String regex = "<([^>]+)>(.*?)</\\1>";
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        originalText.forEach(x -> {
            String originalTrimmedLine = x.trim();
            Matcher matcher = pattern.matcher(originalTrimmedLine);
            String content;
            String tagName;
            if (matcher.find()) {
                tagName = matcher.group(1);
                content = matcher.group(2);
            } else {
                return;
            }
            String trimmedContent = content.trim();
            String finalContent = trimmedContent.length() > TRIM_LENGTH ? trimmedContent.substring(0, TRIM_LENGTH) : trimmedContent;
            trimmedLines.add(tagName + ": " + finalContent);
        });
    }

    private void processCSVData(List<String> originalText, List<String> replacedLines) {
        originalText.forEach(x -> {
            AtomicReference<String> csvLine = new AtomicReference<>("");
            Arrays.asList(x.split(",")).forEach(v -> {
                String trimmedValue = v.length() > TRIM_LENGTH ? v.substring(0, TRIM_LENGTH) : v;
                csvLine.set(csvLine.get() + trimmedValue + ",");
            });
            replacedLines.add(csvLine.get());
        });
    }
}
