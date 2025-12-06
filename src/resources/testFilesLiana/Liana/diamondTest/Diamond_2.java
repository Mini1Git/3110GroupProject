//line split, deletions, additions
import java.util.Scanner;

public class Diamond {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		
		while(true) {
			System.out.println("Enter an odd number from 1-19: ");
			n = scanner.nextInt();
			if (n >= 1 && 
                n <= 19 && 
                n % 2 != 0 ) {
				break;
			}
			else {
				System.out.println("Invalid number. Please try again");
			}
		}
		
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print("*");
			}

        System.out.println("Done hooray!");
        System.out.println("Java is awesome");
	}

}