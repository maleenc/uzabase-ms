package com.uzabase.demoProcess.controller;
import com.uzabase.demoProcess.dto.ProcessRequest;
import com.uzabase.demoProcess.processor.BaseProcessor;
import com.uzabase.demoProcess.processor.BaseProcessorImpl;
import com.uzabase.demoProcess.processor.ProcessorFactory;
import com.uzabase.demoProcess.reader.InputReader;
import com.uzabase.demoProcess.reader.ReaderFactory;
import com.uzabase.demoProcess.writer.OutputWriter;
import com.uzabase.demoProcess.writer.WriterFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/process")
public class ProcessorController {

    @PostMapping
    public String process(@RequestBody ProcessRequest request) {
        try {
            String input = request.getInput();
            String inputSource = request.getInputSource();
            String operation = request.getOperation();
            String output = request.getOutput();
            String outputSource = request.getOutputSource();
            String replaceFrom = request.getReplaceFrom();
            String replaceTo = request.getReplaceTo();

            if(input == null || output == null || operation == null || inputSource == null){
                return "Required parameters missing. --input, --inputSource, --output, and --operation are mandatory";
            }
            if(output.equals("file") && outputSource == null){
                return "outputSource is required with the output=file";
            }
            if(operation.equals("replace") && (replaceTo ==null || replaceFrom == null)){
                return "replace is used with replaceTo and replaceFrom";
            }

            InputReader reader = ReaderFactory.createReader(input, inputSource);
            OutputWriter writer = WriterFactory.createWriter(output, outputSource);

            BaseProcessor baseProcessor = new BaseProcessorImpl();
            BaseProcessor processorFactory = ProcessorFactory.createTransformer(operation, baseProcessor, replaceFrom, replaceTo);

            List<String> data = reader.read();
            List<String> formattedData = processorFactory.transform(data, input);
            writer.write(formattedData);

            return "Processing completed successfully";
        } catch (Exception e) {
            return "Error during processing: " + e.getMessage();
        }
    }

    @GetMapping
    public String processSampleGet() {
        return "testService";
    }
}
