import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

// Shunting Yard
		String expression = "3 + ( 2 + 1 ) * 2 ^ 3 ^ 2 - 8 / ( 5 - 1 * 2 / 2 ))";

		String[] mathExp = expression.split(" ");

		for (String element : mathExp) {
			System.out.print(element + " ");
		}

		System.out.println("\n");

		ArrayList<String> result = Alg_ShuntingYard.getPostfixForm(mathExp);

		for (String element : result) {
			System.out.print(element + " ");
		}

		System.out.println("\n");

		String[] postfixExpression = result.toArray(new String[0]);

		System.out.println("Result is: " + Alg_ShuntingYard.evaluatePostfixForm(postfixExpression));
		System.out.println("\n");

// Radix Sort		
		System.out.println("Numbers:");
		ArrayList<Integer> numbers = new ArrayList<Integer>(
				Arrays.asList(1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713));
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println("\nAscending:");
		numbers = Alg_RadixSort.radixSortAscending(numbers);
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println("\nDescending:");
		numbers = Alg_RadixSort.radixSortDescending(numbers);
		for (int number : numbers) {
			System.out.print(number + " ");
		}
	}
}
