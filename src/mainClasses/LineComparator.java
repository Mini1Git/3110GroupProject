import java.util.ArrayList;
//import apache text library's levenshtein function as we will use it for similarity diff
import org.apache.commons.text.similarity.LevenshteinDistance;
//Line Comparator class

public class LineComparator {

    private ArrayList<String> file1;
    private ArrayList<String> file2;
    private ArrayList<int[]> matched;
    private ArrayList<Integer> unmatched1 = new ArrayList<Integer>();
    private ArrayList<Integer> unmatched2 = new ArrayList<Integer>();
    private int file1_size;
    private int file2_size;

    //accepts two arraylist. Each arrayList represents the lines form each file to compare
    public LineComparator(ArrayList<String> f1, ArrayList<String> f2) {
        file1 = f1;
        file2 = f2;
        file1_size = f1.size();
        file2_size = f2.size();
        matched = new ArrayList<int[]>();

        //fill the unmatched arrays with all line numbers (that aren't empty)
        //matched line numbers will be removed later, leaving only the unmatched
        for (int i = 1; i < file1.size(); i++) {
            if(!file1.get(i).isEmpty()){
                unmatched1.add(i);
            }
        }
        for (int i = 1; i < file2.size(); i++) {
            if(!file2.get(i).isEmpty()){
                unmatched2.add(i);
            }
        }
        //testPrints();
    }

    public ArrayList<int[]> getMatched() {
        return matched;
    }

    //compare function
    public void compare() {
        unix_diff();    //matches identical lines
        similarityDiff(); //matches similar lines
        //lineSplit()   //matches line splits (not implemented yet)

    }

    //compares, stores and returns results in an arraylist of pairs of integers (stored as int[]) gets matched and unmatched
    public void unix_diff() {

        //a nested for loop to comapre each line from each file to check for matching string contents
        for (int i = 1; i < this.file1_size; i++) {
            if (file1.get(i).trim().isEmpty())
                continue; //skiping the empty strings
            for (int j = 1; j < this.file2_size; j++) {

                if (file2.get(j).trim().isEmpty())
                    continue; //skiping the empty strings
                if (file1.get(i).equals(file2.get(j))) {
                    //if the string content from file 1 and 2 match, add the line numbers to the results
                    matched.add(new int[]{i, j});
                    unmatched1.remove((Integer) i); //if a match has been found removes it from unmatched
                    unmatched2.remove((Integer) j);
                    break;
                }

            }
        }
    }

    //compares unmatched lines from unix diff and uses Cosine Similarity and Levenstein Distance to get similarity score
    // lines with high similarity scores are matched and put into result array
    public void similarityDiff() {
        testPrints();

        //this is the maxDiff value, if the similarity of two strings is below maxDiff, it will be matched
        //this value is adjustable increase or decrease max difference acceptability
        int maxDiff = 5;

        //a nested for loop to compare each line from the unmatched arrays, similar to unix diff
        //but this time, we won't compare for identical lines, but use levenshtein to compare similarity
        //if the levenshtein distance of two lines is less than maxDiff (adjustable), then we add it to matched array
        LevenshteinDistance diff = new LevenshteinDistance();
        for (int i = 0; i < unmatched1.size(); i++) {
            for (int j = 0; j < unmatched2.size(); j++) {
                //use levenshtein function here, and check if its less then maxDiff
                if(diff.apply(file1.get(unmatched1.get(i)), file2.get(unmatched2.get(j))) <= maxDiff){
                    matched.add(new int[]{unmatched1.get(i), unmatched1.get(j)});
                }
            }
        }
    }

    //this function test prints out the current array content for debugging and visualization
    public void testPrints(){
        System.out.println("Files 1 and 2");
        System.out.println(file1);
        System.out.println(file2);

        System.out.println("Unmatched arrays (line numbers):");
        System.out.println(unmatched1);
        System.out.println(unmatched2);
    }
}

