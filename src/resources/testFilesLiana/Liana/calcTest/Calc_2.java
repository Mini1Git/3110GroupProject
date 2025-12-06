//test: all lines are the same but changed variable name
public class Calc {
    private int result;

    public Calc() {
        this.result = 0;
    }

    public int addition(int x, int y) {
        int sum = x + y;
        this.result = sum;
        System.out.println("Sum is: " + sum);
        return sum;
    }
//comment
    public int subtract(int a, int b) {
        int diff = a - b;
        this.result = diff;
        System.out.println("Diff is: " + diff);
        return diff;
    }
}