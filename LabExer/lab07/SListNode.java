/* SListNode.java */

public class SListNode{

	protected SListNode head;
	protected SListNode tail;
	protected SListNode next;
	Object item;

	public SListNode(){
		head = null;
		item = 0;
	}

	public SListNode(Object i){
		next = null;
		item = i;
	}

	public SListNode(Object obj, SListNode next) {
	    item = obj;
	    this.next = next;
	}
}