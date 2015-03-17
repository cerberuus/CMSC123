public class BadTransactionException extends Exception{
	public int amount;

	public BadTransactionException(int wrongAmount){
		super("Invalid amount " + wrongAmount);

		amount = wrongAmount;
	}

}