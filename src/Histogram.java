// HEB Histogram for programming challenge
// Derek Rodriguez - 4/14/2018
// Over-built and over-commented for clarity
// Usage: java Histogram [input_file] [output_file]

import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

// Histogram with following assumptions:
// (1) Performance is a concern, but not as far as min-maxing
// (2) Collecting performance is prefered to printing performance
// (3) The relative position of same-occurrence-# words is not important
public class Histogram {
    HashMap<String,Integer> word_freq;  // Collect histogram stats
    int total_words;    // Increase speed and simplicity of printing
    int longest_word;   // Keep track of longest word for HEB format padding
    int highest_freq;   // Keep track of the highest frequency for printing
    
    public Histogram() {
        word_freq = new HashMap<String,Integer>();
        total_words = 0;
        longest_word = 0;
        highest_freq = 0;
    }
    

    // Get a String, parse into words, add each to histogram
    public void addString(String s) {
        Integer fc = word_freq.get(s);
        
        if(fc == null) { // First occurrence 
            word_freq.put(s, 1);
            if(s.length() > longest_word)
                { longest_word = s.length(); }
        }
        else {          // Every other occurrence
            word_freq.put(s, fc+1); 
            if(fc+1 > highest_freq)
                { highest_freq = fc+1; }
        }
        
        total_words++;
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
    public void drawHEBFormat(String outputFile) throws IOException {
        StringBuilder niceLine = new StringBuilder();
        
        // Create String matrix w/ buckets based on occurrences
        ArrayList<ArrayList<String>> freq_matrix = new ArrayList<ArrayList<String>>();
        while(freq_matrix.size() < highest_freq+1) {
            ArrayList<String> inner_list = new ArrayList<String>();    // May want to set capacity here
            freq_matrix.add(inner_list); 
        }
        
        for(String word : word_freq.keySet()) {
            freq_matrix.get(word_freq.get(word)).add(word);   // Ex: "the" (4 times) goes in [4][back]
        }
        /// The above loop should be O(n) for keys in word_freq.
        /// I believe add(word) is between O(1) and O(n) depending on memory allocated
        
        // Iterate high->low, create nice padded lines, print
        try(Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"))) {
            for(int idx = freq_matrix.size()-1; idx > 0; idx--) {
                for(int idy = 0; idy < freq_matrix.get(idx).size(); idy++) {
                    String word = freq_matrix.get(idx).get(idy);
                    
                    // Print padding
                    if(word.length() < longest_word) {
                        int diff = longest_word - word.length();
                        for(; diff > 0; diff--) { fw.write(" "); }
                    }
                
                    // Print word and pipe
                    niceLine.setLength(0);
                    niceLine.append(" ");
                    niceLine.append(word);
                    niceLine.append(" | ");
                    fw.write(niceLine.toString());
                
                    // Print equals and number
                    for(int count = idx; count > 0; count--) 
                        { fw.write("="); }
                    
                    niceLine.setLength(0);
                    niceLine.append(" (");
                    niceLine.append(idx);
                    niceLine.append(")\n");
                    fw.write(niceLine.toString());
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        
    }
    
    // Parse input parameters, take in/output filenames
    //   and generate histogram in HEB-specified format
    public static void main(String[] args) {
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
        
        try { hg.drawHEBFormat(outputFile); }
        catch (IOException e) { e.printStackTrace(); }
        
    }
    
    
    
}