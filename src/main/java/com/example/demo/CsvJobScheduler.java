package com.example.demo;



import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CsvJobScheduler {
    private final CsvBatchJob job = new CsvBatchJob();

    @Scheduled(fixedRate = 1000) // Schedule to run every second
    public void runCsvJob() {
        job.processCsv("input.csv", "output.csv");
    }
}
