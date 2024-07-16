package com.uzabase.demoProcess.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replace extends ProcessingDecorator {

    private final String from;
    private final String to;

    public Replace(BaseProcessor baseProcessor, String from, String to) {
        super(baseProcessor);
        this.from = from;
        this.to = to;
    }

    @Override
    public List<String> transform(List<String> originalText, String type) throws Exception {
        List<String> replacedLines = new ArrayList<>();
        if (type.equals("rss")) {
            processRssFeedData(originalText, replacedLines);
        } else if (type.equals("csv")) {
            processCSVData(originalText, replacedLines);
        }
        return replacedLines;
    }

    private void processRssFeedData(List<String> originalText, List<String> replacedLines) {
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
            String replacedContent = content.trim().replace(from, to);
            replacedLines.add(tagName + ": " + replacedContent);
        });
    }

    private void processCSVData(List<String> originalText, List<String> replacedLines) {
        originalText.forEach(x -> {
            String replacedContent = x.replace(from, to);
            replacedLines.add(replacedContent);
        });
    }


}
