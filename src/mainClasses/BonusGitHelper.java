package mainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BonusGitHelper {
    
    //from A3
    /* method to run terminal commands easily
     input: command and repository path
     returns: List containing output as strings (that would typically be in terminal)*/
    public static ArrayList<String> runCommand(String command, String repoPath) {
        ArrayList<String> output = new ArrayList<>();
        try {
            // split command into parts (e.g., "git", "log"), prepares the command
            ProcessBuilder builder = new ProcessBuilder(command.split(" ")); 
            builder.directory(new File(repoPath)); // set the folder to the repository
            Process process = builder.start();
            //\runs the command on your computer and gives you a handle (process) to control it.
            
            // read the output from the terminal, store into output List
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }
        } catch (Exception e) {
           
        }
        return output;
    }

    //check commit message for "bug fix" words
    public static boolean isFixCommit(String repoPath){
        //get most recent commit, only commit message no other info
        ArrayList<String> logs = runCommand("git log -1 --pretty=%B", repoPath);
        String fullMessage = String.join(" ", logs).toLowerCase();
        
        //System.out.println("Latest commit is: " + fullMessage);
        boolean result = false;
        result = fullMessage.matches(".*(fix|bug|repair).*");
        return result;
    }

    //runs "git show", print out text of the file from the past
    //return text of file in ArrayList<String>
    public static ArrayList<String> getFileContent(String repoPath, String filename, String revision) {
        // Example command: git show HEAD~1:src/MyFile.java
        // "revision" will be "HEAD" for current, "HEAD~1" for one version back, or "HEAD~2" for two versions back
        String command = "git show " + revision + ":" + filename;
        return runCommand(command, repoPath);
    }

}
