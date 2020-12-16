/**
 * -----------------------------------------------------------------------------
 *    Fundamentals of Java - Homework Assignment #9 Inpu Output data
 * -----------------------------------------------------------------------------
 * 1. Download the FIFA 2019 dataset from Kaggle: https://www.kaggle.com/karangadiya/fifa19
 *    Hint: Look for the the grey download link next to the blue "New Notebook" button.
 * 2. The file is currently csv (comma separated value) format. Write a program will copy 
 *    the data from this file into a new file that is tsv (tab-separated) instead. 
 *    Your program should take these steps:
 *     1. Read in the data from the dataset
 *     2. Convert all the commas to tabs
 *     3. Create a new file called fifa-tab.tsv
 *     4. Write the modified data to that new file.
 * */
package inconvertout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Convert Comma Separated Value (CSV) file to Tab Separate Value (TSV) file 
 * @author IR
 */


public class InConvertOut {
    public static void main(String[] args) throws Exception {

        //The input CSV File
        String tsvFilePath = "/Users/T3500/Desktop/NLP/imdb_data_set/data.csv";

        //The output TSV File
        String csvFilePath = "/Users/T3500/Desktop/NLP/imdb_data_set/fifa-tab.tsv";

        convertCSVToTSVFile(csvFilePath, tsvFilePath);

    }

    /**
     * Converts a CSV file into TSV file.
      1. Read in the data from the dataset
      2. Convert all the commas to tabs
      3. Create a new file called fifa-tab.tsv
      4. Write the modified data to that new file.
     *
     * @param csvFilePath
     * @param tsvFilePath
     * @throws IOException
     */
    private static void convertCSVToTSVFile(String csvFilePath, String tsvFilePath) throws IOException {

        //TODO: If outfile does not exist, create one.

        StringTokenizer tokenizer;
        try (BufferedReader br = new BufferedReader(new FileReader(tsvFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath));) {

            int i = 0;
            for (String line; (line = br.readLine()) != null; ) {
                i++;
                if (i % 10000 == 0) {
                    System.out.println("Processed: " + i);

                }
                tokenizer = new StringTokenizer(line, "\t");

                String csvLine = "";
                String token;
                while (tokenizer.hasMoreTokens()) {
                    token = tokenizer.nextToken().replaceAll("\"", "'");
                    csvLine += "\"" + token + "\",";
                }

                if (csvLine.endsWith(",")) {
                    csvLine = csvLine.substring(0, csvLine.length() - 1);
                }

                writer.write(csvLine + System.getProperty("line.separator"));

            }

        }

    }

}
