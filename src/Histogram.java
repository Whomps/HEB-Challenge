// HEB Histogram for programming challenge
// Derek Rodriguez - 4/14/2018
// Over-built and over-commented for clarity

import java.util.Scanner;

// Histogram with following assumptions:
// (1) Entries can be one of 2^16 unicode characters
// (2) Performance is a concern, but not as far as min-maxing
// (3) Collecting performance is prefered to printing performance
public class Histogram {
    private final char[] char_freq;
    
    public Histogram() {
        // Size of char in bits, set all entries to 0 (relatively expensive)
        char_freq = new char[(int)Math.pow(2,16)] = {0};
    }
    
    // Get a single char, add it to histogram
    public void addChar(char c) {
        char_freq[Character.getNumericValue(c)]++;
    }
    
    // Get a String, add each char to histogram
    public void addString(String s) {
        
    }
    
    public void addFile(String fn) {
        
    }
    
    // Draw Histogram in HEB-specified format
    public void drawHEBFormat() {
        
    }
    
    // Parse input parameters, take input filename,
    //   generate histogram based on input parameters,
    //   draw in HEB-specified format
    public static void main(String[] args) {
        System.out.printf("\nHello!!\n");
        
        
        
    }
    
    
    
}