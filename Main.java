import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);

        try {
            String fileName1 = input.nextLine(); //taking user input for file name
            MyReader file1 = new MyReader(fileName1);
            ArrayList<String> fileList1 = file1.listConverter();
            if(fileList1==null)
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
            Output.writeToFile(compResults);
        }
        catch (FileNotFoundException e) {
            System.err.println("File cannot be found");
        }

        input.close();
    }
}