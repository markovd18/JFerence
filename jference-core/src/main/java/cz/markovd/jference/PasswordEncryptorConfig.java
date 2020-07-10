package cz.markovd.jference;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncryptorConfig {

    @Bean
    public PasswordEncryptor createPasswortEncryptor() {
        return new BasicPasswordEncryptor();
    }
}
