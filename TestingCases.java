
//so testing works, but vscode has amnesia and doesn't remember that org.junit is in the lib lol.
//using junit 4
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

public class TestingCases {
    @Test
    public void testing(){
        MyReader file1 = new MyReader("filename.txt");
        MyReader file2 = new MyReader("filename2.txt");

        LineComparator comparator = new LineComparator(file1.listConverter(), file2.listConverter());
        comparator.compare();
        ArrayList<int []> results = comparator.getMatched();
        //ArrayList<int[]> results = LineComparator.compare(file1.listConverter(), file2.listConverter());
        //The result should be an array of all matches? like, ex:{{1,2}, {4.7}....}
        //actual result should be [{0,0}, {1,1}, {3,5}, {4,3}] // one name got removed.
        ArrayList<int[]> expectedResults = new ArrayList<int[]>();
        //matches
        int[] matchWilson = new int[]{1,1};
        int[] matchLiana = new int[]{2,2};
        int[] matchDaniel = new int[]{4,6};
        int[] matchTyler = new int[]{5,4};

        expectedResults.add(matchWilson);
        expectedResults.add(matchLiana);
        expectedResults.add(matchDaniel);
        expectedResults.add(matchTyler);

        for (int i = 0; i < 4; i++){
            assertArrayEquals(expectedResults.get(i), results.get(i));
        }
       
    }

}
