/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  private DList2 list;
  private int width;
  private int height;
  private int starveTime;
  private DListNode2 runNode;

  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public RunLengthEncoding(int i, int j, int starveTime) {
    // Your solution here.
    width = i;
    height = j;
    this.starveTime = starveTime;
    list = new DList2();
    runNode = list.head.next;
  }

  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int starveTime,
                           int[] runTypes, int[] runLengths) {
    width = i;
    height = j;
    this.starveTime = starveTime;
    list = new DList2();
    if(runTypes.length != runLengths.length) {
      System.out.println("Wrong: length is not the same.");
      return;
    }
    for(int l = 0; l < runTypes.length; l++) {
      list.insertBack(runTypes[l]*10, runLengths[l]);
    }
    runNode = list.head.next;
  }

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as an
   *  array of two ints), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */

  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
    runNode = list.head.next;
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  an array of two ints (constructed here), representing the type and the
   *  size of the run, in that order.
   *  @return the next run in the enumeration, represented by an array of
   *          two ints.  The int at index zero indicates the run type
   *          (Ocean.EMPTY, Ocean.SHARK, or Ocean.FISH).  The int at index one
   *          indicates the run length (which must be at least 1).
   */

  public int[] nextRun() {
    // Replace the following line with your solution.
    int[] value = new int[2];
    if(runNode == list.head) {
      return null;
    } else {
      value[0] = runNode.item / 10;
      value[1] = runNode.num;
      runNode = runNode.next;
    }
    return value;
  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    // Replace the following line with your solution.
    Ocean o = new Ocean(width, height, starveTime);
    DListNode2 node = list.head.next;
    int index = 0;
    while(node != list.head) {
      for(int i = 0; i < node.num; i++, index++) {
        int row = index / width;
        int col = index % width;
        if(node.item / 10 == Ocean.FISH) {
          o.addFish(col, row);
        } else if(node.item / 10 == Ocean.SHARK) {
          o.addShark(col, row, node.item % 10);
        }
      }
      node = node.next;
    }
    return o;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    width = sea.width();
    height = sea.height();
    this.starveTime = sea.starveTime();
    list = new DList2();
    int index = 1, hDeep = 0, count = 1;
    int prevType = sea.cellContents(0, 0);
    if(prevType == Ocean.SHARK) {
      hDeep = sea.sharkFeeding(0, 0);
    }
    while(index < (width * height)) {
      int row = index / width;
      int col = index % width;
      if(sea.cellContents(col, row) == Ocean.SHARK && sea.sharkFeeding(col, row) == hDeep && sea.cellContents(col, row) == prevType) {
        //System.out.println("1here?" + "width:" + width + ", height:" + height + ", index:" + index);
        count++;
      } else if(sea.cellContents(col, row) != Ocean.SHARK && sea.cellContents(col, row) == prevType) {
        //System.out.println("2here?" + "width:" + width + ", height:" + height + ", index:" + index);
        count++;
      } else {
        //System.out.println("3here?" + "width:" + width + ", height:" + height + ", index:" + index);
        list.insertBack(((prevType * 10) + hDeep), count);
        prevType = sea.cellContents(col, row);
        if(prevType == Ocean.SHARK) {
          hDeep = sea.sharkFeeding(col, row);
        }else {
          hDeep = 0;
        }
        count = 1;
      }
      if(index == (width * height - 1)) {
        list.insertBack(((prevType * 10) + hDeep), count);
      }
      index++;
    }
    //System.out.println("end");
    runNode = list.head.next;
    check();
    System.out.println("end");
  }

  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    check();
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  private void check() {
    DListNode2 node = list.head.next;
    int index = 0;
    int type = Integer.MIN_VALUE;
    while(node != list.head) {
      if(node.num < 1) {
        System.out.println("The length of node should not less than 1: " + node.num + " is the length.");
        return;
      }
      index = index + node.num;
      if(type == node.item) {
        System.out.println("Warning: the type of list is the same:" + type + " is the previous type, and the new type is: " + node.item);
        return;
      }
      type = node.item;
      node = node.next;
    }
    if(index != (width * height)) {
      System.out.println("The total number of element is not equal to the cell numbers:" + index + " is the result.");
      return;
    }
  }

}