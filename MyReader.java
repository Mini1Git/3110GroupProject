import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;             // Import the Scanner class to read text files
import java.util.HashMap;
import java.util.Scanner;


public class MyReader {
    private String filename;//filename attribute
    // default constructor
    public MyReader() {

    }

    public MyReader(String filename) {
      this.filename = filename; // seting the filename
    }

    public void setFileName(String fileName) { //setter
      this.filename = fileName;


    }
   //converts the file to arraylist
    public ArrayList<String> listConverter(){
      ArrayList<String> fileList = new ArrayList<String>();
      File fileObj = new File(filename); //using the file name in the object

      // try-with-resources: Scanner will be closed automatically
      try (Scanner myReader = new Scanner(fileObj)) {
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          if(data.trim().isEmpty()) //if it's just an empty line then skip
            continue;

          fileList.add(data);
        }
      } 
      catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
         // e.printStackTrace();
        
      }

        return fileList;
    }
   //converts the file to hashmap
     public HashMap<Integer, String> fileHasher(){
      HashMap<Integer, String> fileList = new HashMap<Integer, String>();
      File fileObj = new File(filename); //using the file name in the object

      // try-with-resources: Scanner will be closed automatically
      try (Scanner myReader = new Scanner(fileObj)) {
        int i = 0;
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          if(data.trim().isEmpty()) //if it's just an empty line then skip
            continue;

          fileList.put(i, data);
          i++;
        }
      } 
      catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        // e.printStackTrace();
      }

        return fileList;
    }
    
    

}




