package p.khj745700.coucoupang.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisRepositoryConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    /**
     * <h2>Lettuce로 동시성 해결하는 방법</h2>
     * <a href="https://velog.io/@guns95/%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%AC%B8%EC%A0%9C-Lettuce-%EC%82%AC%EC%9A%A9">동시성 해결하는 방법</a>
     * Lettuce는 분산락을 지원하지 않아서 직접 구현해야 하는 문제는 있으나, 성능상 Jedis나 Redisson-pub->sub 구조보다 나은 것을 해결할 수 있음.
     * pub-sub은 메시지 큐로 전송하는 방식으로, 메시지의 소실 가능성이 있음. 소실 해도 되는 데이터의 경우에는 Redisson도 고려해볼 것.
     *
     * 비동기식은 동기가 들어가게 될 경우 성능이 더욱 하락할 경우도 있기 때문에, 테스트 및 검증이 필요함.
     *
     * <h3>lock-retry</h3>
     * lock - retry ?
     *
     */
    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    /**
     * <h2>RedisTemplate?</h2>
     * RedisConnection의 경우 binaryValue를 주고 받는데 RedisTemplate 같은 경우 수준 높은 추상화와 직렬화를 제공 해주기 때문에 사용하기가 편함.
     * RedisTemplate는 다양한 타입에 대해 다양한 연산을 제공해줌.
     * 특정 키 값에 대해 valye를 String, list, set... 등등 많은 연산을 제공해줌.
     * key bound를 통해 다양한 타입의 key도 정의할 수 있음.
     *
     * default는 ObjectOutputStream 방식을 사용하고 있음.
     *
     * 하지만 저는 StringRedisTemplate를 활용할 예정.
     * 모두 다 serializable을 구현한다는 것도 어렵고, 특정 객체만 저장하는 것도 말이 안된다고 생각.
     * 또한 직렬화를 하게 되면 보안에 취약점이 발생할 수 있음.
     */
    @Bean(name = "redisTemplate")
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate srt = new StringRedisTemplate();
        srt.setConnectionFactory(lettuceConnectionFactory());
        return srt;
    }
}


