import java.util.ArrayList;
import java.util.Stack;

/*1 cat timp exista o entitate de citit:
    1.1 citeste entitatea (i.e. operand sau operator)
    1.2. daca entitatea este un operand (i.e. un numar), atunci:
        1.2.1 adauga la forma postfixata
    1.3 daca entitatea este un operator (fie el O1), atunci:
        1.3.1 cat timp (exista un operator in varful stivei (fie el O2)) SI
                      (O2 este diferit de paranteza-stanga) SI
                      (precedenta(O1) < precedenta(O2) SAU 
                       precedenta(O1) = precedenta(O2) SI O2 are asociativitate-stanga)
            1.3.1.1 extrage O2 si adauga-l la forma postfixata
        1.3.2 adauga O1 in stiva
    1.4 daca entitatea este o paranteza-stanga, atunci:
        1.4.1 adauga paranteza in stiva
    1.5 daca entitatea este o paranteza-dreapta, atunci:
        1.5.1 cat timp operatorul din varful stivei (fie el O) nu este paranteza-stanga:
            1.5.1.1 extrage O si adauga-l la forma postfixata
        1.5.2 daca stiva s-a golit (si nu s-a gasit o paranteza-stanga)
            1.5.2.1 returneaza eroare (i.e. expresia avea paranteze gresite)
        1.5.3 extrage paranteza-stanga din varful stivei
 
// in acest moment nu mai sunt entitati de citit
 
2 cat timp exista operator in stiva (fie el O)
    2.1 extrage O si adauga-l la forma postfixata
    2.2 daca O este o paranteza-stanga, atunci:
        2.2.1 returneaza eroare (i.e. expresia avea paranteze gresite)
 
3 afiseaza / returneaza forma postfixata*/

/*1 cat timp exista o entitate de citit:
    1.1 citeste entitatea (i.e. operand sau operator)
    1.2. daca entitatea este un operand (i.e. un numar), atunci:
        1.2.1 adauga entitatea in stiva
    1.3 daca entitatea este un operator (fie el O), atunci:
        1.3.1 extrage un operand din stiva (fie el op1)
        1.3.2 extrage un operand din stiva (fie el op2) 
        1.3.3 daca nu exista cei 2 operanzi (i.e. op1 si op2), atunci:
            1.3.3.1 returneaza eroare (i.e. expresia postfixata este gresita)
        1.3.4 rezultat = op2 O op1
        1.3.5 adauga rezultat in stiva
 
2 rezultat = extrage un operator din stiva
 
3 daca stiva nu este goala, atunci:
  3.1. returneaza eroare (i.e. expresia postfixata este gresita)
 
4 afiseaza / returneaza rezultat*/

public class Alg_ShuntingYard {

	private static boolean isOperator(String element) {
		switch (element) {
		case "+":
			return true;
		case "-":
			return true;
		case "*":
			return true;
		case "/":
			return true;
		case "^":
			return true;
		}
		return false;
	}

	private static boolean isParenthesis(String element) {
		switch (element) {
		case "(":
			return true;
		case ")":
			return true;
		}
		return false;
	}

	private static int getPrecedence(String operator) {
		switch (operator) {
		case "+":
			return 11;
		case "-":
			return 11;
		case "*":
			return 12;
		case "/":
			return 12;
		case "^":
			return 13;
		default:
			return 0;
		}
	}

	private static boolean isLeftAsoc(String operator) {
		if (!operator.equals("^")) {
			return true;
		}
		return false;
	}

	private static int calculateResult(int op2, String operator, int op1) {
		switch (operator) {
		case "+":
			return op2 + op1;
		case "-":
			return op2 - op1;
		case "*":
			return op2 * op1;
		case "/":
			return op2 / op1;
		case "^":
			int value = op2;
			for (int i = 1; i < op1; i++) {
				op2 *= value;
			}
			return op2;
		}
		return 0;
	}

	public static ArrayList<String> getPostfixForm(String[] elements) {

		ArrayList<String> post = new ArrayList<String>();
		Stack<String> operatorStack = new Stack<String>();

		boolean found = false;

		for (String element : elements) {
			if (!isOperator(element) && !isParenthesis(element)) { // 1.2. daca entitatea este un operand
				post.add(element); 
			} else if (isOperator(element)) { 
				while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")      // 1.3.1
						&& (getPrecedence(element) < getPrecedence(operatorStack.peek())
								|| getPrecedence(element) == getPrecedence(operatorStack.peek())
										&& isLeftAsoc(operatorStack.peek()))) {
					post.add(operatorStack.pop()); // 1.3.1.1 
				}
				operatorStack.add(element);
			} else if (element.equals("(")) {
				operatorStack.add(element);
			} else if (element.equals(")")) {
				while (!operatorStack.peek().equals("(")) {
					post.add(operatorStack.pop());
				}
				if (!operatorStack.peek().equals("("))
					found = true;
				if (operatorStack.isEmpty() && !found) {
					System.out.println("Wrong expression!");
					return null;
				} else {
					found = false;
				}
				operatorStack.pop();
			}
		}

		while (!operatorStack.isEmpty()) { // 2 cat timp exista operator in stiva (fie el O)
			String operator = operatorStack.pop();
			post.add(operator);
			if (operator.equals("(")) {
				System.out.println("Wrong expression");
				return null;
			}
		}
		return post;
	}

	public static int evaluatePostfixForm(String[] elements) {

		Stack<String> operatorStack = new Stack<String>();

		for (String element : elements) {
			if (!isOperator(element)) { // 1.2. daca entitatea este un operand
				operatorStack.add(element);
			} else if (isOperator(element)) { // 1.3 daca entitatea este un operator
				if (operatorStack.size() > 1) {
					int op1 = Integer.parseInt(operatorStack.pop());
					int op2 = Integer.parseInt(operatorStack.pop());
					operatorStack.add(String.valueOf(calculateResult(op2, element, op1)));
				} else {
					System.out.println("Wrong Postfix!");
					return Integer.MIN_VALUE;
				}
			}
		}
		int result = Integer.parseInt(operatorStack.pop());
		if (!operatorStack.isEmpty()) {
			System.out.println("Wrong Postfix");
			return Integer.MIN_VALUE;
		}
		return result;
	}

}
