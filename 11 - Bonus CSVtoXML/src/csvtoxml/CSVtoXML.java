/**
 * -----------------------------------------------------------------------------
 *           Fundamentals of Java  -  Homework Assignment #11
 * -----------------------------------------------------------------------------
 * Purpose: Calls convertToXML function with input and output file name.
 *
 */
package csvtoxml;

/**
 *
 * @author IR on T3500
 */
public class CSVtoXML {
   public static void main(String[] args) {
        Converter con = new Converter();
        con.convertToXML("sample.csv","finalXML.xml");
    } 
}
