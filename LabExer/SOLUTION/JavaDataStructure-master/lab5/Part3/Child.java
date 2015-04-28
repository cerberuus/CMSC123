/**
 *
 * @author DM
 */
public class Child extends Parent implements Interface {

	/*
	 * if this is declared here, everything's peachy... if not... trouble
	 * public static final int foo = 2;
	 * 
	 */

	public static void main(String[] args) {

		/*
		 * Compile time error.
		 * Exception in thread "main" java.lang.RuntimeException:
		 * Uncompilable source code - reference to foo is ambiguous,
		 * both variable foo in part3.Parent and variable foo in part3.Interface match
		 * at part3.Child.main(Child.java:24)
		 * Java Result: 1
		 */
		//System.out.println(foo);


		System.out.println(Parent.foo);
		System.out.println(Interface.foo);

	}
}