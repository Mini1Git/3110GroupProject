

import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class TestingCases {



    public static void run(MyReader file1, MyReader file2, String xmlFile) throws Exception {



        ArrayList<String> fileList1 = file1.listConverter();
        ArrayList<String> fileList2 = file2.listConverter();
        System.out.println(fileList1);
        System.out.println(fileList2);

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

        //sort list before testing
        sortList(results);


        for (int[] pair : results){
            //store the pair of ints
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

    private static double percentageTest(ArrayList<int[]> ourList, String xmlFile) throws Exception{
        ArrayList<Integer>[] correctDataset = new ArrayList[2];
        correctDataset[0] = new ArrayList<>();
        correctDataset[1] = new ArrayList<>();
        /*
        * ArrayList<Integer>[] → array of lists
            ArrayList<int[]> → list of arrays
*  */
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
                correctDataset[0].add(number);
                correctDataset[1].add(number2);
            }

        }
        int countCorrect = 0;



        for (int[] pair : ourList) {
            int x = pair[0];
            int y = pair[1];
            if (correctDataset[0].contains(x) && correctDataset[1].contains(y)){
                System.out.println("Correct: " + x + " " + y);
                countCorrect++;
            }


        }

        int size = 0;
        //count size of correctDataset
        for (int num : correctDataset[0]) {
            size++;

        }
        double percentage = (double) countCorrect / size * 100;

        return percentage;
    }
    private static void sortList(ArrayList<int[]> results){
        results.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
    }
// TODO refactor testingCases because its not computing correctly. The tool works, i just need better algo for testing.

}
