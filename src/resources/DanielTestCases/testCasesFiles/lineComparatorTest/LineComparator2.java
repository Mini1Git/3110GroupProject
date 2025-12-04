import java.util.ArrayList;
import java.util.HashSet;

public class LineComparator {
    double maxDiff = 0.15;

    private ArrayList<String> file1;
    private ArrayList<String> file2;
    private ArrayList<int[]> matched;
    private int file1_size;
    private int file2_size;
    HashSet<Integer> matchedLines1;
    HashSet<Integer> matchedLines2;

    public LineComparator(ArrayList<String> f1, ArrayList<String> f2) {
        file1 = f1;
        file2 = f2;
        file1_size = f1.size();
        file2_size = f2.size();
        matched = new ArrayList<int[]>();
        matchedLines1 = new HashSet<>();
        matchedLines2 = new HashSet<>();
    }

    public void unix_diff() {

        for (int i = 1; i < this.file1_size; i++) {
            if (file1.get(i).trim().isEmpty())
                continue; //skiping the empty strings
            for (int j = 1; j < this.file2_size; j++) {
                if (file2.get(j).trim().isEmpty() || matchedLines2.contains(j))

                if (file1.get(i).equals(file2.get(j))) {
                    matched.add(new int[]{i, j});
                    matchedLines1.add(i);
                    matchedLines2.add(j);
                    break;
                }

            }
        }
    }
}

