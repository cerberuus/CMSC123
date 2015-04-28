/* DList2.java */

/**
 * DList is utilized from lab04 
 * Evan James Tingcoy
 */

/**
 *  A DList2 is a mutable doubly-linked list.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected long size;

  /* DList2 invariants:
   *  1)  head != null.
   *  2)  For any DListNode2 x in a DList2, x.next != null.
   *  3)  For any DListNode2 x in a DList2, x.prev != null.
   *  4)  For any DListNode2 x in a DList2, if x.next == y, then y.prev == x.
   *  5)  For any DListNode2 x in a DList2, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNode2s, NOT COUNTING the sentinel
   *      (denoted by "head"), that can be accessed from the sentinel by
   *      a sequence of "next" references.
   */

  /**
   *  DList2() constructor for an empty DList2.
   */
  public DList() {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  DList2() constructor for a one-node DList2.
   */
  public DList(Object a) {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = new DListNode();
    head.next.item = a;
    head.prev = head.next;
    head.next.prev = head;
    head.prev.next = head;
    size = 1;
  }

  /**
   *  DList2() constructor for a two-node DList2.
   */
  public DList(Object a, Object b) {
    head = new DListNode();
    head.item = Integer.MIN_VALUE;
    head.next = new DListNode();
    head.next.item = a;
    head.prev = new DListNode();
    head.prev.item = b;
    head.next.prev = head;
    head.next.next = head.prev;
    head.prev.next = head;
    head.prev.prev = head.next;
    size = 2;
  }

  /**
   *  insertFront() inserts an item at the front of a DList2.
   */
  public void insertFront(Object i) {
    // Fixed insertFront
    DListNode temp = new DListNode();
    temp.item = i;
    temp.next = head.next;
    temp.prev = head;
    head.next.prev = temp;
    head.next = temp;
    size++;
}

    /**
     *DListNode2 tmpNode = new DListNode2(i);
     *tmpNode.prev = head;
     *tmpNode.next = head.next;
     *head.next.prev = tmpNode;
     *head.next = tmpNode;
     *size++;
     *}
     */ 

  /**
   *  removeFront() removes the first item (and first non-sentinel node) from
   *  a DList2.  If the list is empty, do nothing.
   */
   public void removeFront() {
      if(size != 0){
        head.next = head.next.next;
        //head.next.item = i;
        head.next.prev = head;
        size--;
      }
  }

  /**
   * remove() removes the item from DList
   */

  public void remove(DListNode node) {
    if (node != null) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      node.next = null;
      node.prev = null;
      size--;
    }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   */
}
