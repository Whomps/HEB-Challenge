// HEB Histogram for programming challenge
// Derek Rodriguez - 4/14/2018
// Over-built and over-commented for clarity
// Usage: java Histogram [input_file] [output_file]

import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

// Histogram with following assumptions:
// (1) Performance is a concern, but not as far as min-maxing
// (2) Collecting performance is prefered to printing performance
public class Histogram {
    HashMap<String,Integer> word_freq;
    
    public Histogram() {
        word_freq = new HashMap<String,Integer>();
    }
    

    // Get a String, parse into words, add each to histogram
    public void addString(String s) {
        System.out.printf("Adding next word: %s\n", s);
    }
    
    // Get a file, parse strings
    public void addFile(String fn) throws FileNotFoundException {
        // Auto file handling w/ exceptions
        try(BufferedReader buffRead = new BufferedReader(new FileReader(fn))) {
            String line = null;
            
            // Read in file by line, parse by line
            while((line = buffRead.readLine()) != null) {
                
                // Upon reading a new line, parse out unwanted characters
                String[] parsedLine = line.replaceAll("[^a-zA-Z1-9 ]", "").toLowerCase().split("\\s+");
                
                // Then add each word to the histogram via addString
                for(String word : parsedLine) 
                    { addString(word); }
            }
            
        } catch(IOException e) { e.printStackTrace(); }
    }
    
    // Draw Histogram in HEB-specified format
    public void drawHEBFormat() {
        
    }
    
    // Parse input parameters, take in/output filenames
    //   and generate histogram in HEB-specified format
    public static void main(String[] args) {
        System.out.printf("\nHello!!\n");
        String inputFile = null, outputFile = null;
        
        // Some defaults
        if(args.length < 2) { outputFile = "output.txt"; }
        else if(args.length < 1) { 
            inputFile = "input.txt"; 
            outputFile = "output.txt"; 
        }
        else {
            inputFile = args[0];
            outputFile = args[1];
        }
        
        Histogram hg = new Histogram();
        try { hg.addFile(inputFile); }
        catch (IOException e) { e.printStackTrace(); }
        
    }
    
    
    
}