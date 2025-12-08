import java.util.ArrayList;
//import apache text library's levenshtein function as we will use it for similarity diff
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.Arrays;
import java.util.HashSet;
//Line Comparator class

public class LineComparator {

    //this is the maxDiff value, if the similarity of two strings is below maxDiff, it will be matched
    //this value is adjustable increase or decrease max difference acceptability
    double maxDiff = 0.325;

    private ArrayList<String> file1;
    private ArrayList<String> file2;
    private ArrayList<int[]> matched;
    private int file1_size;
    private int file2_size;
    HashSet<Integer> matchedLines1;
    HashSet<Integer> matchedLines2;

    //accepts two arraylist. Each arrayList represents the lines form each file to compare
    public LineComparator(ArrayList<String> f1, ArrayList<String> f2) {
        file1 = f1;
        file2 = f2;
        file1_size = f1.size();
        file2_size = f2.size();
        matched = new ArrayList<int[]>();
        matchedLines1 = new HashSet<>();
        matchedLines2 = new HashSet<>();
    }

    //returns matched lines
    public ArrayList<int[]> getMatched() {
        return matched;
    }

    //returns unmatched lines
    public ArrayList<Integer>[] getUnmatched() {
        //to get unmatched lines, we simply loop through both files and removed matched lines

        ArrayList<Integer>[] unmatched = new ArrayList[2]; // array of 2 ArrayLists
        unmatched[0] = new ArrayList<>(); //[0] will be unmatched from file1
        unmatched[1] = new ArrayList<>(); //[1] will be unmatched from file2

        //loop thru file 1, adding only unmatched lines to array (skipping empty lines)
        for(int i = 1; i < this.file1_size; i++){
            if (file1.get(i).trim().isEmpty())
                continue; //skiping the empty strings

            if(!matchedLines1.contains(i)){
                unmatched[0].add(i);
            }
        }

        //loop thru file 2, adding only unmatched lines to array (skipping empty lines)
        for(int i = 1; i < this.file2_size; i++){
            if (file2.get(i).trim().isEmpty())
                continue; //skiping the empty strings

            if(!matchedLines2.contains(i)){
                unmatched[1].add(i);
            }
        }

        return unmatched;
    }

    //compare function
    public void compare() {
        unix_diff();    //matches identical lines
        similarityDiff(); //matches similar lines
        lineSplit();   //matches line splits
    }

    //compares, stores and returns results in an arraylist of pairs of integers (stored as int[]) gets matched and unmatched
    public void unix_diff() {

        //a nested for loop to compare each line from each file to check for matching string contents
        for (int i = 1; i < this.file1_size; i++) {
            if (file1.get(i).trim().isEmpty())
                continue; //skiping the empty strings
            for (int j = 1; j < this.file2_size; j++) {
                if (file2.get(j).trim().isEmpty() || matchedLines2.contains(j))
                    continue; //skiping the empty strings or lines that are already matched

                if (file1.get(i).equals(file2.get(j))) {
                    //if the string content from file 1 and 2 match, add the line numbers to the results
                    matched.add(new int[]{i, j});
                    matchedLines1.add(i);
                    matchedLines2.add(j);
                    break;
                }

            }
        }
    }

    //compares unmatched lines from unix diff and uses Cosine Similarity and Levenstein Distance to get similarity score
    // lines with high similarity scores are matched and put into result array
    public void similarityDiff() {
        //a nested for loop to compare each line from the unmatched arrays, similar to unix diff
        //but this time, we won't compare for identical lines, but use levenshtein to compare similarity
        //we add the best similarity score match (thats also under the threshold) to matched array
        for(int i = 1; i < file1_size; i++) {
            //skip empty lines or lines that are matched
            if (file1.get(i).trim().isEmpty() || matchedLines1.contains(i))
                continue; //skiping the empty strings

            //keeps track of the best match and best similarity score
            int bestMatchLine = -1;
            double bestMatchScore = 10.0;

            for (int j = 1; j < file2_size; j++) {
                //this if statement prevents dupes by skipping lines that are already matched and skips empty lines
                if(matchedLines2.contains(j) || file2.get(j).trim().isEmpty()){
                    continue;
                }

                //contentDiff stores the normalized levenshtein score of the current line to check
                double contentDiff = normalizedLD(file1.get(i), file2.get(j));

                //contextDiff stores levenshtein distance for the surrounding lines
                double contextDiff = 0;

                //here, we're going to calc the context score by doing leven distance for the surrounding lines
                int prevLine1 = i - 1;
                int prevLine2 = j - 1;
                int nextLine1 = i + 1;
                int nextLine2 = j + 1;
                //if the line we're trying to get context of, is either the last line
                //we just set next line to be 0, which is an empty string
                if(i >= file1_size - 1){
                    nextLine1 = 0;
                }
                if(j >= file2_size - 1){
                    nextLine2 = 0;
                }

                //calcs the LD of previous line and next line, adds it and divdes by 2 to get the average
                //this stores the context score
                contextDiff = (normalizedLD(file1.get(prevLine1), file2.get(prevLine2)) + normalizedLD(file1.get(nextLine1), file2.get(nextLine2))) / 2;

                //recommended finalDiff ratio of contextDiff/contentDiff in the project instruction
                double finalDiff = (contentDiff * 0.6) + (contextDiff * 0.4);

                //checks if the final score is less the maxDiff threshold and if its better then the current best match
                if(finalDiff <= maxDiff && finalDiff < bestMatchScore){
                    bestMatchScore = finalDiff;
                    bestMatchLine = j;
                }
            }

            //now we add the best match if it exists
            if(bestMatchLine >= 0){
                matched.add(new int[]{i, bestMatchLine});
                matchedLines1.add(i);
                matchedLines2.add(bestMatchLine);
            }

        }
    }

    //check for line split matches from the remaining unmatched lines
    public void lineSplit(){
        //loop thru the unmatched arrays again
        for(int i = 1; i < file1_size; i++) {
            //skip empty lines
            if (file1.get(i).trim().isEmpty() || matchedLines1.contains(i))
                continue;

            for (int j = 1; j < file2_size; j++) {
                //this if statement prevents dupes by skipping lines that are already matched and skips empty lines
                if(matchedLines2.contains(j) || file2.get(j).trim().isEmpty()){
                    continue;
                }

                //here, we're going to calc the context score of the line splits by doing leven distance for the surrounding lines
                int prevLine1 = i - 1;
                int prevLine2 = j - 1;
                int nextLine1 = i + 1;
                int nextLine2 = j + 1;
                //if the line we're trying to get context of, is either the last line
                //we just set next line to be 0, which is an empty string
                if(i >= file1_size - 1){
                    nextLine1 = 0;
                }
                if(j >= file2_size - 1){
                    nextLine2 = 0;
                }

                String currentLineString = "";
                double currentScore = 1.0;
                int lineSplitIndex = 0;

                //now we check for line splitting
                //if the line after the current line isnt unmatched, it means it's arleady matched by another index and thus isn't part of the line split
                while(!matchedLines2.contains(j+lineSplitIndex) && j + lineSplitIndex < file2_size){
                    //we concatinate the next line with our currentLine and then check score for the new concat string (using context + content diff)
                    currentLineString = currentLineString + file2.get(j+lineSplitIndex);
                    double contentDiff = normalizedLD(file1.get(i), currentLineString);
                    double contextDiff = (normalizedLD(file1.get(prevLine1), file2.get(prevLine2)) + normalizedLD(file1.get(nextLine1), file2.get(nextLine2))) / 2;
                    double nextScore = (contentDiff*0.6) + (contextDiff*0.4);
                    if(nextScore > currentScore){
                        //once concatinating the lines stop decreasing the ld, we stop checking for line split
                        break;
                    }
                    else{
                        currentScore = nextScore;
                        lineSplitIndex += 1;

                        //update the nextLine2 if there is one
                        if(nextLine2 < file2_size - 1){
                            nextLine2 += 1;
                        }
                        else{
                            nextLine2 = 0;
                        }
                    }
                }

                //now, if the score of the above loop is less then maxDiff, we add all lines to matched!
                if(currentScore < maxDiff) {
                    for (int x = 0; x < lineSplitIndex; x++) {
                        //skips empty lines
                        if(file2.get(j+x).trim().isEmpty()){
                            continue;
                        }

                        matched.add(new int[]{i, j+ x});
                        matchedLines1.add(i);
                        matchedLines2.add(j + x);
                    }
                }
            }
        }
    }

    //this is the normalized levenshtein distance function
    //takes two strings and calcs the Levenshrein distance using apache's text library
    //we also normalize this score by the added length of string1 and string2
    // we normalize because longer strings can result in higher scores, even if the string is more similar then shorter strings
    // (ex: abc and xyz have LD of 3, while teststring12345 and teststringabcde have LD of 5)
    public double normalizedLD(String text1, String text2){
        if (text1.isEmpty() && text2.isEmpty()) {
            return 0.0; //if both strings are empty, return 0
        }

        LevenshteinDistance diff = new LevenshteinDistance();
        return ((double)diff.apply(text1, text2) / (text1.length() + text2.length()));
    }


    //this function test prints out the current array content for debugging and visualization
    public void testPrints(){
        System.out.println("Files 1 and 2");
        System.out.println(file1);
        System.out.println(file2);

        System.out.println("Matched lines (line numbers):");
        System.out.println(matchedLines1);
        System.out.println(matchedLines2);
    }
}


