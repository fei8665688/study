package feign;

import com.pkfare.rules.api.service.AutoPenaltyRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author phil.zhang
 * @date 2019/8/27
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.pkfare.rules.api.service")
public class AutoRulesApplication {

  @Autowired
  private AutoPenaltyRemoteService autoPenaltyRemoteService;

  public static void main(String[] args) {
    SpringApplication.run(AutoRulesApplication.class);
  }

}
