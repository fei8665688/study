package spark;

/**
 * @author phil.zhang
 * @date 2019/8/13
 */
public class TestHashCode {

  public static void main(String[] args) {
    Object o = new Object();
    Object o1 = new Object();
    System.out.println(o.hashCode());
    System.out.println(o.hashCode());
    System.out.println(o.hashCode());
    System.out.println(o1.hashCode());
    System.out.println(o1.hashCode());
  }

}
