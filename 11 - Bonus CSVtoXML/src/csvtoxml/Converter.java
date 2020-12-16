/**
 * -----------------------------------------------------------------------------
 *           Fundamentals of Java  -  Homework Assignment #11
 * -----------------------------------------------------------------------------
 * Purpose: Calls convertToXML function with input and output file name.
 *
 */
package csvtoxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author IR on T3500
 */

class Converter {
    private String splitter;
    private Document finalXML;
    private Element rootElement;
    private BufferedReader br;
    private ArrayList<String> tagNames;
    private String ipFile;

    Converter(){
        splitter = ",";
        finalXML = null;
        rootElement = null;
        br = null;
    }

    /**
     * Purpose: 1. Check format of input file is csv
     *          2. Reads input file. First line in CSV is for XML tag names.
     *              if csvHeader line exists, calls addRecordToRoot function with csvHeader line
     *
     *
     * @return Boolean : true for successful file creation, else false
     */
    public Boolean convertToXML(String inputFile, String outputFile)
    {
        ipFile = inputFile;
        try{

            String extension = inputFile.substring(inputFile.lastIndexOf('.')+1,inputFile.length());

            if( !(extension.equalsIgnoreCase("csv")) ){         // file extension not csv, return false
                System.out.println("Error: Input File "+inputFile+" format is incorrect. Aborted. Try again with .csv");
                return false;
            }
            InputStream in = getClass().getResourceAsStream("/"+inputFile);
            if(in == null){
                throw new FileNotFoundException();
            }
            br = new BufferedReader(new InputStreamReader(in));
            String csvHeader = br.readLine();
            if(csvHeader != null){
                addRecordToRoot(csvHeader, outputFile);

            } else {        //csvHeader is null means input file is empty. Return false
                System.out.println("Error: Input File "+inputFile+" is empty. Aborted.");
                return false;
            }
        }catch (FileNotFoundException e){
            System.out.println("Error: Input File "+inputFile+" not found. Aborted.");
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(br != null){
                try{
                    br.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Succcess: File "+ipFile+" converted to "+outputFile);
        return true;
    }

    /**
     * Purpose: 1. Uses csvHeader to generate XML tag Names.
     *          2. addElementsToRecord fn call to add each element to record.
     *          2. Each record is appended to root element.
     *          3. generateXMLFile fn call to generate XML file with outputfile name.
     *
     */
    private void addRecordToRoot(String csvHeader, String outputFile){
        try {
            finalXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            rootElement = finalXML.createElement("root");
            finalXML.appendChild(rootElement);
            tagNames = new ArrayList<String>(Arrays.asList(csvHeader.split(splitter)));
            String csvRecord;
            while((csvRecord = br.readLine()) != null){
                addElementsToRecord(csvRecord);
            }
            generateXmlFile(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose: Uses tagNames to create xml tags and with record values.
     *
     *
     */
    private void addElementsToRecord(String csvRecord){
        ArrayList<String> recordNode = new ArrayList<String>(Arrays.asList(csvRecord.split(splitter)));
        Element record = finalXML.createElement("record");
        rootElement.appendChild(record);
        int valueIndex = 0;
        for(String tag: tagNames){
            Element el = finalXML.createElement(tag);
            String value;
            if(valueIndex >= recordNode.size()){
                value = "NA";
                System.out.println("Warning: Input file "+ipFile);
                System.out.println(tagNames);
                System.out.println(csvRecord);
                System.out.println("Warning: No value for header "+tag+". Assigned NA");
            }
            else {
                value = recordNode.get(valueIndex);
            }
            el.appendChild(finalXML.createTextNode(value));
            record.appendChild(el);
            valueIndex++;

        }

        while(valueIndex < recordNode.size())
        {
            System.out.println("Warning: Input file "+ipFile);
            System.out.println("Warning: Dropped value "+recordNode.get(valueIndex)+" since no header");
            valueIndex++;
        }
    }

    /**
     * Purpose: outputFile name is used to generate new XML file using created XML DOM.
     *
     *
     */
    private void generateXmlFile(String outputFile){
        DOMSource dom = new DOMSource(finalXML);
        StreamResult result = new StreamResult(new File(outputFile));
        try {
            TransformerFactory.newInstance().newTransformer().transform(dom, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}

