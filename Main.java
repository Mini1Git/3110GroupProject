import java.util.ArrayList;
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
        input.close();
    }
}