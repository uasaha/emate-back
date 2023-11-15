package me.emate.mateback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Mate back spring boot application.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MateBackApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(MateBackApplication.class, args);
  }

}
