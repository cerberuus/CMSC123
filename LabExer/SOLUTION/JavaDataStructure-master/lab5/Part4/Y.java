public class Y extends X {
	// prints 2, intentionally.

	@Override
	public void printOne() {
		System.out.println(2);
	}

	@Override
	public void printField() {
		System.out.println(field);
	}
	private int field = 2; 
}