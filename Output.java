import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Output {
    //loops through arrayList filled w arrays of two ints (pair of ints)
    //prints results on output.txt file

    public static void writeToFile(ArrayList<int[]> pairs){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){

            //loop through each element in arrayList
            for (int[] pair : pairs){
                //store the pair of ints
                int x = pair[0];
                int y = pair[1];

                writer.write("<LOCATION ORIG = \"" + x + "\" NEW = \"" + y + "\" />");
                writer.newLine();
            }
            System.out.print("Results written successfully");
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }

    }
}
