

import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class TestingCases {



    public static void run(MyReader file1, MyReader file2, String xmlFile) throws Exception {



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
            getLine(file1, file2, x, y);



            //}
        }
        System.out.println(percentageTest(results, xmlFile) + "%");

    }
    //for getLine, input the x, being the line number of the first file, and the line number of the second.
    private static void getLine(MyReader file1, MyReader file2, int x, int y){

        System.out.print("\t\""+file1.listConverter().get(x).trim() + "\" |and| \"" + file2.listConverter().get(y).trim()+"\"\n");
    }

    //made this function so its easier to test within the range of the data set test cases.
    private static List<Integer> getRange(String xmlFile) throws Exception{
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

    private static double percentageTest(ArrayList<int[]> ourListOfY, String xmlFile) throws Exception{
        List<Integer> correctDataset = new ArrayList<>();
        List<Integer> correctDataset2 = new ArrayList<>();
        if (xmlFile == null){
            return -1;
        }
        File file = new File(xmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        NodeList versionTagList = doc.getElementsByTagName("VERSION");
        Element versionNum = (Element) versionTagList.item(0);

        //got the correct version ^
        NodeList locationList = versionNum.getElementsByTagName("LOCATION");
        // got correct list ^
        //now can iterate through the correct dataset list.
        for (int i = 0; i < locationList.getLength(); i++){
            Node node = locationList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                int number = Integer.parseInt(element.getAttribute("ORIG"));
                int number2 = Integer.parseInt(element.getAttribute("NEW"));
                correctDataset.add(number);
                correctDataset2.add(number2);
            }

        }
        int countCorrect = 0;
        for (int i = 0; i < correctDataset.size(); i++){

            if (ourListOfY.get(i)[0] == (correctDataset.get(i)) && ourListOfY.get(i)[1] == correctDataset2.get(i)){
                countCorrect++;

            }
            else{
                System.out.println("Missing: " + correctDataset.get(i) + " and " + correctDataset2.get(i) + " Instead, got: " + ourListOfY.get(i)[0] + " and " + ourListOfY.get(i)[1]);
            }
        }
        double percentage = (double) countCorrect / correctDataset.size() * 100;

        return percentage;
    }
// TODO: Sort the XML files (after finishing them) to be in order numbered X. maek a function, to match prof's format.

}
