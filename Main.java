import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        String fileName1 = input.nextLine(); //taking user input for file name

        MyReader file1 = new MyReader(fileName1);
        ArrayList<String> fileList1 = file1.listConverter();

        
        String fileName2 = input.nextLine(); //taking user input for file name

        MyReader file2 = new MyReader(fileName2);

        ArrayList<String> fileList2 = file2.listConverter();
        
        //comparison
        ArrayList<int []> compResults = LineComparator.compare(fileList1, fileList2);

        //write to file
        Output.writeToFile(compResults);

        input.close();
    }
}