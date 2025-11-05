

public class MyReader {
    private String filename;//filename attribute
    // default constructor
    public MyReader() {

    }

    public MyReader(String filename) {
      this.filename = filename; // seting the filename
    }
    
    
  //   public static void main(String[] args) {
  //   File myObj = new File("filename.txt");

  //   // try-with-resources: Scanner will be closed automatically
  //   try (Scanner myReader = new Scanner(myObj)) {
  //     while (myReader.hasNextLine()) {
  //       String data = myReader.nextLine();
  //       System.out.println(data);
  //     }
  //   } catch (FileNotFoundException e) {
  //     System.out.println("An error occurred.");
  //     e.printStackTrace();
  //   }
  // }
}




