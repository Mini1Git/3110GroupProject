import java.util.ArrayList;
//import apache text library's levenshtein function as we will use it for similarity diff
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.HashSet;
//Line Comparator class

public class LineComparator {

    //this is the maxDiff value, if the similarity of two strings is below maxDiff, it will be matched
    //this value is adjustable increase or decrease max difference acceptability
    double maxDiff = 0.15;

    private ArrayList<String> file1;
    private ArrayList<String> file2;
    private ArrayList<int[]> matched;
    private ArrayList<Integer> unmatched1 = new ArrayList<Integer>();
    private ArrayList<Integer> unmatched2 = new ArrayList<Integer>();
    private int file1_size;
    private int file2_size;
    HashSet<Integer> matchedLines1 = new HashSet<>();
    HashSet<Integer> matchedLines2 = new HashSet<>();

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
    }

    public ArrayList<int[]> getMatched() {
        return matched;
    }

    //compare function
    public void compare() {
        unix_diff();    //matches identical lines
        similarityDiff(); //matches similar lines
        lineSplit();   //matches line splits (not implemented yet)
    }

    //compares, stores and returns results in an arraylist of pairs of integers (stored as int[]) gets matched and unmatched
    public void unix_diff() {

        //a nested for loop to comapre each line from each file to check for matching string contents
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
        //a nested for loop to compare each line from the unmatched arrays, similar to unix diff
        //but this time, we won't compare for identical lines, but use levenshtein to compare similarity
        //we add the best similarity score match (thats also under the threshold) to matched array
        for(int i = 0; i < unmatched1.size(); i++) {
            //skip empty lines
            if (file1.get(unmatched1.get(i)).trim().isEmpty())
                continue; //skiping the empty strings

            //keeps track of the best match and best similarity score
            int bestMatchLine = -1;
            double bestMatchScore = 10.0;

            for (int j = 0; j < unmatched2.size(); j++) {
                //this if statement prevents dupes by skipping lines that are already matched and skips empty lines
                if(matchedLines2.contains(j) || file2.get(unmatched2.get(j)).trim().isEmpty()){
                    continue;
                }

                //contentDiff stores the normalized levenshtein score of the current line to check
                double contentDiff = normalizedLD(file1.get(unmatched1.get(i)), file2.get(unmatched2.get(j)));

                //contextDiff stores levenshtein distance for the surrounding lines
                double contextDiff = 0;

                //here, we're going to calc the context score by doing leven distance for the surrounding lines
                int prevLine1 = unmatched1.get(i) - 1;
                int prevLine2 = unmatched2.get(j) - 1;
                int nextLine1 = unmatched1.get(i) + 1;
                int nextLine2 = unmatched2.get(j) + 1;
                //if the line we're trying to get context of, is either the first or last line
                //we just set prev/ next line to the current line num
                if(unmatched1.get(i) <= 1){
                    prevLine1 = unmatched1.get(i);
                }
                if(unmatched2.get(j) <= 1){
                    prevLine2 = unmatched2.get(j);
                }
                if(unmatched1.get(i) >= file1_size - 1){
                    nextLine1 = unmatched1.get(i);
                }
                if(unmatched2.get(j) >= file2_size - 1){
                    nextLine2 = unmatched2.get(j);
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
                matched.add(new int[]{unmatched1.get(i), unmatched2.get(bestMatchLine)});
                matchedLines1.add(unmatched1.get(i));
                matchedLines2.add(unmatched2.get(bestMatchLine));
            }

        }
    }

    //check for line split matches from the remaining unmatched lines
    public void lineSplit(){
        //in the similarityDiff function, we didn't remove from the unmatched2 array so we remove them here
        //(note, i dont remove from unmatched1 as file1 lines can match to many of file2 lines)
        for(int i = unmatched2.size() - 1; i >= 0; i--) {
            if(matchedLines2.contains(unmatched2.get(i))){
                unmatched2.remove(i);
            }
        }

        //loop thru the unmatched arrays again
        for(int i = 0; i < unmatched1.size(); i++) {
            //skip empty lines
            if (file1.get(unmatched1.get(i)).trim().isEmpty())
                continue;

            for (int j = 0; j < unmatched2.size(); j++) {
                //this if statement prevents dupes by skipping lines that are already matched and skips empty lines
                if(matchedLines2.contains(j) || file2.get(unmatched2.get(j)).trim().isEmpty()){
                    continue;
                }

                //we get our starting scores and data
                double currentScore = normalizedLD(file1.get(unmatched1.get(i)), file2.get(unmatched2.get(j)));
                String currentLineString = file2.get(unmatched2.get(j));
                int lineSplitIndex = 1;

                //now we check for line splitting
                //if the line after the current line isnt unmatched, it means it's arleady matched by another index and thus isn't part of the line split
                while(unmatched2.contains(unmatched2.get(j)+lineSplitIndex)){
                    //we concatinate the next line with our currentLine and then check LD for the new concat string
                    currentLineString = currentLineString + file2.get(unmatched2.get(j)+lineSplitIndex);
                    double nextScore = normalizedLD(file1.get(unmatched1.get(i)), currentLineString);
                    if(nextScore > currentScore){
                        //once concatinating the lines stop decreasing the ld, we stop checking for line split
                        break;
                    }
                    else{
                        currentScore = nextScore;
                        lineSplitIndex += 1;
                    }
                }

                //now, if the score of the above loop is less then maxDiff, we add all lines to matched!
                if(currentScore <= maxDiff) {
                    for (int x = 0; x < lineSplitIndex; x++) {
                        matched.add(new int[]{unmatched1.get(i), unmatched2.get(j)+ x});
                        matchedLines1.add(unmatched1.get(i));
                        matchedLines2.add(unmatched2.get(j) + x);
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
        LevenshteinDistance diff = new LevenshteinDistance();
        return ((double)diff.apply(text1, text2) / (text1.length() + text2.length()));
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

