import java.util.Scanner;
import java.io.File;

public class BonusMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //get repository path
        System.out.println("Enter full path to the Git repositroy: ");
        String repoPath = input.nextLine().trim();

        //get file to analyze for bugs
        System.out.println("Enter the file name: ");
        String fileName = input.nextLine().trim();

        try{
            BonusBugFinder.analyze(repoPath, fileName);
        }
        catch (Exception e){
            System.err.println("Error ");
        }
        input.close();
    }
}
