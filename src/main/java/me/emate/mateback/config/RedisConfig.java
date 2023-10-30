package me.emate.mateback.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis를 사용하기 위한 Redis config 입니다.
 *
 * @author 여운석
 */
@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    @Value("${emate.redis.host}")
    private String host;

    @Value("${emate.redis.port}")
    private String port;

    @Value("${emate.redis.password}")
    private String password;

    /**
     * Redis connection factory를 빈으로 등록.
     *
     * @return the lettuce connection factory
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfiguration =
                LettuceClientConfiguration.builder()
                        .useSsl().build();

        RedisStandaloneConfiguration standaloneConfiguration =
                new RedisStandaloneConfiguration();

        standaloneConfiguration.setHostName(host);
        standaloneConfiguration.setPort(Integer.parseInt(port));
        standaloneConfiguration.setPassword(password);

        return new LettuceConnectionFactory(standaloneConfiguration, clientConfiguration);
    }

    /**
     * Redis template을 빈으로 등록.
     *
     * @return redis template
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
