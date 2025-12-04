import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) throws Exception {
        //realTool();
        // below is testing
        String file_1 = "testingASM.txt";
        String file_2 = "testingASM2.txt";
        String file_xml = "testingASM.xml";
        String path = "testFilesWilson/asmTest/";

        testing(path, file_1, file_2, file_xml);
    }
    public static void testing(String path, String file1, String file2, String fileXml) throws Exception {
        MyReader file_1 = new MyReader("src/resources/" + path + file1);
        MyReader file_2 = new MyReader("src/resources/" + path + file2);
        TestingCases.run(file_1, file_2, "src/resources/" + path + fileXml);
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