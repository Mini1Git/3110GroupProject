import java.util.Scanner;
import java.io.File;

public class BonusMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //get repository path
        System.out.println("Enter full path to the Git repositroy: ");
        String rawRepoPath = input.nextLine().trim();
        //remove quatation marks since windows automatically puts them in path names
        String repoPath = rawRepoPath.replace("\"", "");

        //check exists
        File repoDir = new File(repoPath);
        if (!repoDir.exists() || !repoDir.isDirectory()){
            System.out.println("Repository path doesnt exist. exiting");
            return;
        }

        //get file to analyze for bugs
        System.out.println("Enter the file name/ relative path: ");
        String rawFileName = input.nextLine().trim();
        //remove quotes
        String fileName = rawFileName.replace("\"", "");

        //check exists
        File targetFile = new File(repoDir, fileName);
        if (!targetFile.exists()){
            System.out.println("File not found. exiting ");
            return;
        }

        try{
            BonusBugFinder.analyze(repoPath, fileName);
        }
        catch (Exception e){
            System.err.println("Error ");
        }
        input.close();
    }
}
