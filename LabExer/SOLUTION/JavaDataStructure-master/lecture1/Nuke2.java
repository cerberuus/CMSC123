/* Problem 2
---------
Write a program called "Nuke2.java" containing a class called Nuke2 whose main
method reads a string from the keyboard and prints the same string, with its
second character removed.  (That's character number 1, since Java numbers
characters in a string starting from zero.)  In other words, after you run
"java Nuke2", if you type in the string "skin", the program will print "sin".
 */
import java.util.*;

public class Nuke2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String word = scan.next();
		if(word.length() < 2) {
			System.out.println("Wrong size of string.");
			return;
		}
		word = word.charAt(0) + word.substring(2);
		System.out.println(word);
	}
}