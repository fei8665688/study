package string.match;

/**
 * @author phil.zhang
 * @date 2019/9/27
 */
public class RegexTest {


  public static void main(String[] args) {
    String s = "45678921";
    boolean matches = s.matches("[\\d]{7}");
    System.out.println(matches);
  }
}
