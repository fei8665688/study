package kylin;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author phil.zhang
 * @date 2019/9/5
 */
public class PasswordGenerator {

  @Test
  public void genPassword() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String password = "TABLEAU";

    String encodePassword = encoder.encode(password);
    System.out.println(encodePassword);
  }

}
