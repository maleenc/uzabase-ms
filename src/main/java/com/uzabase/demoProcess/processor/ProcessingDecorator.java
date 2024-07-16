package com.uzabase.demoProcess.processor;

import java.util.List;

public abstract class ProcessingDecorator implements BaseProcessor {

    BaseProcessor baseProcessor;

    public ProcessingDecorator(BaseProcessor abstractProcessor) {
        this.baseProcessor = abstractProcessor;
    }

    @Override
    public List<String> transform(List<String> originalText, String type) throws Exception {
        return baseProcessor.transform(originalText, type);
    }
}
