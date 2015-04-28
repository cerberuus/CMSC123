/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  protected List list;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    list = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return list.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    if(list.length() == 0) {
      list.insertFront(c);
    } else { 
      itHere:
        try {
          ListNode node = list.front();
          while(node.isValidNode()) {
            int value = c.compareTo(((Comparable)node.item()));
            if(value > 0) { // c > node
              node.insertBefore(c);
              break itHere;
            } else if(value == 0) {
              break itHere;
            } else { // node > c
              node = node.next();
            }
          }
          list.insertBack(c);
        } catch( InvalidNodeException e) {
          System.err.println(e);
        }
    }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
    ListNode sNode = s.list.front();
    ListNode tNode = list.front();
    ListNode sNodeEnd = s.list.back();
    ListNode tNodeEnd = list.back();
    try {
      if(!sNode.isValidNode()) {
        return;
      }
      if(!tNode.isValidNode()) {
        while(sNode.isValidNode()) {
          list.insertBack(sNode.item());
          sNode = sNode.next();
        }
        return;
      }
      if(((Comparable)sNodeEnd.item()).compareTo((Comparable)tNode.item()) >= 0) {
        while(sNode.isValidNode()) {
          if(((Comparable)sNodeEnd.item()).compareTo((Comparable)tNode.item()) == 0 && (!sNode.next().isValidNode())) {
            return;
          }
          tNode.insertBefore(sNode.item());
          sNode = sNode.next();
        }
        return;
      }
      if((((Comparable)tNodeEnd.item()).compareTo((Comparable)sNode.item())) >= 0) {
        if((((Comparable)tNodeEnd.item()).compareTo((Comparable)sNode.item())) == 0) {
          sNode = sNode.next();
        }
        while(sNode.isValidNode()) {
          list.insertBack(sNode.item());
          sNode = sNode.next();
        }
        return;
      }
      for(int i = 0; i < this.cardinality(); i++) {
        while(sNode.isValidNode()) {
          int bool = ((Comparable)tNode.item()).compareTo((Comparable)sNode.item());
          if(bool == 0) { // t = s
            sNode = sNode.next();
            tNode = tNode.next();
            break;
          } else if(bool < 0) { // s > t
            tNode.insertBefore(sNode.item());
            sNode = sNode.next();
          } else { // t > s
            tNode = tNode.next();
            break;
          }
        }
      }
      while(sNode.isValidNode()) {
        list.insertBack(sNode.item());
        sNode = sNode.next();
      }
      return;
    } catch(InvalidNodeException e) {
      System.err.println(e);
    }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    ListNode sNode = s.list.front();
    ListNode tNode = list.front();
    try {
    // Your solution here.
      int times = this.cardinality();
      for(int i = 0; i < times; i++) {
        while(sNode.isValidNode()) {
          int bool = ((Comparable)tNode.item()).compareTo((Comparable)sNode.item());
          if(bool == 0) {
            sNode = sNode.next();
            tNode = tNode.next();
            break;
          } else if(bool < 0) { // s > t
            sNode = sNode.next();
          } else { // t > s
            ListNode delNode = tNode;
            tNode = tNode.next();
            delNode.remove();
            break;
          }
        }
        if(!sNode.isValidNode()) {
          break;
        }
      }
      while(tNode.isValidNode()) {
        ListNode delNode = tNode;
        tNode = tNode.next();
        delNode.remove();
      }
      return;
    } catch(InvalidNodeException e) {
      System.err.println(e);
    }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    String result = "{  ";
    ListNode current = list.front();
    try {
      while (current.isValidNode()) {
        result = result + current.item() + "  ";
        current = current.next();
      }
    } catch(InvalidNodeException e) {
      System.err.println(e);
    }
    return result + "}";
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);
    Set s4 = new Set();
    System.out.println("Set s4 = "  + s4);
    Set s5 = new Set();
    System.out.println("Set s5 = "  + s5);
    s.union(s4);
    System.out.println("After s.union(s4), s = " + s);
    s4.union(s);
    System.out.println("After s4.union(s), s4 = " + s4);
    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);
    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);
    s5 = new Set();
    s.intersect(s5);
    System.out.println("After s.intersect(s5), s = " + s);
    s5.intersect(s3);
    System.out.println("After s5.intersect(s3), s5 = " + s5);

    System.out.println("s.cardinality() = " + s.cardinality());
    System.out.println("========Union test=========");
    Set s6 = new Set();
    s6.insert(new Integer(3));
    s6.insert(new Integer(4));
    s6.insert(new Integer(5));
    System.out.println("Set s6 = " + s6);
  
    Set s7 = new Set();
    s7.insert(new Integer(1));
    s7.insert(new Integer(2));
    s7.insert(new Integer(3));
    System.out.println("Set s7 = " + s7);
  
    s6.union(s7);
    System.out.println("After s6.union(s7), s6 = " + s6);

    s6 = new Set();
    s6.insert(new Integer(3));
    s6.insert(new Integer(4));
    s6.insert(new Integer(5));
    System.out.println("Set s6 = " + s6);
  
    s7 = new Set();
    s7.insert(new Integer(1));
    s7.insert(new Integer(2));
    s7.insert(new Integer(0));
    System.out.println("Set s7 = " + s7);
  
    s6.union(s7);
    System.out.println("After s6.union(s7), s6 = " + s6);

    s6 = new Set();
    s6.insert(new Integer(1));
    s6.insert(new Integer(2));
    s6.insert(new Integer(3));
    System.out.println("Set s6 = " + s6);
  
    s7 = new Set();
    s7.insert(new Integer(3));
    s7.insert(new Integer(6));
    s7.insert(new Integer(8));
    System.out.println("Set s7 = " + s7);
  
    s6.union(s7);
    System.out.println("After s6.union(s7), s6 = " + s6);

    s6 = new Set();
    s6.insert(new Integer(1));
    s6.insert(new Integer(2));
    s6.insert(new Integer(0));
    System.out.println("Set s6 = " + s6);
  
    s7 = new Set();
    s7.insert(new Integer(3));
    s7.insert(new Integer(6));
    s7.insert(new Integer(8));
    System.out.println("Set s7 = " + s7);
  
    s6.union(s7);
    System.out.println("After s6.union(s7), s6 = " + s6);

    s6 = new Set();
    s6.insert(new Integer(1));
    s6.insert(new Integer(2));
    s6.insert(new Integer(3));
    System.out.println("Set s6 = " + s6);
  
    s7 = new Set();
    s7.insert(new Integer(2));
    s7.insert(new Integer(3));
    s7.insert(new Integer(8));
    System.out.println("Set s7 = " + s7);
  
    s6.union(s7);
    System.out.println("After s6.union(s7), s6 = " + s6);
  }
    //You may want to add more (ungraded) test code here.
 
}