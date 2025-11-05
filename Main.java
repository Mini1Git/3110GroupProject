import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        String fileName1 = input.nextLine(); //taking user input for file name

        MyReader file1 = new MyReader(fileName1);
        ArrayList<String> fileList1 = file1.listConverter();
        // System.out.println(fileList1);

        int listSize = fileList1.size(); //size of arraylist
        for(int i = 0; i<listSize; i++)
        {
            System.out.println(fileList1.get(i));
        }

        
        String fileName2 = input.nextLine(); //taking user input for file name

        MyReader file2 = new MyReader(fileName2);

        HashMap<Integer, String> fileHash = file2.fileHasher();
        
        // Print values
        // for (String i : fileHash.values()) {
        // System.out.println(i);
        // }

        // Print keys and values
        System.out.println(fileHash);
        
        for (Integer i : fileHash.keySet()) {
        System.out.println("key: "+ i + " value: " + fileHash.get(i));
        }

        input.close();
    }
}