import java.util.ArrayList;
import java.util.HashSet;

public class LineComparator {
    private ArrayList<String> file1;
    private ArrayList<String> file2;
    private ArrayList<int[]> matched;
    private ArrayList<Integer> unmatched1 = new ArrayList<Integer>();
    private ArrayList<Integer> unmatched2 = new ArrayList<Integer>();
    private int file1_size;
    private int file2_size;
    HashSet<Integer> matchedLines1 = new HashSet<>();
    HashSet<Integer> matchedLines2 = new HashSet<>();

    public LineComparator(ArrayList<String> f1, ArrayList<String> f2) {
        file1 = f1;
        file2 = f2;
        file1_size = f1.size();
        file2_size = f2.size();
        matched = new ArrayList<int[]>();

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

    public void unix_diff() {
        for (int i = 1; i < this.file1_size; i++) {
            if (file1.get(i).trim().isEmpty())
                continue;
            for (int j = 1; j < this.file2_size; j++) {

                if (file2.get(j).trim().isEmpty() || matchedLines2.contains(j))
                    continue;
                if (file1.get(i).equals(file2.get(j))) {
                    matched.add(new int[]{i, j});
                    matchedLines1.add(i);
                    matchedLines2.add(j);
                    unmatched1.remove((Integer) i);
                    unmatched2.remove((Integer) j);
                    break;
                }

            }
        }
    }
}

