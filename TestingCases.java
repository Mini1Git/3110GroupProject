

import static org.junit.Assert.*;

import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

import org.junit.Test;

public class TestingCases {
    String path = "src/resources/"; // just change this if you need to.
    MyReader file1 = new MyReader(path +"GC_1.java");
    MyReader file2 = new MyReader(path +"GC_2.java");
    String xmlFile = path + "GC.xml";
    @Test
    public void testing() throws Exception {
        ArrayList<String> fileList1 = file1.listConverter();
        ArrayList<String> fileList2 = file2.listConverter();
        LineComparator comparator = new LineComparator(fileList1, fileList2);
        comparator.compare();
        ArrayList<int []> results = comparator.getMatched();
        List<Integer> range = new ArrayList<>();

        try{
            if(xmlFile != null){
                range = getRange(xmlFile);
            }

        }
        catch(Exception e){
            System.out.println("xml error");
            e.printStackTrace();
        }
        //System.out.println(results);




        for (int[] pair : results){
            //store the pair of ints
            //we should prob skip absolute matches for test cases.

            int x = pair[0];
            int y = pair[1];





            //basically, input the range of X, x being first file line range.
            //if (x >= range.get(0) && x <= range.get(range.size()-1)){

            System.out.println("First File: "+ x + " Second File: " + y);
            getLine(x, y);

            //}
        }

    }
    //for getLine, input the x, being the line number of the first file, and the line number of the second.
    public void getLine(int x, int y){

        System.out.println(file1.listConverter().get(x).trim() + " |and| " + file2.listConverter().get(y).trim());
    }

    //made this function so its easier to test within the range of the data set test cases.
    public List<Integer> getRange(String xmlFile) throws Exception{
        List<Integer> range = new ArrayList<>();
        File file = new File(xmlFile);
        //document builder is related to xml parsers, lets you load xml file as a tree of nodes.
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // factory design pattern lol
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // is the parser
        Document doc = dBuilder.parse(file); // parses the file

        // now we need to get the nodes
        NodeList nodeList = doc.getElementsByTagName("LOCATION"); // <LOCATION ORIG="114" NEW="114" /> // these uses the LOCATION TAG.
        //Thus, each location tag becomes a ELEMENT object
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            //then get the attribute
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node; // cast
                int number = Integer.parseInt(element.getAttribute("ORIG"));
                range.add(number);
            }

        }
        return range;
    }



}
