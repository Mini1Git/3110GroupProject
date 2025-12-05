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

        ArrayList<String> currentFixed = BonusGitHelper.getFileContent(repoPath, fileName, "HEAD");
        ArrayList<String> currentBuggy = BonusGitHelper.getFileContent(repoPath, fileName, "HEAD~1");

        //find what lines were changed between buggy and fixed
        LineComparator diff = new LineComparator(currentBuggy, currentFixed);
        diff.compare();
        ArrayList<int[]> matches = diff.getMatched();

        // store the lines that were "fixed"
        ArrayList<Integer> fixedIndices = new ArrayList<>();

        // helper map matchMap to look up matches easily: [BuggyIndex -> FixedIndex]
        HashMap<Integer, Integer> matchMap = new HashMap<>();
        for (int[] pair : matches) {
            matchMap.put(pair[0], pair[1]);
        }

        // iterate through every line in the buggy file
        for (int i = 1; i < currentBuggy.size(); i++) {
            // skip empty lines 
            if (currentBuggy.get(i).trim().isEmpty()) continue;

            if (!matchMap.containsKey(i)) {
                // CASE 1 DELETION
                // the line was deleted to fix the bug.
                fixedIndices.add(i);
            } 
            else {
                // CASE 2 MODIFICATION
                // line has a match: check if exact match or similarity (modified)
                int mappedIndex = matchMap.get(i);
                
                String buggyContent = currentBuggy.get(i);
                String fixedContent = currentFixed.get(mappedIndex);

                // if contents are NOT equal, it was modified (similiarity match)
                // if contents are equal, it was a unix/exact match (not a bug fix)
                if (!buggyContent.equals(fixedContent)) {
                    fixedIndices.add(i);
                }
            }
        }
        // 'fixedIndices' contains the line numbers in 'buggyFile' that caused the bug.
        if (fixedIndices.isEmpty()){
            //no changes found
            System.out.println("No changes found");
            return;
        }
        // --- NEW DEBUG PRINT: Show what we are tracking initially ---
        System.out.println("Tracking the following " + fixedIndices.size() + " buggy lines from HEAD~1:");
        for (int index : fixedIndices) {
            System.out.println("   [Line " + index + "] " + currentBuggy.get(index).trim());
        }
        System.out.println("--------------------------------------------------");
        //now trace back to find who introduced the buggy lines
        int currentRev = 1; //starting at HEAD~1 i.e. one version back

        //keep going until buggy lines are all tracked
        while (!fixedIndices.isEmpty()){
            int prevRev = currentRev + 1;
            ArrayList<String> olderFile = BonusGitHelper.getFileContent(repoPath, fileName, "HEAD~" + prevRev);
            // if older file does not exist, we are at the start file
            // so the current version is the bug introducer
            if (olderFile.isEmpty()){
                System.out.println("Bug was introduced in HEAD~" + currentRev + ", at lines " + fixedIndices);
                break;
            }

            //compare olderFile with currentBuggy, check if fixedIndices exist in olderFile
            LineComparator historyDiff = new LineComparator(olderFile, currentBuggy);
            historyDiff.compare();
            ArrayList<int[]> historyMatches = historyDiff.getMatched();
            //array list of 2 element integer arrays
            ArrayList<Integer> inheritedIndices = new ArrayList<>(); //lines that were inherited
            ArrayList<Integer> introducedLines = new ArrayList<>(fixedIndices); //line introduced now

            //check if the buggy lines map to older file
            //pair[0] is older file line index, pair[1] is current buggy file line index
            for (int[] pair : historyMatches){
                if (fixedIndices.contains(pair[1])){
                    //bug is in older file, map it to the index in the older file, it was inherited
                    inheritedIndices.add(pair[0]);
                    //remove line from introduced since it was not introduced
                    introducedLines.remove(Integer.valueOf(pair[1]));
                }
            }

            if (!introducedLines.isEmpty()){
                //bug not in older file, introduced in current revision
                System.out.println("Bug was introduced in HEAD~" + currentRev + ", at lines " + introducedLines);
            
                // --- NEW DEBUG PRINT: Show content of found bugs ---
                for (int index : inheritedIndices) {
                    System.out.println("   > Content: \"" + currentBuggy.get(index).trim() + "\"");
                }
                System.out.println(); // Empty line for formatting
            
            }

            //track bugs inherited, continue looping backwards previous versions
            fixedIndices = inheritedIndices;
            currentBuggy = olderFile;
            currentRev ++;

            //to prevent infinite/long loop, can comment out if needed
            if (currentRev > 50){
                System.out.println("Went back 50 commits. stopping. ");
                break;
            }
        }

  }
}
