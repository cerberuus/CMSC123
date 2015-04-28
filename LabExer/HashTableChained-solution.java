/* HashTableChained.java */

package dict;

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
	
	list.DList[] table;
	int tablesize;
	
	/** 
	*  Construct a new empty hash table intended to hold roughly sizeEstimate
	*  entries.  (The precise number of buckets is up to you, but we recommend
	*  you use a prime number, and shoot for a load factor between 0.5 and 1.)
	**/
	
	public HashTableChained(int sizeEstimate) {
		int targetsize = (int) Math.round(sizeEstimate); // originally this actually gave a load factor of ~1.25
		table = new list.DList[targetsize];
	}
	
	/** 
	*  Construct a new empty hash table with a default size.  Say, a prime in
	*  the neighborhood of 100.
	**/
	
	public HashTableChained() {
		table = new list.DList[101];
	}
	
	/**
	 *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
	 *  to a value in the range 0...(size of hash table) - 1.
	 *
	 *  This function should have package protection (so we can test it), and
	 *  should be used by insert, find, and remove.
	 **/
	
	int compFunction(int code) {
	 	/* int returnval = (((6*code + 11) % 536870911) % table.length);
		if (returnval < 0) {
			returnval = returnval + table.length;
		} */
		int returnval = Math.abs((((6*code + 11) % 67108865) % table.length));
		return returnval;
	}
	
	/** 
	*  Returns the number of entries stored in the dictionary.  Entries with
	*  the same key (or even the same key and value) each still count as
	*  a separate entry.
	*  @return number of entries in the dictionary.
	**/
	
	public int size() {
		return tablesize;
	}
	
	/** 
	*  Tests if the dictionary is empty.
	*
	*  @return true if the dictionary has no entries; false otherwise.
	**/
	
	public boolean isEmpty() {
		if (tablesize == 0) {
			return true;
		} else {
			return false;
		}
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
		Entry insertion = new Entry();
		insertion.key = key;
		insertion.value = value;
		int targetindex = compFunction(key.hashCode());
		if (table[targetindex] == null) {
			list.DList newlist = new list.DList();
			newlist.insertFront(insertion);
			table[targetindex] = newlist;
		} else {
			table[targetindex].insertFront(insertion);
		}
		tablesize++;
		return insertion;
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
		int targetindex = compFunction(key.hashCode());
		list.DList targetlist = table[targetindex];
		if (targetlist != null) {
			list.ListNode curnode = null;
			try { // since we're using a DList implementation that throws exceptions, this is required
				curnode = targetlist.front();
				while (!((Entry) curnode.item()).key().equals(key)) { // if the current key is not equals() to the one we're looking for, look in the next node
					curnode = curnode.next();
				}
				return (Entry) curnode.item(); // found a match w/out throwing exceptions
			} catch (list.InvalidNodeException e) { // no match for key in the list
				return null;
			}
		} else {
			return null;	
		}
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
		int targetindex = compFunction(key.hashCode());
		list.DList targetlist = table[targetindex];
		if (targetlist != null) {
			list.ListNode curnode = null;
			try { // since we're using a DList implementation that throws exceptions, this is required
				curnode = targetlist.front();
				while (!((Entry) curnode.item()).key().equals(key)) { // if the current key is not equals() to the one we're looking for, look in the next node
					curnode = curnode.next();
				}
				Entry returned = (Entry) curnode.item(); // found a match w/out throwing exceptions
				curnode.remove();
				tablesize--;
				return returned;
			} catch (list.InvalidNodeException e) { // no match for key in the list
				return null;
			}
		} else {
			return null;	
		}
	}
	
	/**
	 *  Remove all entries from the dictionary.
	 */
	public void makeEmpty() {
		int targetsize = table.length - 1;
		table = new list.DList[targetsize];
		tablesize = 0;
	}
	
	/**
	 * Print statistical info about the hashtable: the number of keys stored in each bucket and the total number of collisions.
	 */
	public void printHistogram() {
		String histog = "";
		int cols = 0;
		for (int i=0; i<table.length; i++) {
			if (table[i] == null) {
				histog = histog + " 0";
			} else {
				histog = histog + " " + table[i].length();
				cols = cols + table[i].length() - 1;
			}
		}
		System.out.println(histog);
		System.out.println("Number of collisions is " + cols + " and table size is " + table.length);
	}
}