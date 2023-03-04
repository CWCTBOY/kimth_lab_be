package snust.kimth_lab.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;

@Service
@Transactional
public class RedisUtil {
  private final StringRedisTemplate redisTemplate;

  @Autowired
  public RedisUtil(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public String getData(String key) {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    return valueOperations.get(key);
  }

  public void setDataExpire(String key, String value) {
    long duration = 60 * 3L;
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    Duration expireDuration = Duration.ofSeconds(duration);
    valueOperations.set(key, value, expireDuration);
  }

  public void deleteData(String key) {
    redisTemplate.delete(key);
  }
}
