

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.text.DecimalFormat;
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
        ArrayList<Integer>[] unmatchedLines = comparator.getUnmatched();

        System.out.println("These are unmatched! \n"+unmatchedLines[0] + "\n" + unmatchedLines[1] + "\n");

        //TODO DONT FORGET TO SORT THE OUTPUT AS WELL AS ADD THE UNMATCHED TO OUTPUT.


        ArrayList<int []> results = comparator.getMatched();

        for (Integer x : unmatchedLines[0]){
            results.add(new int[]{x,-1}); // add unmatched
        }

        List<Integer> range = new ArrayList<>();


        //System.out.println(results);

        //sort list before testing
        sortList(results);


        for (int[] pair : results){
            //store the pair of ints
            int x = pair[0];
            int y = pair[1];

            //basically, input the range of X, x being first file line range.

            System.out.println("First File: "+ x + " Second File: " + y);getLine(file1, file2, x, y);

        }
        System.out.println(percentageTest(results, xmlFile) + "%");

    }
    //for getLine, input the x, being the line number of the first file, and the line number of the second.
    private static void getLine(MyReader file1, MyReader file2, int x, int y){
        if (y == -1){ // if no matching lines
            return;
        }
        System.out.print("\t\""+file1.listConverter().get(x).trim() + "\" |and| \"" + file2.listConverter().get(y).trim()+"\"\n");
    }
    private static void writeLineXML(BufferedWriter writer, MyReader file1, MyReader file2, int x, int y) throws IOException {

        writer.write("\t<!--\""+file1.listConverter().get(x).trim() + "\" |and| \"" + file2.listConverter().get(y).trim()+"\"-->");
    }



    private static double percentageTest(ArrayList<int[]> ourList, String xmlFile) throws Exception{

        Map<Integer, List<Integer>> correctMap = new HashMap<>();
       //use hashmap for more accurate pairings XML
        if (xmlFile == null){
            return -1;
        }
        File file = new File(xmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        try {
        Document d = dBuilder.parse(file);} // try and if not then die.
        catch (Exception e) {
            System.err.println("xml error " + file.getAbsolutePath());
            return -1;
        }
        Document doc = dBuilder.parse(file);
        NodeList versionTagList = doc.getElementsByTagName("VERSION");
        Element versionNum = (Element) versionTagList.item(versionTagList.getLength() - 1); // will always get the version we need.
        //got the correct version ^
        NodeList locationList = versionNum.getElementsByTagName("LOCATION");
        // got correct list ^
        //now can iterate through the correct dataset list.
        for (int i = 0; i < locationList.getLength(); i++){
            Node node = locationList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                int orig = Integer.parseInt(element.getAttribute("ORIG"));

                List<Integer> newList = new ArrayList<>();
                newList.add(Integer.parseInt(element.getAttribute("NEW")));

                //get alt aka line splits if there are. Prof has <alts> as line splits.
                NodeList altList = element.getElementsByTagName("ALT");
                for (int j = 0; j < altList.getLength(); j++) {
                    Element alt = (Element) altList.item(j);
                    newList.add(Integer.parseInt(alt.getAttribute("NEW")));
                }

                correctMap.put(orig, newList); // thus, we can now map ORIG to multiple lines if line split.

            }

        }
        int countCorrect = 0;


        for (int[] pair : ourList) {
                int x = pair[0];
                int y = pair[1];


                if (!correctMap.containsKey(x)) continue;// skip these, but will make it so that this will evaluate within the XML correct range.

                List<Integer> possiblePairsX = correctMap.get(x);
                List<Integer> possiblePairsY = correctMap.get(y);
                if (possiblePairsX.contains(y)) {
                    countCorrect++;
                }

            }
        double percentage = (double) (countCorrect) / (correctMap.size()) * 100;
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



    public static void createXMLMappings(String fileOne, String fileTwo, String nameOfXMLFile) throws Exception {
        //convert to MyReader.
        MyReader file1 = new MyReader(fileOne);
        MyReader file2 = new MyReader(fileTwo);

        ArrayList<String> file_1 = file1.listConverter();
        ArrayList<String> file_2 = file2.listConverter();

        LineComparator lineComparator = new LineComparator(file_1, file_2);
        lineComparator.compare();
        ArrayList<int[]> results = lineComparator.getMatched();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nameOfXMLFile +".xml" ))){
            writer.write("<VERSION NUMBER = \"1\">");
            //loop through each element in arrayList
            //sort
            sortList(results);
            for (int[] pair : results){
                //store the pair of ints
                int x = pair[0];
                int y = pair[1];

                writer.write("\t<LOCATION ORIG = \"" + x + "\" NEW = \"" + y + "\" />");
                writeLineXML(writer,file1, file2, x, y);
                writer.newLine();
            }
            writer.write("</VERSION>");
            writer.flush();
            writer.close();
            System.out.print("Results written successfully to " + nameOfXMLFile +".xml");
        } catch (IOException e) {

            System.err.println("Error writing to file " + e.getMessage());
        }
    }

    public static void writeCSVData(ArrayList<String> files1, ArrayList<String> files2, ArrayList<String> xmlFiles, ArrayList<String> nameOfPersons, boolean writeTestingResults){
         // if wanna write testing results, WriteTestingResults = true;, ELSE, write the overall percentages with var maxDiff.
        try{
            if (writeTestingResults) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("TESTING_RESULTS.csv"));
                writer.write("Author, File 1, File 2, XML File, Percentage"); // columns here
                System.out.println(xmlFiles.size());
                double total = 0;
                for (int i = 0; i < xmlFiles.size(); i++) {
                    System.out.println("Working on... " + xmlFiles.get(i));
                    File file1_Path = new File(files1.get(i));
                    File file2_Path = new File(files2.get(i));
                    File xmlFile = new File(xmlFiles.get(i));
                    writer.newLine();
                    MyReader file1 = new MyReader(file1_Path.getPath());
                    MyReader file2 = new MyReader(file2_Path.getPath());
                    LineComparator lc = new LineComparator(file1.listConverter(), file2.listConverter());

                    lc.compare();
                    ArrayList<int[]> results = lc.getMatched();
                    ArrayList<Integer>[] unmatchedLines = lc.getUnmatched();

                    for (Integer x : unmatchedLines[0]) {
                        results.add(new int[]{x, -1}); // add unmatched
                    }
                    sortList(results);
                    try {
                        writer.write(nameOfPersons.get(i) + ", " + file1_Path.getName() + ", " + file2_Path.getName() + ", " + xmlFile.getName() + ", " + percentageTest(results, xmlFiles.get(i)));

                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e.getMessage() + " " + xmlFiles.get(i));
                    }
                    System.out.println("Done " + xmlFiles.get(i) + "!");
                    //wanna calculate average of all tests.
                    total += percentageTest(results, xmlFiles.get(i));
                }
                double avg = total / xmlFiles.size();
                System.out.println("\n\nFINISHED!\nAverage is: " + avg + "%");
                writer.flush();
                writer.close();
            }
            else{
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("maxDiffPercentages.csv"));
                    writer.write("MaxDiff, Percentage");
                    DecimalFormat df = new DecimalFormat("#.##");
                    double totalPercentage;
                    DecimalFormat formatMaxDiff = new DecimalFormat("#.###");
                    double start = 0.1;
                    for (int data = 0; data < 800; data++) {
                        totalPercentage = 0;
                        double m = start + data / 1000.0;
                        System.out.println("Loop "+ (data+1) +":\n\tWorking on... maxDiff = " + formatMaxDiff.format(m));
                        for (int i = 0; i < xmlFiles.size(); i++) {

                            File file1_Path = new File(files1.get(i));
                            File file2_Path = new File(files2.get(i));
                            File xmlFile = new File(xmlFiles.get(i));
                            MyReader file1 = new MyReader(file1_Path.getPath());
                            MyReader file2 = new MyReader(file2_Path.getPath());
                            LineComparator lc = new LineComparator(file1.listConverter(), file2.listConverter());
                            lc.maxDiff = m;
                            lc.compare();
                            ArrayList<int[]> results = lc.getMatched();
                            ArrayList<Integer>[] unmatchedLines = lc.getUnmatched();

                            for (Integer x : unmatchedLines[0]) {
                                results.add(new int[]{x, -1}); // add unmatched
                            }
                            sortList(results);
                            totalPercentage += percentageTest(results, xmlFiles.get(i));



                        }
                        writer.newLine();
                        writer.write(m +", " + df.format(totalPercentage/xmlFiles.size()) + "%");
                    }
                    writer.flush();
                    writer.close();
                }
                catch (Exception e){
                    System.err.println("Error writing to file " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing to file " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error writing to file :" + e.getMessage() + "??????\n ");
            e.printStackTrace();

        }

    }



}
