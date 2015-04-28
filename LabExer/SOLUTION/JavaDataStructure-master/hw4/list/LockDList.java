package list;

public class LockDList extends DList {
	// a locked node can never be removed from its list

	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}

	public LockDList() {
		head = newNode(null, head, head);
		size = 0;
	}

	public void lockNode(DListNode node) {
		((LockDListNode) node).lock = true;
	}

	public void remove(DListNode node) {
		if(((LockDListNode) node).lock == true) {
			return;
		} else {
			super.remove(node);
		}
	}
}