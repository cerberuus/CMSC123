/* HashTableChained.java */

package dict;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {
 
  /**
   *  Place any data fields here.
   **/
  protected int hashSize;
  protected HashElement[] hashList;
  private final static int DEFAULTSIZE = 199;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    hashList = new HashElement[sizeEstimate];
    hashSize = 0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    hashList = new HashElement[DEFAULTSIZE];
    hashSize = 0;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return ((71 * code + 997) % Integer.MAX_VALUE) % hashList.length;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return hashList.length;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return (hashList.length == 0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry entry = new Entry();
    entry.key = key;
    entry.value = value;
    int code = Math.abs(compFunction(key.hashCode()));
    if(hashList[code] == null) {
      hashList[code] = new HashElement();
      hashList[code].nodes = new LinkedList<Entry>();
      hashList[code].nodes.push(entry);
      hashSize++;
    } else {
      hashList[code].nodes.push(entry);
      hashSize++;
    }
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int code = Math.abs(compFunction(key.hashCode()));
    if(hashList[code] == null) {
      return null;
    }
    Entry entry = hashList[code].nodes.peek();
    if(entry == null) {
      return null;
    } else {
      if(entry.key.equals(key)) {
        return entry;
      } else {
        ListIterator<Entry> list = hashList[code].nodes.listIterator();
        while(list.hasNext()){
          entry = list.next();
          if(entry.key.equals(key)) {
            return entry;
          }
        }
      }
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int code = Math.abs(compFunction(key.hashCode()));
    if(hashList[code] == null) {
      return null;
    }
    Entry entry = hashList[code].nodes.peek();
    if(entry == null) {
      return null;
    } else {
      if(entry.key.equals(key)) {
        hashList[code].nodes.pop();
        hashSize--;
        return entry;
      } else {
        ListIterator<Entry> list = hashList[code].nodes.listIterator();
        while(list.hasNext()){
          entry = list.next();
          if(entry.key.equals(key)) {
            list.remove();
            hashSize--;
            return entry;
          }
        }
      }
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    hashList = new HashElement[hashList.length];
    hashSize = 0;
  }

  public int numCollision() {
        int num = 0;
        for(int i = 0; i < hashList.length; ++i) {
            if(hashList[i] != null) {
              num += hashList[i].nodes.size() - 1;
            }
        }
        return num;
    }

}