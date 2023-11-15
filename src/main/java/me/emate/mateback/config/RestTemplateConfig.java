package me.emate.mateback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Rest template config입니다.
 *
 * @author 여운석
 */
@Configuration
public class RestTemplateConfig {

  /**
   * 커넥션 타임아웃, 리드 타임아웃을 설정한 httpRequestFactory를 반환.
   *
   * @return clientHttpRequestFactory client http request factory
   */
  @Bean
  public ClientHttpRequestFactory clientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

    factory.setConnectTimeout(30000);
    factory.setReadTimeout(100000);
    factory.setBufferRequestBody(false);

    return factory;
  }

  /**
   * Rest template을 빈으로 등록.
   *
   * @param clientHttpRequestFactory clientHttpRequestFactory
   * @return rest template
   */
  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
    return new RestTemplate(clientHttpRequestFactory);
  }
}
