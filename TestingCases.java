
//so testing works, but vscode has amnesia and doesn't remember that org.junit is in the lib lol.


import org.junit.Test;

public class TestingCases {
    @Test
    public void testing(){
        MyReader file1 = new MyReader("filename.txt");
        MyReader file2 = new MyReader("filename2.txt");
        
    }

}
