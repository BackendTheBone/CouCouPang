package p.khj745700.coucoupang.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800) // default 값 : 1800
public class RedisSessionConfig {
    //TODO : ClusterTopology 설정 필요
}
