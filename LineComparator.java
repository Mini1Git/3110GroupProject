import java.util.ArrayList;
//Line Comparator class

public class LineComparator {

    //compare function
    //accepts two arraylist. Each arrayList represents the lines form each file to compare
    //compares, stores and returns results in an arraylist of pairs of integers (stored as int[])
    public static ArrayList<int[]> compare(ArrayList<String> file1, ArrayList<String> file2){
        //creates the result arraylist
        ArrayList<int[]> results = new ArrayList<int[]>();

        //a nested for loop to comapre each line from each file to check for matching string contents
        for(int i = 0; i < file1.size(); i++){
            if(file1.get(i).trim().isEmpty())
                continue; //skiping the empty strings
            for(int j = 0; j < file2.size(); j++){
                if(file1.get(j).trim().isEmpty())
                    continue; //skiping the empty strings
                if(file1.get(i).equals(file2.get(j))){
                    //if the string content from file 1 and 2 match, add the line numbers to the results
                    results.add(new int[]{i, j});
                }

            }
        }

        //return the matches
        return results;
    }

}

