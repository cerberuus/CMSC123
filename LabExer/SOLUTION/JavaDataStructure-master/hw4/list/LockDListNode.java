package list;

public class LockDListNode extends DListNode {
	protected boolean lock;

	public LockDListNode(Object i, DListNode p, DListNode n) {
    super(i, p, n);
    lock = false;
  }	
}