/**
 *
 * Part IV:  Method Overriding (1 point)
 * -------------------------------------
 * Consider a subclass that has a method that overrides a method with the same
 * prototype in its superclass.
 *
 *************************************************************
 *
 * (a)  Define a variable whose static type is the subclass and which references
 * an object of the subclass.  If we cast the variable to the superclass type
 * before calling the overridden method
 *
 * ((Superclass) subclassvariable).method();
 *
 * does Java call the superclass method or the subclass method?
 * - Java calls the subclass method.
 * (b)  Define a variable whose static type is the superclass and which references
 * an object of the superclass (but not the subclass).  If we cast the
 * variable to the subclass type before calling the method, does Java call
 * the superclass method or the subclass method?
 * - run-time error.
 * (c)  Suppose you have an object whose class is the subclass.  Can you figure
 * out a way to call the superclass method on that object without having to
 * go through the subclass method of the same name?
 * - no
 *************************************************************
 */
public class Part4 {
	public static void main(String[] args) {

		// (a)
		Y y = new Y();
		((X) y).printOne(); // prints 2
		((X) y).printField(); // prints 2

		// (b)
		/*
		 * Exception in thread "main" java.lang.ClassCastException: part4.X cannot be cast
		 * to part4.Y	at part4.Part4.main(Part4.java:14)
		 * Java Result: 1
		 */
		//X x = new X();
		//((Y) x).printOne();
		//((Y) x).printField();
	}
}