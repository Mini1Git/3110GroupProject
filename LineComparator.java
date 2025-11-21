import java.util.ArrayList;
//Line Comparator class

public class LineComparator {

    private ArrayList<String> file1;
    private ArrayList<String> file2;
    private ArrayList<int[]> matched;
    private ArrayList<String> unmatched1;
    private ArrayList<String> unmatched2;
    private int file1_size;
    private int file2_size;

    //accepts two arraylist. Each arrayList represents the lines form each file to compare
    public LineComparator(ArrayList<String> f1, ArrayList<String> f2){
        file1 = f1;
        file2 = f2;
        file1_size = f1.size();
        file2_size = f2.size();
        matched = new ArrayList<int[]>();
        unmatched1 = new ArrayList(f1);
        unmatched2 = new ArrayList(f2);
    }

    public ArrayList<int[]> getMatched(){
        return matched;
    }

    //compare function
    public void compare(){
        unix_diff();
        //similarity() //hasn't been implemented yet

    }
    //compares, stores and returns results in an arraylist of pairs of integers (stored as int[]) gets matched and unmatched
    public void unix_diff(){

        //a nested for loop to comapre each line from each file to check for matching string contents
        for(int i = 1; i < this.file1_size; i++){
            if(file1.get(i).trim().isEmpty())
                continue; //skiping the empty strings
            for(int j = 1; j < this.file2_size; j++){

                if(file2.get(j).trim().isEmpty())
                    continue; //skiping the empty strings
                if(file1.get(i).equals(file2.get(j))){
                    //if the string content from file 1 and 2 match, add the line numbers to the results
                    matched.add(new int[]{i, j});
                    unmatched1.set(i, ""); //if a match has been found removes it from unmatched
                    unmatched2.set(j, ""); 
                    break;
                }

            }
        }
    }

}

