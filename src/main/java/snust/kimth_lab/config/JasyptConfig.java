package snust.kimth_lab.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
  private final static String encryptKey = "my_jasypt_key";
  private final static String algorithm = "PBEWithMD5AndDES";
  private final static String iteration = "1000";
  private final static String poolSize = "1";
  private final static String pName = "SunJCE";
  private final static String className = "org.jasypt.salt.RandomSaltGenerator";
  private final static String type = "base64";

  @Bean(name = "jasyptStringEncryptor")
  public StringEncryptor stringEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(encryptKey);
    config.setAlgorithm(algorithm);
    config.setKeyObtentionIterations(iteration);
    config.setPoolSize(poolSize);
    config.setProviderName(pName);
    config.setSaltGeneratorClassName(className);
    config.setStringOutputType(type);
    encryptor.setConfig(config);
    return encryptor;
  }
}
