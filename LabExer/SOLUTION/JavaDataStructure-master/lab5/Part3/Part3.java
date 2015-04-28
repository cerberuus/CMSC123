/**
 * Part III:  More Conflicting Declarations (1 point)
 * --------------------------------------------------
 * Suppose a subclass inherits a "public static final" constant from a superclass,
 * and implements a Java interface that contains a "public static final" constant
 * with the same name.
 *************************************************************
 * (a)  Will Java compile the result?  Does it make any difference whether the
 * constant in the superclass and the constant in the interface have the
 * same value?
 * - ..if the child declares the same 'public static final varname': Yes. Otherwise: No
 * - no. If it has the same name, it will not show errors. However, the value cannot be
 * accessed clearly. The status access can be used. Like Parent.foo, or Interface.foo.
 *************************************************************
 * (b)  Write a main() method in the subclass that accesses the constant using the
 * same name used in the superclass and the Java interface.  Will Java
 * compile the result?  Does it make any difference whether the constant in
 * the superclass and the constant in the interface have the same value?
 * - No. Uncompilable source code - reference to foo is ambiguous (see Child.java)
 * - No. Compiler doesn't care what value they have. It cares the reference is ambiguous.
 *************************************************************
 * (c)  Figure out how to modify your main() method so that it accesses and prints
 * one of the two conflicting values.  (Look to Lecture 9 for clues.)  Make
 * sure your code compiles and runs without errors.
 * - These work fine: (see Child.java)
 *   Parent.foo
 *   Interface.foo
 *************************************************************
 */
public class Part3 {

	public static void main(String[] args) {
		Child child = new Child();
		
		/*
		 * Exception in thread "main" java.lang.RuntimeException: Uncompilable
		 * source code - reference to foo is ambiguous, both variable foo in part3.Parent
		 * and variable foo in part3.Interface match
		 * at part3.Part3.main(Part3.java:19)
		 * Java Result: 1
		 */
		//System.out.println(child.foo);
		//
		//
		/*
		 * Exception in thread "main" java.lang.RuntimeException:
		 * Uncompilable source code - reference to foo is ambiguous, both variable foo in part3.
		 * Parent and variable foo in part3.Interface match
		 * at part3.Part3.main(Part3.java:27)
		 * Java Result: 1
		 */
		//System.out.println(Child.foo);

	}
}