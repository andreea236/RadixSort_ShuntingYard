import java.util.ArrayList;
import java.util.Stack;

public class Alg_RadixSort {

	public static ArrayList<Integer> radixSortAscending(ArrayList<Integer> numbers) {

		ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>(10);

		for (int i = 0; i < 10; i++) {
			stacks.add(new Stack<Integer>()); // add on
		}

		int div = 1;
		boolean finished = false;

		while (!finished) {
			finished = true;
			for (int number : numbers) {
				if (number / div != 0) {
					finished = false;
					stacks.get(number / div % 10).add(number);
				} else {
					stacks.get(0).add(number); //
				}
			}
			div *= 10;
			numbers.clear();
			for (int i = 0; i < stacks.size(); i++) {
				for (int number : stacks.get(i)) {
					numbers.add(number); // add
				}
				stacks.get(i).clear();
			}
		}
		return numbers;
	}

	public static ArrayList<Integer> radixSortDescending(ArrayList<Integer> numbers) {
		ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>(10);
		for (int i = 0; i < 10; i++) {
			stacks.add(new Stack<Integer>());
		}
		int div = 1;
		boolean finished = false;
		while (!finished) {
			finished = true;
			for (int number : numbers) {
				if (number / div != 0) {
					finished = false;
					stacks.get(number / div % 10).add(number);
				} else {
					stacks.get(0).add(number);
				}
			}
			div *= 10;
			numbers.clear();
			for (int i = 9; i >= 0; i--) {
				for (int number : stacks.get(i)) {
					numbers.add(number);
				}
				stacks.get(i).clear();
			}
		}
		return numbers;
	}
}
