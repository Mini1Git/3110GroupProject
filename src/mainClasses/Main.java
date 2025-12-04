import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) throws Exception {
        //realTool();
        // below is testing
        //finish all the XML files first, then make an array of all the file1s, file2s, and xml files. Then make loop to auto test the results.
        ArrayList<String> fileOnes = new ArrayList<>();
        ArrayList<String> fileTwos = new ArrayList<>();
        ArrayList<String> fileXMLs = new ArrayList<>();

        String daniel = "DanielTestCases/testCasesFiles/";
        String liana = "testFilesLiana/testFilesLiana/";
        String tyler = "testFilesTyler/testFilesTyler/";
        String wilson = "testFilesWilson/";


        String path = "src/resources/" + liana;

        String file_1 = "Calc_1.java";
        String file_2 = "Calc_2.java";
        String file_xml = "Calc";


        // System.out.println("Writing XML to: " + new File(path + file_xml + ".xml").getAbsolutePath());TestingCases.createXMLMappings(path+file_1, path+file_2, path+file_xml);
        testing(path, file_1, file_2, file_xml);
    }
    public static void testing(String path, String file1, String file2, String fileXml) throws Exception {
        MyReader file_1 = new MyReader(path + file1);
        MyReader file_2 = new MyReader(path + file2);
        TestingCases.run(file_1, file_2, path + fileXml+ ".xml");
    }
    public static void realTool(){
        Scanner input = new Scanner(System.in);

        try {
            String fileName1 = input.nextLine(); //taking user input for file name
            MyReader file1 = new MyReader(fileName1); //Takes in the file name and sets it as string name to use for listConverter().
            ArrayList<String> fileList1 = file1.listConverter(); // converts the file to array of strings
            if(fileList1==null) // if the list is null, altho wouldnt this be handled in MyReader class?
                throw new FileNotFoundException();
            String fileName2 = input.nextLine(); //taking user input for file name
            MyReader file2 = new MyReader(fileName2);
            ArrayList<String> fileList2 = file2.listConverter();
            if(fileList2==null)
                throw new FileNotFoundException();
            //comparison
            LineComparator comparator = new LineComparator(fileList1, fileList2);
            comparator.compare();
            ArrayList<int []> compResults = comparator.getMatched();

            //write to file
            Output.writeToFile("src/resources/results.xml",compResults);
        }
        catch (FileNotFoundException e) {
            System.err.println("File cannot be found");
        }

        input.close();
    }
}