/**
 *
 * Part II:  Conflicting Declarations (1 point)
 * --------------------------------------------
 * Suppose a subclass inherits a method implementation from a superclass, and
 * implements a Java interface (that's the "interface" keyword) that contains
 * a method with the same name and prototype.
 *
 * (a)  Will Java compile the result?
 * - Yes, but the method must be overrided. Since it is one of method in interface.
 *
 *************************************************************
 * (b)  What if the method declaration in the interface has a different return type?
 * - No: Exception in thread "main" java.lang.RuntimeException: Uncompilable source code - returnOne()
 * in part2.Child cannot override returnOne() in part2.Parent return type double is not compatible with int
 * Besides, if it is the same with the interface method, then show the error: 
 * error: returnOne() in Child cannot override returnOne() in Parent
 *	public double returnOne() {
 *	              ^
 * return type double is not compatible with int
 * However, if it is the same with the parent method, then show the following error:
 * java:3: error: Child is not abstract and does not override abstract method returnOne() in Interface:
 * Error:  public class Child extends Parent implements Interface {
 *        ^
 * Error: returnOne() in Child cannot implement returnOne() in Interface
 * 	public int returnOne() {
 * 	           ^
 *   return type int is not compatible with double
 *************************************************************
 * (c)  What if the method declaration in the interface has the same return type,
 * but a signature with a different parameter type?
 * - Yes, different prototypes are fine.
 *************************************************************
 * (d)  What if the method declaration in the interface has the same return type,
 * and the same number of parameters and parameter types, but those
 * parameters have different names?
 * - That's ok. Names don't matter, types matter.
 *************************************************************
 */
public class Part2 {

	public static void main(String[] args) {
		Parent parent = new Parent();
		System.out.println("parent:");
		System.out.println(parent.returnOne()); // 1
		System.out.println(parent.threeIntegers(1, 5, 10)); // 50

		Child child = new Child();
		System.out.println("child:");
		System.out.println(child.returnOne()); // 2
		System.out.println(child.returnOne(2.0)); // 2
		System.out.println(child.threeIntegers(1, 5, 10)); //16
	}
}