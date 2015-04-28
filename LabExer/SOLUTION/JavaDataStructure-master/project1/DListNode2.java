/* DListNode2.java */

/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode2 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int item;
  public int num;
  public DListNode2 prev;
  public DListNode2 next;

  /**
   *  DListNode2() constructor.
   */
  DListNode2() {
    item = Ocean.EMPTY;
    num = 0;
    prev = null;
    next = null;
  }

  DListNode2(int item) {
    this.item = item;
    num = 1;
    prev = null;
    next = null;
  }

  DListNode2(int item, int num) {
    this.item = item;
    this.num = num;
    prev = null;
    next = null;
  }

}