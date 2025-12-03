//test: all lines are the same but changed variable names
public class Calc {
    private int currentResult;

    public Calc() {
        this.currentResult = 0;
    }

    public int addition(int x, int y) {
        int sum = x + y;
        this.currentResult = sum;
        System.out.println("Sum is: " + sum);
        return sum;
    }
//comment
    public int subtract(int a, int b) {
        int diff = a - b;
        this.currentResult = diff;
        System.out.println("Diff is: " + diff);
        return diff;
    }
}