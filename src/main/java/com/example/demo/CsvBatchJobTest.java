package com.example.demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class CsvBatchJobTest {

    private final String inputFilePath = "test_input.csv";
    private final String outputFilePath = "test_output.csv";

    @BeforeMethod
    public void setUp() throws Exception {
        // Create a sample input CSV file
        Files.write(Paths.get(inputFilePath), "name,age\nJohn,30\nDoe,25\n".getBytes());
    }

    @Test
    public void testProcessCsv() throws Exception {
        CsvBatchJob job = new CsvBatchJob();
        job.processCsv(inputFilePath, outputFilePath);

        // Check the contents of the output file
        String output = new String(Files.readAllBytes(Paths.get(outputFilePath)));
        System.out.println("Actual Output: " + output); // Print actual output
        assertEquals(output.trim(), "NAME,AGE\nJOHN,30\nDOE,25");
    }


    @AfterMethod
    public void tearDown() throws Exception {
        // Clean up test files
        Files.deleteIfExists(Paths.get(inputFilePath));
        Files.deleteIfExists(Paths.get(outputFilePath));
    }
}
