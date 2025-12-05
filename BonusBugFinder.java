import java.util.ArrayList;
import java.util.HashMap;
public class BonusBugFinder {
    /*
    - compare buggy (HEAD~1) vs. fixed (HEAD)
    - any line in buggy that is unmatched or modified/similarity matched was "fixed"
    - these are our target lines.
    - compare old (HEAD~2) with buggy (HEAD~1). check our target lines
    - if the target line matches something in old, the bug was already there.
    - if the target line does not match anything in old, then HEAD~1 introduced the bug
    */

    public static void analyze(String repoPath, String fileName){
        if (!BonusGitHelper.isFixCommit(repoPath)){
            System.out.println("Latest commit is not a bug fix. ");
            return;
        }
        System.out.println("Latest commit is a bug fix. Searching for bug introducing changes... ");

        ArrayList<String> fixedFile = BonusGitHelper.getFileContent(repoPath, fileName, "HEAD");
        ArrayList<String> buggyFile = BonusGitHelper.getFileContent(repoPath, fileName, "HEAD~1");
        ArrayList<String> oldFile   = BonusGitHelper.getFileContent(repoPath, fileName, "HEAD~2");

        //find what lines were changed between buggy and fixed
        LineComparator fixDiff = new LineComparator(buggyFile, fixedFile);
        fixDiff.compare();
        ArrayList<int[]> matches = fixDiff.getMatched();

        // store the lines that were "fixed"
        ArrayList<Integer> fixedIndices = new ArrayList<>();

        // helper map matchMap to look up matches easily: [BuggyIndex -> FixedIndex]
        HashMap<Integer, Integer> matchMap = new HashMap<>();
        for (int[] pair : matches) {
            matchMap.put(pair[0], pair[1]);
        }

        // iterate through every line in the buggy file
        for (int i = 1; i < buggyFile.size(); i++) {
            // skip empty lines 
            if (buggyFile.get(i).trim().isEmpty()) continue;

            if (!matchMap.containsKey(i)) {
                // CASE 1 DELETION
                // the line was deleted to fix the bug.
                fixedIndices.add(i);
            } 
            else {
                // CASE 2 MODIFICATION
                // line has a match: check if exact match or similarity (modified)
                int mappedIndex = matchMap.get(i);
                
                String buggyContent = buggyFile.get(i);
                String fixedContent = fixedFile.get(mappedIndex);

                // if contents are NOT equal, it was modified (similiarity match)
                // if contents are equal, it was a unix/exact match (not a bug fix)
                if (!buggyContent.equals(fixedContent)) {
                    fixedIndices.add(i);
                }
            }
        }
        // 'fixedIndices' contains the line numbers in 'buggyFile' that caused the bug.
        //  trace back old file, who introduced the bug

  }
}
