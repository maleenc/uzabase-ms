package com.uzabase.demoProcess.processor;

public class ProcessorFactory {

    public static BaseProcessor createTransformer(String type, BaseProcessor baseProcessor, String... params) {
        switch (type.toLowerCase()) {
            case "trim":
                return new Trim(baseProcessor);
            case "replace":
                if (params.length < 2) {
                    throw new IllegalArgumentException("Replace processor missing 2 parameters");
                }
                return new Replace(baseProcessor, params[0], params[1]);
            case "trim&replace":
                if (params.length < 2) {
                    throw new IllegalArgumentException("trim&replace processor missing 2 parameters");
                }
                return new Trim(new Replace(baseProcessor, params[0], params[1]));
            default:
                throw new IllegalArgumentException("Unknown processor type: " + type);
        }
    }

}
