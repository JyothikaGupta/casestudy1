package com.example.demo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;

import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvBatchJob {
    private static final Logger LOGGER = Logger.getLogger(CsvBatchJob.class.getName());

    public void processCsv(String inputFilePath, String outputFilePath) {
        List<String[]> processedData = new ArrayList<>();

        // Reading the CSV file
        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Process data: Convert each field to uppercase
                for (int i = 0; i < nextLine.length; i++) {
                    nextLine[i] = nextLine[i].toUpperCase();
                }
                processedData.add(nextLine);
            }
        } catch (IOException | CsvValidationException e) {
            LOGGER.log(Level.SEVERE, "Error reading the CSV file", e);
            return; // Exit if an error occurs
        }

        // Writing to the output CSV file
        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(outputFilePath))
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER) // Prevent quotes
                .build()) {
            writer.writeAll(processedData);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing the CSV file", e);
        }


    }
}
