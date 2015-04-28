

public class Child extends Parent implements Interface {

	/**
	 * Exception in thread "main" java.lang.RuntimeException:
	 * Uncompilable source code - returnOne() in part2.Child cannot override returnOne() in part2.Parent
	 * return type double is not compatible with int
	 */
	//public double returnOne() {
	//}

	//INTERFACE
	/**
	 * interface declares a different prototype than parent. all's well.
	 * @param x
	 * @return
	 */
	public int returnOne(double x) {
		return (int)x;
	}

	//Override
	public int returnOne() {
		return 2; // returns 2 intentionally...
	}

	// Override and Interface
	public int threeIntegers(int xxx, int yyy, int zzz) {
		return xxx + yyy + zzz;
	}
}