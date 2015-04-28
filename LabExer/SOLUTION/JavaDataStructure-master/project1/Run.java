/* Run.java */

public class Run {

  private int num;
  private int type;

  /**
   *  Run element constructor.
   */
  public Run() {
    num = 0;
    type = Ocean.EMPTY;
  }

  public Run(int i) {
    num = 1;
    type = i;
  }

  public Run(int num, int type) {
    this.num = num;
    this.type = type;
  }

  public int getNum() {
    return this.num;
  }

  public int getType() {
    return this.type;
  }

}