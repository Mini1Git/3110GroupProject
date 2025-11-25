

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestingCases {
    MyReader file1 = new MyReader("ASTResolving_1.java");
    MyReader file2 = new MyReader("ASTResolving_2.java");
    @Test
    public void testing(){


        LineComparator comparator = new LineComparator(file1.listConverter(), file2.listConverter());
        comparator.compare();
        ArrayList<int []> results = comparator.getMatched();

        for (int[] pair : results){
            //store the pair of ints
            //we should prob skip absolute matches for test cases.

            int x = pair[0];
            int y = pair[1];
            //basically, input the range of X, x being first file line range.
            if (x >=114 && x <= 134){
                System.out.println("First File: "+ x + " Second File: " + y);
                getLine(x, y);}


        }
       //getLine(134, 137);
    }
//for getLine, input the x, being the line number of the first file, and the line number of the second.
    public void getLine(int x, int y){

        System.out.println(file1.listConverter().get(x).trim() + " |and| " + file2.listConverter().get(y).trim());
    }

//need to program a function using assert test.
}
