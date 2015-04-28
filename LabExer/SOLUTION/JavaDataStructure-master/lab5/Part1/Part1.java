
/**
 ************************************************************
 *
 * Part I:  Assignments and Casting (1 point)
 * ------------------------------------------
 * Let Y be a subclass of X, and let y and x be variables of classes Y and X
 * respectively.  From lecture, you know that the assignment "x = y" is valid, but
 * the assignment "y = (Y) x" requires a cast to compile, and will cause a
 * run-time error if x references an object that isn't a Y.
 *
 * What about arrays of objects?  Suppose xa is an array of X's, and ya is an
 * array of Y's.
 *
 *************************************************************
 * Questions
 *************************************************************
 * (a)  At compile-time, can we assign xa to ya, and vice versa?  When is a cast required?
 *
 * - Yes. But we can do the assign if and only if: the xa hold the Y object, and finally cast xa to Y
 * 	 then assign xa to ya. see testArrayAssign_xa_of_Y_to_ya_withCast(). 
 * - We can cast to a subclass array.
 *   ie: Subclass[] y = (Subclass[]) x;
 *   if and only if: x is an array of SuperClass static type that
 *   actually refences an array Subclass objects. see testArrayAssign_ya_to_xa_and_back_with_cast() )
 *************************************************************
 * (b)  At run-time, if ya references an array of Y's, can we assign it to xa?
 * Can we then assign it back from xa to ya?
 *
 * - Yes.
 * - Yes, with a cast. see answer to (a).
 *************************************************************
 * (c)  If xa references an array of X's (that are not Y's), can we assign it to
 * ya?  Can we then assign it back from ya to xa?  Does it make a difference
 * if the array of type X[] references objects that are all of class Y?  Why
 * do you think this is the case?
 *
 * - No. see testArrayAssign_xa_to_ya() and testArrayAssign_xa_to_ya_withCast()
 * - No...
 * - No, see testArrayAssign_xa_of_Y_to_ya_withCast()
 * - In Java, arrays are type-aware. What we should notice here is that:
 * - here, it says that the type x[] references ojbects that are all of class Y, if we 
 * - initialzed the array to be an array of X reference, then it likes X[] with X references, which reference 
 * - to a Y object, which is legal( x = y). However, if we says it likes this:
 * - X[] with Y references, (dynamic type is Y, but the static type is X), the Y references refer to the 
 * - Y object, at this situation, the Y[] ya = (Y[]) xa; can be legal. Like a.
 *************************************************************
 */
public class Part1 {

	public static void main(String[] args) {
		X x = new X();
		Y y = new Y();
		x.printOne(); // prints 1
		y.printOne(); // prints 2
		x.printClass(); // prints X
		y.printClass(); // prints Y
		System.out.println("x.field = " + x.field); // = X
		System.out.println("y.field = " + y.field); // = Y
		System.out.println("(new X()).field = " + (new X()).field); // = X
		System.out.println("(new Y()).field = " + (new Y()).field); // = Y
		System.out.println("( (X) (new Y())).field = " + ((X) (new Y())).field); // = X
		// run-time error:
		// Exception in thread "main" java.lang.ClassCastException: X cannot be cast to Y
		//System.out.println("( (Y) (new X())).field = " + ((Y) (new X())).field);
		testAssign_x_to_y_WithoutCast();
		testAssign_x_to_y_WithCast();
		testAssign_y_to_x_WithoutCast();
		testAssign_y_to_x_WithCast();
		testArrayAssign_xa_to_ya();
		testArrayAssign_xa_to_ya_withCast();
		testArrayAssign_xa_of_Y_to_ya_withCast();
		testArrayAssign_ya_to_xa();
		testArrayAssign_ya_to_xa_and_back();
		testArrayAssign_ya_to_xa_and_back_with_cast();
		
	}

	/**
	 * compile time error
	 * Exception in thread "main" java.lang.RuntimeException: Uncompilable source code - incompatible types
	 * required: Y
	 * found:    X
	 */
	public static void testAssign_x_to_y_WithoutCast() {
		//X x = new X();
		//Y y = x;
	}

	/**
	 * run time error
	 * Exception in thread "main" java.lang.ClassCastException: X cannot be cast to Y
	 */
	public static void testAssign_x_to_y_WithCast() {
		//X x = new X();
		//Y y = (Y) x;
	}

	/**
	 * Works fine if we aasign y to x, which is assign child to parents, dynamic
	 */
	public static void testAssign_y_to_x_WithoutCast() {
		System.out.println("testAssign_y_to_x_WithoutCast:");
		Y y = new Y();
		X x = y;
		x.printOne(); // prints Y
		y.printOne(); // prints Y
		x.printClass(); // prints Y
		y.printClass(); // prints Y
		System.out.println(x.field); // prints X
		System.out.println(y.field); // prints Y
	}

	/**
	 * Works fine
	 */
	public static void testAssign_y_to_x_WithCast() {
		System.out.println("testAssign_y_to_x_WithCast:");
		Y y = new Y();
		X x = (X) y;
		x.printOne(); // prints Y
		y.printOne(); // prints Y
		x.printClass(); // prints Y
		y.printClass(); // prints Y
		System.out.println(x.field); // prints X
		System.out.println(y.field); // prints Y
	}

	/**
	 * compile time error.
	 * Exception in thread "main" java.lang.RuntimeException: Uncompilable source code - incompatible types
	 * required: Y[]
	 * found:    X[]
	 */
	public static void testArrayAssign_xa_to_ya() {
//		X[] xa = new X[5];
//		for (int i = 0; i < 5; i++) {
//			xa[i] = new X();
//		}
//		Y[] ya = xa;
	}

	/**
	 * run-time error.
	 * Exception in thread "main" java.lang.ClassCastException: X; cannot be cast to Y;
	 */
	public static void testArrayAssign_xa_to_ya_withCast() {
//		X[] xa = new X[5];
//		for (int i = 0; i < 5; i++) {
//			xa[i] = new X();
//		}
//		Y[] ya = (Y[]) xa;
	}

	/**
	 * run-time error
	 * Exception in thread "main" java.lang.ClassCastException: X; cannot be cast to Y;
	 */
	public static void testArrayAssign_xa_of_Y_to_ya_withCast() {
		X[] xa = new Y[5]; // get through it
		//X[] xa = new N[5]; //  runtime error as describe before
		for (int i = 0; i < 5; i++) {
			xa[i] = new Y();
		}
		Y[] ya = (Y[]) xa; // without the cast it's a compile-time error.
	}

	public static void testArrayAssign_ya_to_xa() {
		System.out.println("testArrayAssign_ya_to_xa");
		Y[] ya = new Y[5];
		for (int i = 0; i < 5; i++) {
			ya[i] = new Y();
		}
		X[] xa = ya;
		xa[0].printOne(); // prints Y
		ya[0].printOne(); // prints Y
		xa[0].printClass(); // prints Y
		ya[0].printClass(); // prints Y
		System.out.println(xa[0].field); // prints X
		System.out.println(ya[0].field); // prints Y
	}

	/**
	 * compile time error
	 * Exception in thread "main" java.lang.RuntimeException: Uncompilable source code - incompatible types
	 * required: Y[]
	 * found:    X[]
	 */
	public static void testArrayAssign_ya_to_xa_and_back() {
//		System.out.println("testArrayAssign_ya_to_xa_and_back:");
//		Y[] ya = new Y[5];
//		for (int i = 0; i < 5; i++) {
//			ya[i] = new Y();
//		}
//		X[] xa = ya;
//		Y[] ya2 = xa;
	}

	public static void testArrayAssign_ya_to_xa_and_back_with_cast() {
		System.out.println("testArrayAssign_ya_to_xa_and_back_with_cast:");
		Y[] ya = new Y[5];
		for (int i = 0; i < 5; i++) {
			ya[i] = new Y();
		}
		X[] xa = ya;
		Y[] ya2 = (Y[]) xa;
		xa[0].printOne(); // prints Y
		ya[0].printOne(); // prints Y
		xa[0].printClass(); // prints Y
		ya[0].printClass(); // prints Y
		System.out.println(xa[0].field); // prints X
		System.out.println(ya[0].field); // prints Y
	}
}