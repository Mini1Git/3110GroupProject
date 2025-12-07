import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        ArrayList<String> names = new ArrayList<>();



        String path = "src/resources/";


        String file1 = "ProfDataset/Professor/javaPerspectiveFactory/JavaPerspectiveFactory_1.java";
        String file2 = "ProfDataset/Professor/javaPerspectiveFactory/JavaPerspectiveFactory_2.java";
        String fileXML = "ProfDataset/Professor/javaPerspectiveFactory/JavaPerspectiveFactory";
        findFiles(path, fileOnes, fileTwos, fileXMLs, names); // helps with automation
        TestingCases.writeCSVData(fileOnes, fileTwos, fileXMLs, names, true);

         //System.out.println("Writing XML to: " + new File(path + file_xml + ".xml").getAbsolutePath());TestingCases.createXMLMappings(path+file_1, path+file_2, path+file_xml);
        //testing(path, file1, file2, fileXML ); //for testing specific datasets.

    }
    public static void testing(String path, String file1, String file2, String fileXml) throws Exception {
        MyReader file_1 = new MyReader(path + file1);
        MyReader file_2 = new MyReader(path + file2);
        TestingCases.run(file_1, file_2, path + fileXml+ ".xml");
    }

    //this function is so sad.
    public static void findFiles(String path, ArrayList<String> f1, ArrayList<String> f2, ArrayList<String> fX, ArrayList<String> names) throws Exception {
        File surfaceDir = new File(path);
        int totalMappings = 0;
        // JEEZ, basically, it goes like: Person -> theirTestCases -> typeTestcase -> files.
        // needed to get the individual files to automate TESTING.
        for (File dir : surfaceDir.listFiles()) { // This is the surface directory level, which gets each person's testcases surface FOLDER.
            if (dir.isFile()) {
                continue; // we dont care about that
            }

            //System.out.println(Arrays.toString(dir.listFiles()));
            for (File deepDir : dir.listFiles()) { //Within this, go into nameFolder, where all within that, all the tests are seperated by folder.
                //System.out.println("F:" + Arrays.toString(deepDir.listFiles()));

                if (deepDir.isFile()) {
                    continue; // dont care
                }
                for (File testDir : deepDir.listFiles()) { // Within this, go into individual testCases category. also get name here
                   // names.add(testDir.getParent());
                    names.add(testDir.getParentFile().getName());
                    //System.out.println(testDir.getName()+": "+Arrays.toString(testDir.listFiles()));
                    // calculate how many mappings total?


                    for (File testFile : testDir.listFiles()) { // get actual Testcase files.

                        if (testFile.isFile()) {
                            if (testFile.getName().endsWith(".xml")) {
                                fX.add(testFile.getAbsolutePath());
             //This line of code is to calculate how many mappings our team has. // if (!testDir.getParentFile().getName().equals("Professor")) {System.out.println("lines of" + testDir.getParentFile().getName());Path filePath = Paths.get(testFile.getPath());totalMappings += (int) Files.lines(filePath).count();}

                            }
                            else if (testFile.getName().contains("2")) {
                                f2.add(testFile.getAbsolutePath());
                            }
                            else if (testFile.getName().contains("1")) {
                                f1.add(testFile.getAbsolutePath());
                            }
                        }
                    }

                }
            }
        }
       // System.out.println("WE HAVE "+(totalMappings - 40) + " MAPPINGS!"); // -40 because of the <VERSION> (theres 2 in each xml file)
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