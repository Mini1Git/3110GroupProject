import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Output {
    //loops through arrayList filled w arrays of two ints (pair of ints)
    //prints results on filename file

    public static void writeToFile(String filename, ArrayList<int[]> pairs){

        //put the line pairings into a treeMap that sorts by original lines ascending order
        // key = orig value = new
        //and line splits can be stored more clearly
        Map<Integer, List<Integer>> pairTree = new TreeMap<>();
        for (int[] pair: pairs){
            int orig = pair[0];
            int neww = pair[1];
            //add to pairTree, orig line nums as the key, value an ArrayList storing all NEW line nums
            pairTree.computeIfAbsent(orig, k -> new ArrayList<>()).add(neww);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){

            writer.write("<VERSION NUMBER = \"" + 1 + "\" >");
            writer.newLine();

            //loop through each element in arrayList
            for (Map.Entry<Integer, List<Integer>> entry: pairTree.entrySet()){
                int x = entry.getKey();
                List<Integer> newLines = entry.getValue();

                //order NEW lines
                Collections.sort(newLines);

                //single line match
                if (newLines.size() == 1){
                    writer.write("    <LOCATION ORIG= \"" + x + "\" NEW= \"" + newLines.get(0) + "\" />");
                    writer.newLine();
                }
                //no matches ( deleted)
                else if (newLines.isEmpty()) {
                    writer.write("    <LOCATION ORIG= \"" + x + "\" NEW=\"-1\" />");
                    writer.newLine();
                }
                //line split match
                //first match printed normally
                else{
                    writer.write("        <LOCATION ORIG=\"" + x + "\" NEW=\"" + newLines.get(0) + "\">");
                    writer.newLine();

                    //other line split matches printed in ALT tags
                    for (int i = 1; i < newLines.size(); i++) {
                        writer.write("        <ALT NEW=\"" + newLines.get(i) + "\"/>");
                        writer.newLine();
                    }

                    //closing tag
                    writer.write("</LOCATION>");
                    writer.newLine();
                }
            }


            writer.write("</VERSION>");
            System.out.print("Results written successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }

    }
}
