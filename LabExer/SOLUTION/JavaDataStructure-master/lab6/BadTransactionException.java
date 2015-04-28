public class BadTransactionException extends Exception {

  public int invalidAmount;  
  public BadTransactionException(int badAmount) {
    super("Invalid amount: " + badAmount);

    invalidAmount = badAmount;
  }
}