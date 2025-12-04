import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GitHelper {
    
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
            e.printStackTrace();
        }
        return output;
    }

    //check commit message for "bug fix" words
    public static boolean isFixCommit(String repoPath){
        //get most recent commit, only commit message no other info
        ArrayList<String> logs = runCommand("git log -1 --pretty=%B", repoPath);
        String fullMessage = String.join(" ", logs).toLowerCase();

        boolean result = false;
        result = fullMessage.matches(".*(fix|bug|repair).*");
        return result;
    }
}
